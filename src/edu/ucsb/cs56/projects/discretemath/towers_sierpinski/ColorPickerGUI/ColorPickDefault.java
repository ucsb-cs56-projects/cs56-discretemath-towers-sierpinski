package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.ColorPickerGUI;

import java.io.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

/**
 ColorPickDefault, a more complex GUI that I infused with interesting buttons that give the option to save values for use in the future.
 Also, this class can be viewed as a "Template" for other GUI's that wish to save instances and allow choices to the user.  Orig from scratch.
 @author Will Mateer
 @version CS56, proj3, S13
 */
public class ColorPickDefault {

	private ColorPickRunner ColorRun; //Instance of ColorRunner made in the Choose option.
    private JLabel banner; //Subtitle Text
	private JFrame frame; //Main frame
	private JPanel TextPanel; //Panel to hold Scroll area with written color hexes
	private JPanel MainPanel; // Panel to add buttons and banner to
	private JTextArea text; //main display of current chosen colors in hex form? (not like preview panel made by button)
	private Map<String,ArrayList<Color>> CustomColorList; //The list of all the available "default" color schemes.
	private Map<String,ArrayList<Color>> UserChoicesToSave; //Acts as buffer of Userdefined Schemes to be saved. Different from the total map.
	private ArrayList<Color> ChosenColorList; //Current choice of ColorList scheme.
	private ArrayList<Color> TmpList; //acts as a temp transfer list for getting from a runner instance.
	private boolean open; //boolean to tell parent prog whether it is open or not.
	private JComboBox ComboChoices; //ComboBox gui for default. Reflects changes in CustomColorList.
    
	/**
	 No arg constructor that sets up everything required for the class instance to run. This includes other method calls and lots of variable defintions.
	*/
    public ColorPickDefault() {
		
	frame = new JFrame("Default Color Choices For Sierpinski Triangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	CustomColorList = new HashMap<String,ArrayList<Color>>();
	UserChoicesToSave = new HashMap<String,ArrayList<Color>>();
	CreateDefaultChoices();
	ChosenColorList = new ArrayList<Color>();
	open = true;
	//TITLE
        banner = new JLabel("Choose a Color Scheme in the drop down menu. You can preview the scheme or save your choice.",JLabel.CENTER);
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("ComicSans", Font.BOLD, 20));
		
		//DISPLAYS CURRENT CHOICES COLORS IN ORDER AND BY HEX/COLOR_TO_TEXT???
		TextPanel = new JPanel(new BorderLayout());
		text = new JTextArea(10,30);
		text.setLineWrap(true);
		text.setEditable(false);
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TextPanel.add(scroller);
		UpdateTextWithChosenColor();
		
		//COMBOBOX
		ComboChoices = new JComboBox();
		UpdateChoiceBox();
		ComboChoices.addActionListener(new ChoiceComboListener());
		
		//ADD BUTTON TO PICKER PANEL TO CONFIRM A COLOR CHOICE
		JButton PreviewButton = new JButton("Preview Current Color Scheme");
		PreviewButton.addActionListener(new PreviewListener());
		
		//ADD BUTTON TO PICKER PANEL TO EXIT AND RETURN VALUE TO PARENT PROG CALL
		JButton ExitButton = new JButton("Close Window and Export Colors");
		ExitButton.addActionListener(new ExitListener());
		
		//ADD BUTTON TO FRAME TO OPEN NEW CUSTOM PICKER
		JButton CustomButton = new JButton("Choose New Scheme");
		CustomButton.addActionListener(new CustomListener());
		
		//ADD RESET TO DEFAULTS BUTTON
		JButton ResetButton = new JButton("Reset to default schemes");
		ResetButton.addActionListener(new ResetListener());
		
		//ADD EVERYTHING TO LAYOUT
		MainPanel = new JPanel(new BorderLayout());
		
		MainPanel.add(TextPanel, BorderLayout.CENTER);
		MainPanel.add(ComboChoices, BorderLayout.NORTH);

		JPanel LeftPanel = new JPanel(new BorderLayout());
		LeftPanel.add(ExitButton, BorderLayout.NORTH);
		LeftPanel.add(ResetButton, BorderLayout.SOUTH);
		
		JPanel RightPanel = new JPanel(new BorderLayout());
		RightPanel.add(CustomButton, BorderLayout.NORTH);
		RightPanel.add(PreviewButton, BorderLayout.SOUTH);
		
		frame.add(MainPanel,BorderLayout.CENTER);
		frame.add(banner,BorderLayout.NORTH);
		frame.add(LeftPanel,BorderLayout.WEST);
		frame.add(RightPanel,BorderLayout.EAST);
		frame.pack();
        frame.setVisible(true);
	}
	/**
	 Listener for ComboBox. Calls UpdateTextWithChosenColor() method.
	*/
	class ChoiceComboListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			String ColorTmpCB = (String)ComboChoices.getSelectedItem();
			ChosenColorList = CustomColorList.get(ColorTmpCB);
			UpdateTextWithChosenColor();
		}
	}
	
	/**
	 Creates a new instance of ColorPickRunner each time
	 the button calls the ActionListener. Also determines if the user wants to end or continue.
	 */
	class CustomListener implements ActionListener { //ALMOST DONE WITH METHOD
		public void actionPerformed(ActionEvent event){
			final Runnable Linker = new Runnable() {
				public void run() {
					ColorRun = new ColorPickRunner();
					TmpList = ColorRun.returnColorList();
				}
			};
			
			Thread LinkThread = new Thread() {
				public void run() {
					try {
						synchronized (this) {
							SwingUtilities.invokeAndWait(Linker);
							while (ColorRun.isOpen()) {
								Thread.sleep(100);
							}
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						
						TmpList = ColorRun.returnColorList();
						Object[] options = {"Save & Continue","Export & Exit"};
						int n = JOptionPane.CLOSED_OPTION;
						String Choice = null;

						while (n == JOptionPane.CLOSED_OPTION) {
							n = JOptionPane.showOptionDialog(null, "Would you like to save your choice and continue browsing or would you like to confirm as your final choice",
															  "Custom Color Chooser", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						}
						if(n == JOptionPane.YES_OPTION) {
							while (Choice == null) {
								Choice = (String)JOptionPane.showInputDialog(null,"Choose a Name for the Custom Color Scheme","Custom Color Chooser",
																	   JOptionPane.PLAIN_MESSAGE,null,null,"PLACEHOLDER");
								if (CustomColorList.containsKey(Choice) == true) {
									Choice = null;
								}
							}
							ChosenColorList = TmpList;
							CustomColorList.put(Choice, ChosenColorList);
							UserChoicesToSave.put(Choice, ChosenColorList);
							UpdateTextWithChosenColor();
							ComboChoices.addItem(Choice);
							ComboChoices.setSelectedItem(Choice);
						}
						else if(n == JOptionPane.NO_OPTION) {
							ChosenColorList = TmpList;
							frame.setVisible(false);
							WriteToPropertiesFile();
							SaveNewDefaultViaSerializable();
							open = false;
							frame.dispose();
							System.exit(1);
						}
						
					}
				}
			};
			LinkThread.start();
		}
	}
	
	/**
	 Listener for preview button.  Also makes a PreviewPanel instance as defined later on.
	*/
	class PreviewListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			final Runnable Linker = new Runnable() {
				public void run() {
					JFrame PreviewFrame = new JFrame();
					PreviewFrame.setSize(30*ChosenColorList.size(),200);
					PreviewPanel Prev = new PreviewPanel();
					PreviewFrame.add(Prev);
					PreviewFrame.setVisible(true);
				}
			};
				
			Thread LinkThread = new Thread() {
				public void run() {
					try {
						synchronized (this) {
							SwingUtilities.invokeAndWait(Linker);
							
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			LinkThread.start();
		}

	}
	
	/**
	 Draws the preview panel, and is called in the preview listener
	*/
	class PreviewPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i=0; i<ChosenColorList.size(); i++) {
				g.setColor(ChosenColorList.get(i));
				g.fillRect(30*i, 0, 30, 200);
			}
		}
	}
	
	/** 
	 This class is called when exit button is pressed.  This exports 
	 chosen colors to a Properties file and saves the UserDef values via serializible. And ends program.
	 */
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			open = false;
			frame.setVisible(false);
			WriteToPropertiesFile();
			SaveNewDefaultViaSerializable();
			frame.dispose();
			System.exit(0);
		}
	}
	
	/**
	 Listener for Reset Button, removes items from combo box and resets the UserDef values.
	*/
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			File serFile = new File("build/Saved_Default.ser");
			if (serFile.exists()){
				serFile.delete();
			}
			for (Map.Entry<String, ArrayList<Color>> entry : UserChoicesToSave.entrySet())
			{
				ComboChoices.removeItem(entry.getKey());
			}
			UserChoicesToSave = new HashMap<String,ArrayList<Color>>(); 
			SaveNewDefaultViaSerializable();
			ComboChoices.setSelectedIndex(0);
			UpdateTextWithChosenColor();
		}
	}
	
	/**
	 method that provides hardcoded defaults to load into the program. Also checks if there were saved user defined values.
	*/
	private void CreateDefaultChoices() { // CAN HARDCODE ADDITIONAL VALUES
		ArrayList<Color> Autumn = new ArrayList<Color>();
		ArrayList<Color> SpringTime = new ArrayList<Color>();
		ArrayList<Color> Rainbow = new ArrayList<Color>();
		ArrayList<Color> GrayScale = new ArrayList<Color>();
		ArrayList<Color> Purples = new ArrayList<Color>();
		// 5 new selections below added by George Shih, W14
		ArrayList<Color> Winter = new ArrayList<Color>();
		ArrayList<Color> Wood = new ArrayList<Color>();
		ArrayList<Color> Inferno = new ArrayList<Color>();
		ArrayList<Color> Shadow = new ArrayList<Color>();
		ArrayList<Color> USA = new ArrayList<Color>();

		
		Collections.addAll(Autumn, new Color(255,0,0), new Color(255,153,51), new Color(255,255,0), new Color(51,204,0), new Color(0,0,255));
		Collections.addAll(SpringTime, new Color(0,255,0), new Color(0,204,102), new Color(0,204,204), new Color(153,0,204), new Color(255,153,153));
		Collections.addAll(Rainbow, new Color(255,0,51), new Color(0,153,51), new Color(0,0,204), new Color(0,255,255), new Color(204,204,204));
		Collections.addAll(GrayScale, new Color(200,200,200), new Color(150,150,150), new Color(100,100,100), new Color(50,50,50), new Color(0,0,0));
		Collections.addAll(Purples, new Color(255,102,255), new Color(255,0,255), new Color(204,0,204), new Color(153,0,153), new Color(51,0,51));
		// Not too certain what the last 2 Colors are used for, they don't seem to change the color scheme at all
		Collections.addAll(Winter, new Color(0,255,255), new Color(0,0,255), new Color(0,128,255), new Color(0,0,0), new Color(0,0,0));
		Collections.addAll(Wood, new Color(100,50,0), new Color(160,80,0), new Color(200,160,120), new Color(0,0,0), new Color(0,0,0));
		Collections.addAll(Inferno, new Color(255,0,0), new Color(255,75,0), new Color(255,0,75), new Color(0,0,0), new Color(0,0,0));
		Collections.addAll(Shadow, new Color(0,0,0), new Color(255,0,0), new Color(200,0,0), new Color(0,0,0), new Color(0,0,0));
		Collections.addAll(USA, new Color(0,0,255), new Color(255,0,0), new Color(225,225,225), new Color(0,0,0), new Color(0,0,0));
		
		CustomColorList.put("Autumn",Autumn);
		CustomColorList.put("SpringTime",SpringTime);
		CustomColorList.put("Rainbow",Rainbow);
		CustomColorList.put("GrayScale",GrayScale);
		CustomColorList.put("Purples",Purples);
		CustomColorList.put("Winter",Winter);
		CustomColorList.put("Wood",Wood);
		CustomColorList.put("Inferno",Inferno);
		CustomColorList.put("Shadow",Shadow);
		CustomColorList.put("USA",USA);
		
		CheckSavedUserDefinedSchemes();
	}

	/**
	 Updates the combobox with CustomColorList values
	*/
	private void UpdateChoiceBox() {
		CreateDefaultChoices();
		
		for (Map.Entry<String, ArrayList<Color>> entry : CustomColorList.entrySet())
		{
			ComboChoices.addItem(entry.getKey());
		}
	}
	
	/**
	 method that goes through ChosenColorList and writes it to the text area, resets each time.
	*/
	private void UpdateTextWithChosenColor() { 
		text.setText("");
		for (int i=0; i < ChosenColorList.size();i++)
		{
			Color TmpCol = ChosenColorList.get(i);
			text.append(Integer.toString(i) + ".)" + TmpCol.toString() + "\n");
		}
	}
	
	/**
	 Method that saves the chosen colors in the ChosenColorList 
	 to a properties file in the build directory.
	*/
	public void WriteToPropertiesFile() {
		
	    Properties prop = new Properties();
	    try {
		for (int i = 0; i < ChosenColorList.size(); i++) 
		    {
			Color ColorToAdd = ChosenColorList.get(i);
			String HexString = Integer.toHexString(ColorToAdd.getRGB());
			// Removes opacity element "ff"
			String HexString2 = HexString.substring(2);
			String ColorName = Integer.toString(i);
			prop.setProperty(ColorName, HexString2); 
		    }

		FileOutputStream Output = new FileOutputStream("colors.properties");
		prop.store(Output, "Colors Saved by an instance of ColorPickDefault");
		Output.flush();
		Output.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}
	
	/**
	 Checks for a serialized file from previous ColorDef instances.  Saves the findings to CustomColorList and UserChoicesToSave.
	*/
	private void CheckSavedUserDefinedSchemes() {
			//checks the saved Serialzed obj file.
		
			//if there and has info, it adds to the CustomColorList. through form of for loop, until end of file.
			
			//if nothing is there then does nothing.
		
			//also checks if there are duplicates, cant have same names, but can have same schemes.
		
		try {
			FileInputStream fileStream = new FileInputStream("build/Saved_Default.ser");
			ObjectInputStream inputStream = new ObjectInputStream(fileStream);
			Object UserDefaults = inputStream.readObject();
			HashMap<String,ArrayList<Color>> TmpDefaults = (HashMap<String,ArrayList<Color>>) UserDefaults; //HASH>>>>????
			UserChoicesToSave.putAll(TmpDefaults);
			if (UserChoicesToSave != null) {
				for (Map.Entry<String, ArrayList<Color>> entry : UserChoicesToSave.entrySet())
				{
					CustomColorList.put(entry.getKey(),entry.getValue());
				}
			}
			inputStream.close();
		}
		catch (Exception ex) {
			System.out.println("Saved_Default.ser does not exist.  This means there are no previous user defined color schemes.");
		}
	}
	
	/**
	 Saves the UserDef values of ColorDef instance to a .ser file.  This is revived in later instances to extract saved User Color scheme choices.
	*/
	private void SaveNewDefaultViaSerializable() { 
			try{
				FileOutputStream fileStream = new FileOutputStream("build/Saved_Default.ser");
				ObjectOutputStream outStream = new ObjectOutputStream(fileStream);
				outStream.writeObject(UserChoicesToSave);
				outStream.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
	}
	
	/**
	 Method that works just like how ColorPickRunner.isOpen() works with this class
	 This method can be used by a parent class call to determine if this window 
	 has been closed yet.
	 @return open set to true when open, false when closed
	*/
	public boolean isOpen(){
		return open;
	}
	
	/**
	 Method for nonmembers to access the ChosenColorList directly.
	 @return ChosenColorList Arraylist that is the chosen Color scheme
	*/
	public ArrayList<Color> returnColorList() {
		return ChosenColorList;
	}
	
    public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
			@Override
			public void run() {
                new ColorPickDefault();
            }
        });
    }

}
