package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.ColorPickerGUI;

import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

/**
 Acts like an inner class to the parent call of ColorPickRunner class.
 Has its own JFrame and buttons as well as an instance of JColorChooser.
 */


public class ColorPickSaver implements ChangeListener {

    protected JColorChooser ColorPicker;
    protected JLabel banner;
    private JFrame frame;
    private Color CurrentColor;
    private boolean open;
    public int CurrentColorInt;
    public int SavedColorToExport;

    
    /**
       No arg constructor. This is called each time if you want
       a new ColorPick frame.
    */
	
    public ColorPickSaver() {
	frame = new JFrame("Sierpinski Color Picker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		CurrentColor = new Color(255,255,255);
		open = true;
        
        banner = new JLabel("Choose the color and confirm",JLabel.CENTER);
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 20));
        banner.setPreferredSize(new Dimension(100, 65));
		
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Objective"));
		
        ColorPicker = new JColorChooser(banner.getForeground());
        ColorPicker.getSelectionModel().addChangeListener(this);
        ColorPicker.setBorder(BorderFactory.createTitledBorder("Choose Next Color for triangles"));
		
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

	
    /**
       Listener linked to the confirm button in order to know when to close and return the color chosen.
       @return Color CurrentColor
    */

    class ConfirmListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
			
	    //Parent call, implicitly extracts the return color, by cycling throught the boolean open, to see when the window closes.
	    frame.setVisible(false);
	    open = false;
	    frame.dispose();
	    ReturnSelectedColor();
	}
    }


    /**
       Method called whenever JColorChooser notifies 
       this ActionListener as to a ChangeEvent.  What you must implement for ChangeListener
       @param ChangeEvent e event that causes method to fire
    */

    public void stateChanged(ChangeEvent e) {
        CurrentColor = ColorPicker.getColor();
        //banner.setForeground(CurrentColor);
		banner.setBackground(CurrentColor);
    }

	
    /**
       Gives nonmembers access to boolean open, which is used to see if the window is still open.
       @return boolean open 
    */
    public boolean isOpen() {
	return open;
    }

	
    /**
       Gives nonmember access to CurrentColor, which is the color selected by the user.
       @return Color CurrentColor
    */

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
