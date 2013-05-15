import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class S extends JPanel{

	private int n;
	private ArrayList<Color>colors;
	private double tx, ty;
	private double side, sL;

	public S(int n){
		this.n=n;
		side=900;
		sL=side/(Math.pow(2,n+1)-1);
		colors = new ArrayList<Color>();
		for (int i=0;i<n+1;i++){
			colors.add(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		tx=600.0;
		ty=50.0;
	}

	public void drawMove(Graphics g, int depth, double tX, double tY, String order){	
		//order stuff

		double x1 = sL * (Math.pow(2,depth)-1) * Math.cos(Math.PI/3);
		double y1 = sL * (Math.pow(2,depth)-1) * Math.sin(Math.PI/3);
		double x2 = sL * (Math.pow(2,depth)) * Math.cos(Math.PI/3);
		double y2 = sL * (Math.pow(2,depth)) * Math.sin(Math.PI/3);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(colors.get(depth));
		g2.setStroke(new BasicStroke(3));
		g2.draw(new Line2D.Double(tX-x1,tY+y1,tX-x2,tY+y2));
		g2.draw(new Line2D.Double(tX+x1,tY+y1,tX+x2,tY+y2));

		double bX=tX+sL * (Math.pow(2,depth+1)-1)*Math.cos(Math.PI/3)-sL*Math.pow(2,depth);
		double bY=tY+sL* (Math.pow(2,depth+1)-1) * Math.sin(Math.PI/3);

		g2.draw(new Line2D.Double(bX,bY,bX+sL,bY));

		//draw Strings along lines

	}	

	public void drawSerpienski(Graphics g, int depth, double topX, double topY, String order){

		if(depth < 0) return;

		drawMove(g, depth, topX, topY, order);

		double x = sL * Math.pow(2,depth) * Math.cos(Math.PI/3);
		double y = sL * Math.pow(2,depth) * Math.sin(Math.PI/3);

		drawSerpienski(g,depth-1, topX, topY, order+"T");
		drawSerpienski(g,depth-1, topX-x,topY+y, order+"L");
		drawSerpienski(g,depth-1, topX+x, topY+y, order+"R");

	}

	public void paintComponent(Graphics g){
		drawSerpienski(g, n, tx, ty, "");	
	}

	public static void main(String[] args){

		JFrame f = new JFrame("yo");
		f.setContentPane(new S(Integer.parseInt(args[0])));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1200,900);
		f.setVisible(true);

	}




}
