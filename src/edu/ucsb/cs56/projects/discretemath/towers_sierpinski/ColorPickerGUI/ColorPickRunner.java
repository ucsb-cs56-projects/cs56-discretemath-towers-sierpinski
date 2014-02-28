package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.ColorPickerGUI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

/**
 Parent Frame class that makes instances of ColorPickSaver
*/
public class ColorPickRunner {

    private ColorPickSaver ColorPicker;
    protected JLabel banner;
    private JFrame frame;
    private JPanel TextPanel;
    private JPanel MainPanel; 
    private JTextArea text;
    private ArrayList<Color> ColorList;
    public int CurrentColor;
    private Color TmpCol;
    private boolean open;
    
    /**
       no arg constructor that makes the GUI, and sets up private variables.
    */
    public ColorPickRunner() {
	//MAIN FRAME, was originally in a static method, but I had difficulty passing and obtaining values between two gui instances.
	frame = new JFrame("Color Picker For Sierpinski Triangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ColorList = new ArrayList<Color>();
	CurrentColor = 0;
	open = true;
		
	//TITLE
        banner = new JLabel("Colors Chosen Already For Triangles",JLabel.CENTER);
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		//MAIN TEXT AREA
		TextPanel = new JPanel(new BorderLayout());
		text = new JTextArea(10,30);
		text.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TextPanel.add(scroller);
				
		//ADD BUTTON AT BOTTOM OF PICKER PANEL TO CONFIRM A COLOR CHOICE
		JButton ChooseButton = new JButton("Choose Next Color");
		ChooseButton.addActionListener(new ChooseListener());
		
		//ADD BUTTON AT BOTTOM OF PICKER PANEL TO EXIT AND RETURN VALUE TO PARENT PROG CALL
		JButton ExitButton = new JButton("Close Window and Export Colors");
		ExitButton.addActionListener(new ExitListener());
		
		MainPanel = new JPanel(new BorderLayout());
		
		MainPanel.add(TextPanel, BorderLayout.CENTER);
		MainPanel.add(banner, BorderLayout.NORTH);
		MainPanel.add(ChooseButton, BorderLayout.EAST);
		MainPanel.add(ExitButton, BorderLayout.WEST);
		
		frame.add(MainPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 Creates a new instance of ColorPickSaver each time
	 the button calls the ActionListener.  Also the main thread waits for the 
	 new window to close before it can finish.
	 */
	class ChooseListener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
		TmpCol = new Color(0,0,0);
			
		final Runnable Linker = new Runnable() {
			public void run() {
			    ColorPicker = new ColorPickSaver();
			    TmpCol = ColorPicker.ReturnSelectedColor();
			}
		    };
			
			Thread LinkThread = new Thread() {
				public void run() {
				    try {
					synchronized (this) {
					    SwingUtilities.invokeAndWait(Linker);
					    while (ColorPicker.isOpen()) {
						Thread.sleep(10);
					    }
					}
				    }
				    catch (Exception e) {
					e.printStackTrace();
				    }
				    finally {
					TmpCol = ColorPicker.ReturnSelectedColor();
					ColorList.add(TmpCol);
					text.append(Integer.toString(CurrentColor) +".) "+ TmpCol.toString() + "\n");
					CurrentColor++;

				    }
				}
			    };
			LinkThread.start();
	    }
	}
	
	/** 
	 This class is called when exit button is pressed.  This exports 
	 chosen colors to a Properties file.
	 */
	class ExitListener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
		open = false;
		//return arraylist value to target...?, should be obj that instantiates this obj to make call to private arraylist.
		frame.setVisible(false);
		WriteToPropertiesFile();
		frame.dispose();
		//returnColorList();
	    }
		
	}
	
	/**
	 Method that saves the chosen colors in the ColorList 
	 to a properties file in the build directory.
	*/
	public void WriteToPropertiesFile() {
	    
	    Properties prop = new Properties();
	    try {
		for (int i=0; i<ColorList.size(); i++) 
		    {
			Color ColorToAdd = ColorList.get(i);
			String HexString = Integer.toHexString(ColorToAdd.getRGB());
			// Removes opacity element "ff"
			String HexString2 = HexString.substring(2);
			String ColorName = Integer.toString(i);
			prop.setProperty(ColorName, HexString2); 
		    }
		FileOutputStream Output = new FileOutputStream("colors.properties");
		prop.store(Output, "Colors Saved by an instance of ColorPickRunner");
		Output.flush();
		Output.close();
	    } catch (IOException ex) {
    		ex.printStackTrace();
	    }
	}

	/**
	 Method that works just like how ColorPickSaver.isOpen() works with this class
	 This method can be used by a parent class call to determine if this window 
	 has been closed yet.
	 @return boolean open
	*/
	public boolean isOpen(){
		return open;
	}
	
	/**
	 Method for nonmembers to access the ColorList directly.
	 @return ArrayList<Color> ColorList
	*/
	public ArrayList<Color> returnColorList() {
		return ColorList;
	}
	
    public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
			@Override
			public void run() {
                new ColorPickRunner();
            }
        });
    }

}
