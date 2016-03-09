package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.AffineTransform;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;


/**
   *Runs a GUI version of the first project. Uses the Batik library to display SVG image.
   *@author George Shih
   *@author Caitlin Scarberry
   *@version CS56 Winter 2016
*/

public class SierpinskiTriangleGUI {
	public static void main(String[] args) {

        // Create a new JFrame.
        JFrame f = new JFrame("Sierpinski Triangle");
        SierpinskiTriangleGUI app = new SierpinskiTriangleGUI(f);

        // Display the frame.
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // The frame.
    protected JFrame frame;

    //Transform for zooming/moving the image
    private AffineTransform svgTransform = new AffineTransform();

    // The SVG canvas.
    protected JSVGCanvas svgCanvas = new JSVGCanvas();

    public SierpinskiTriangleGUI(JFrame f) {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add("Center",svgCanvas);
        f.add(panel);
        File file = new File("output.html");
        try {
            svgCanvas.setURI(file.toURL().toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        svgCanvas.setRenderingTransform(svgTransform);
        JPanel zoomControls = new JPanel();
        JButton in = new JButton();
        in.setText("Zoom In");
        in.addActionListener(e -> {svgTransform.scale(1.2,1.2); svgCanvas.setRenderingTransform(svgTransform);});
        JButton out = new JButton();
        out.setText("Zoom out");
         out.addActionListener(e -> {svgTransform.scale(.8,.8); svgCanvas.setRenderingTransform(svgTransform);});
        zoomControls.add(in);
        zoomControls.add(out);

       	panel.add("South",zoomControls);


       	MouseAdapter listener = new MouseAdapter(){
       		long lastUpdate;
       		private int mouseX;
       		private int mouseY;
       		public void mouseDragged(MouseEvent e){
       			/*restricting how often the translation is applied
       			  actually makes dragging smoother
       			  and uses less of the computer's resources*/
	       		if(System.currentTimeMillis()-lastUpdate>(1000/24)){
	       			svgTransform.translate(e.getX()-mouseX, e.getY()-mouseY);
	       			svgCanvas.setRenderingTransform(svgTransform);
	       			mouseX = e.getX();
	       			mouseY = e.getY();
	       			lastUpdate = System.currentTimeMillis();
       			}
       		}
       		public void mousePressed(MouseEvent e){
       			mouseX = e.getX();
       			mouseY = e.getY();
       		}
       	};

       	svgCanvas.addMouseMotionListener(listener);
       	svgCanvas.addMouseListener(listener);

        f.setSize(400, 400);
    }
    	
}