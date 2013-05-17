package edu.cs56.projects.discretemath.towers_sierpinski;

// What jaco-a imports for class which draws triangles
import java.awt.*;
import java.awt.geom.*; // May not need this?
import java.util.*; // Or... this? Or anything below?
import javax.swing.*;
import java.awt.event.*;

// What Conrad imports in lab5 for saving image
/*import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
*/

// Probably still need these
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author Shanen Cross
 * @version CS56, S13, lab04
 *
 * Contains method which allows you to save the image in either the .jpg or .pnh format.
*/

public class SaveImage {

    public void save(Component comp) {
	Dimension size = comp.getSize();
	BufferedImage bufImage = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
	Graphics2D g = bufImage.createGraphics();
	comp.paint(g);

	try {
	    ImageIO.write(bufImage, "png", new File("SierpinskiTriangle.png"));
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    // main for TESTING PURPOSES ONLY
    /* public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.getContentPane().add(BorderLayout.CENTER, new DrawPane());
	frame.setSize(300,300);
	frame.setVisible(true);
    }

    public static class DrawPane extends JPanel{
        public void paintComponent(Graphics g){
	    //draw on g here e.g.
	    g.fillRect(20, 20, 100, 200);
	}
	}*/
}
