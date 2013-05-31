import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;



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
	
	//Creates a new instance of ColorPickSaver each time, currently this works the way I want it to, but I was unable to figure a simpler version.
	class ChooseListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
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
	
	//Parent program should check for end, similar to how this obj checks for the end of ColorPickSaver
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			open = false;
			//return arraylist value to target...?, should be obj that instantiates this obj to make call to private arraylist.
			frame.setVisible(false);
			frame.dispose();
			returnColorList();
		}
		
	}
	
	/*public void WriteToPropertiesFile(){  // NOT NECCESSARY
		
		Properties prop = new Properties();
    	try {
			for (int i=0; i<ColorList.size(); i++) 
			{
				Color ColorToAdd = ColorList.get(i);
				String HexString = Integer.toHexString(ColorToAdd.getRGB());
				String ColorName = "Color #" + Integer.toString(i);
				prop.setProperty(ColorName, HexString); 
			}
			FileOutputStream Output = new FileOutputStream("build/ColorProperties");
			prop.store(Output, "Colors Saved by an instance of ColorPickRunner");
			Output.flush();
			Output.close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}*/

	public boolean isOpen(){
		return open;
	}
	
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
