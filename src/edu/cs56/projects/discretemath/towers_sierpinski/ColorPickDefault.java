package edu.cs56.projects.discretemath.towers_sierpinski;

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
 Parent Frame class that makes instances of ColorPickRunner or displays default color schemes to choose from.
*/
public class ColorPickRunner {

	private ColorPickRunner ColorRunner; //needed
    protected JLabel banner; //Displays written "Objective"?
	private JFrame frame; //main frame
	private JPanel TextPanel; //panel to hold Scroll area with written color hexes
	private JPanel MainPanel; // Panel to add buttons and banner to
	private JTextArea text; //main display of current chosen colors in hex form? (not like preview panel made by button)
	private Map<String,ArrayList<Color>> CustomColorList; //Maybe change to map.  The list of all the available "default" color schemes.
	private List<ArrayList<Color>> UserChoicesToSave; //Possibly unnecessary.  Acts as buffer to be saved. Different from the total map.
	private ArrayList<Color> ChosenColorList; //Current choice of ColorList.  Would be sent to props file/maybe added to defaults/
	private Arraylist<Color> TmpList; // maybe unnecessary, but acts as a tmp transfer list for getting from a runner instance.
	private boolean open; //boolean to tell parent prog whether it is open or not.
	private JComboBox ComboChoices; //ComboBox gui for default. Is added to if CustomColorList is added to. Reflects CustomColorList.
    
	/**
	 no arg constructor that makes the GUI, and sets up private variables.
	*/
    public ColorPickDefault() {
		
		frame = new JFrame("Default Color Choices For Sierpinski Triangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CustomColorList = new Map(<String>,ArrayList<Color>);//DUNNO IF CORRECT.
		CustomColorList = CreateDefaultChoices(); //NEED TO IMPLEMENT METHOD
		ChosenColorList = FIRST DEFAULT OPTION FOR CustomColorList//NEED TO IMPLEMENT BASED ON DEFAULT SCHEMES
		open = true;
		
		//TITLE
        banner = new JLabel("Choose a Color Scheme in the drop down menu.  You can preview the scheme or save your choice.",JLabel.CENTER);
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		//DISPLAYS CURRENT CHOICES COLORS IN ORDER AND BY HEX/COLOR_TO_TEXT???
		TextPanel = new JPanel(new BorderLayout());
		text = new JTextArea(10,30);
		text.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TextPanel.add(scroller);
		//USE UPDATE METHOD, also updates everytime ChosenColorList changes.
		
		//COMBOBOX, needs to be set to reflect changes in CustomColorList...
		ComboChoices = new JComboBox(SetChoices());
		ComboChoices.setSelectedIndex(0);
		ComboChoices.addActionListener(ChoiceComboListener);
		//USE UPDATE METHOD
		
		//ADD BUTTON AT BOTTOM OF PICKER PANEL TO CONFIRM A COLOR CHOICE
		JButton PreviewButton = new JButton("Preview Current Color Scheme");
		PreviewButton.addActionListener(new PreviewListener());
		
		//ADD BUTTON AT BOTTOM OF PICKER PANEL TO EXIT AND RETURN VALUE TO PARENT PROG CALL
		JButton ExitButton = new JButton("Close Window and Export Colors");
		ExitButton.addActionListener(new ExitListener());
		
		//ADD BUTTON AT BOTTOM OF FRAME TO OPEN NEW CUSTOM PICKER
		JButton CustomButton = new JButton("Choose New Scheme");
		CustomButton.addActionListener(new CustomListener());
		
		//ADD RESET TO DEFAULTS BUTTON???? removes user defined classes without having to ant clean. Possibly.
		
		//ADD TO VARIOUS PANELS,THEN TO FRAME TO MAKE LAYOUT SIMPLER.
		MainPanel = new JPanel(new BorderLayout());
		
		MainPanel.add(TextPanel, BorderLayout.CENTER);
        MainPanel.add(banner, BorderLayout.NORTH);
		MainPanel.add(CustomButton, BorderLayout.EAST);
		MainPanel.add(ExitButton, BorderLayout.WEST);
		MainPanel.add(PreviewButton, BorderLayout.PAGE_END);
		
		frame.add(MainPanel);
		frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 Creates a new instance of ColorPickRunner each time
	 the button calls the ActionListener.  Also the main thread waits for the 
	 new window to close before it can finish.
	 ////USE THIS BUTTON TO RUN THE COLORRUNNERCLASS INSTANCE...
	 
	 */
	
	class ChoiceComboListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			JComboBox tmpCB = (JComboBox)e.getSource();
			ArrayList ColorTmpCB = (ArrayList)cb.getSelectedItem();
			ChosenColorList = ColorTmpCB;
			UPDATECOLORLISTLABELWITHCOLORS() //SEPARTE METHOD TO UPDATE COLOR LABEL WITH COLOR IN CURRENT LIST CHOSEN
		}
	}
	
	class CustomListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			TmpList.add(new Color(0,0,0));
			
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
							//ADD TMP LIST TO CURRENT DEFAULT CHOICES OR CONFIRM AS COLOR CHOICE PROMPT
							
							PROMPT/OPTIONBOX...?>???
							if add then
								CustomColorList.add(TmpList);
								ChosenColorList = TmpList;
								//ALSO UPDATE COMBO CHOOSER WITH NEW CHOICE
								UpdateLabelWithChosenColor();
								//SCROLLBOXXXtext.append(Integer.toString(CurrentColor) +".) "+ TmpCol.toString() + "\n");
								CurrentColor++;
								
							if confirm but not add
								ChosenColorList = TmpList;
								frame.setVisible(false);
								WriteToPropertiesFile();
								SaveNewDefaultViaSerializable()
								frame.dispose();
					}
				}
			};
			LinkThread.start();
		}
	}
	
	class PreviewListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			//DISPLAY EXTRA JFRAME, with preview of current chosen color scheme in order, by showing a box/checkbox/list??? idk yet.
			
		}
	}
	
	/** 
	 This class is called when exit button is pressed.  This exports 
	 chosen colors to a Properties file.//SHOULD STAY THE SAME MOSTLY  MAYBE CHANGE TO SERIALZABLE... for the saving of arraylist objs.
	 */
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			open = false;
			//return arraylist value to target...?, should be obj that instantiates this obj to make call to private arraylist.
			frame.setVisible(false);
			WriteToPropertiesFile();
			SaveNewDefaultViaSerializable();
			frame.dispose();
			//returnColorList();
		}
		
	}
	
	private void CreateDefaultChoices() { //UNTESTED, can be hardcoded with new defaults.
		ArrayList<Color> Rainbow = new ArrayList<Color>("","","","","");
		ArrayList<Color> SpringTime = new ArrayList<Color>("","","","","");
		ArrayList<Color> Winter = new ArrayList<Color>("","","","","");
		ArrayList<Color> GrayScale = new ArrayList<Color>("","","","","");
		ArrayList<Color> Purples = new ArrayList<Color>("","","","","");
		
		CustomColorList.add("Rainbow",Rainbow);
		CustomColorList.add("SpringTime",SpringTime);
		CustomColorList.add("Winter",Winter);
		CustomColorList.add("GrayScale",GrayScale);
		CustomColorList.add("Purples",Purples);
		
		CheckSavedUserDefinedSchemes();
	}
	
	private String[] UpdateChoiceBox() {
			go through keys in Map<String,Arraylist<Color>>
			return list of those keys as the defintions of the schemes of the colors.
	}
	
	private UpdateLabelWithChosenColor() { //goes through ChosenColorList and writes it to the text area, resets each time.
		TextArea.set( go through ChosenColorList colors and print out each color -> hex/color(r,g,b);)
	}
	
	
	
	/**
	 Method that saves the chosen colors in the ColorList 
	 to a properties file in the build directory. //STAY THE SAME MOSTLY
	*/
	public void WriteToPropertiesFile(){
		
		Properties prop = new Properties();
    	try {
			for (int i=0; i<ColorList.size(); i++) 
			{
				Color ColorToAdd = ColorList.get(i);
				String HexString = Integer.toHexString(ColorToAdd.getRGB());
				String ColorName = "Color_" + Integer.toString(i);
				prop.setProperty(ColorName, HexString); 
			}
			FileOutputStream Output = new FileOutputStream("build/ColorProperties");
			prop.store(Output, "Colors Saved by an instance of ColorPickRunner");
			Output.flush();
			Output.close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	private void CheckSavedUserDefinedSchemes() {
			//checks the saved Serialzed obj file.
		
			//if there and has info, it adds to the CustomColorList. throught form of for loop, until end of file.
			
			//if nothing is there then does nothing.
		
			//also checks if there are duplicates, cant have same names, but can have same schemes.
	}
	
	
	private void SaveNewDefaultViaSerializable() { //Adds to the default colors provided and when called in option box of ColorRunner.close()
		
	}
	
	private savtoPropertiesFile() { //METHOD USED Just like in ColorRunner, needs to save the One list of Chosen colors at the close().
		Properties prop = new Properties();
    	try {
			for (int i=0; i<ColorList.size(); i++) 
			{
				Color ColorToAdd = ColorList.get(i);
				String HexString = Integer.toHexString(ColorToAdd.getRGB());
				String ColorName = "Color_" + Integer.toString(i);
				prop.setProperty(ColorName, HexString); 
			}
			FileOutputStream Output = new FileOutputStream("build/ColorProperties");
			prop.store(Output, "Colors Saved by an instance of ColorPickDefault");
			Output.flush();
			Output.close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }  //NEED TO FIX, ie change Props destination.
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
	 Method for nonmembers to access the ColorList directly.
	 @return ArrayList<Color> ColorList
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
