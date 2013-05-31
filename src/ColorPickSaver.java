import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;



public class ColorPickSaver implements ChangeListener {

	protected JColorChooser ColorPicker;
    protected JLabel banner;
	private JFrame frame;
	private Color CurrentColor;
	private boolean open;
	public int CurrentColorInt;
	public int SavedColorToExport;
    
    public ColorPickSaver() {
		frame = new JFrame("Sierpinski Color Picker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		CurrentColor = new Color(255,255,255);
		open = true;
        
        //Set up the banner at the top of the window
        banner = new JLabel("Choose the colors for the Sierpinski Triangles",JLabel.CENTER);
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 20));
        banner.setPreferredSize(new Dimension(100, 65));
		
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Objective"));
		
        //Set up color chooser for setting text color
        ColorPicker = new JColorChooser(banner.getForeground());
        ColorPicker.getSelectionModel().addChangeListener(this);
        ColorPicker.setBorder(BorderFactory.createTitledBorder("Choose Next Color for triangles"));
		
		//ADD BUTTON AT BOTTOM OF PICKER PANEL TO CONFIRM A COLOR CHOICE
		JButton ConfirmButton = new JButton("Confirm Color Choice");
		ConfirmButton.addActionListener(new ConfirmListener());
		
		ColorPicker.setPreviewPanel(new JPanel());
		
		JComponent ColorPickPane = new JPanel(new BorderLayout());
		
        ColorPickPane.add(bannerPanel, BorderLayout.NORTH);
        ColorPickPane.add(ColorPicker, BorderLayout.CENTER);
		ColorPickPane.add(ConfirmButton, BorderLayout.SOUTH);
		
		ColorPickPane.setOpaque(true);
        frame.setContentPane(ColorPickPane);
        frame.pack();
        frame.setVisible(true);
	}
	
	class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			
			//Parent call, implicitly extracts the return color, by cycling throught the boolean open, to see when the window closes.
			frame.setVisible(false);
			open = false;
			frame.dispose();
			ReturnSelectedColor();
		}
	}

	//What happens when ColorPicker's state is changed.
	public void stateChanged(ChangeEvent e) {
        CurrentColor = ColorPicker.getColor();
        //banner.setForeground(CurrentColor);
		banner.setBackground(CurrentColor);
    }
	
	public boolean isOpen() {
		return open;
	}
	
	public Color ReturnSelectedColor() {
		return CurrentColor;
	}

    public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
            public void run() {
				new ColorPickSaver();
            }
        });
    }

}
