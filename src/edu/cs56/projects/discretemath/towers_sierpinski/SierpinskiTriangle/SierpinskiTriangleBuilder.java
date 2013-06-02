package edu.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;

import edu.cs56.projects.discretemath.towers_sierpinski.SVGraphics.*;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

/**
 * SierpinskiTriangleBuilder class used with SierpinskiTriangle class to build 
 * Towers of Hanoi representation
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SierpinskiTriangleBuilder {
    private int disks;
    private SVDefinitions defintions = new SVDefinitions();
    
    /** one arg constructor defines the # of disks
     */
    public SierpinskiTriangleBuilder(int disks) {
        this.disks = disks;
        build();
    }
    
    /** two arg constructor defines the # of disks
     */
    public SierpinskiTriangleBuilder(int disks, ArrayList<Color> colors) {
        this.disks = disks;
        build();
        setColors(colors);
    }
    
    /** sets color array list for SierpinskiTriangle class
     *  @param colors 
     */
    public void setColors(ArrayList<Color> colors) {
        SierpinskiTriangle.colors = colors;
    }
    
    /** Returns the actual SVGraphics item that is the Sierpinski triangle
     */
    public SVCustom build(){
        if(this.disks < 1) return new SVCustom();
        SVCustom s = new SVCustom(new Point(100, (int)Math.pow(2, this.disks)*104 - 52));
        int left[] = new int[]{0,1};
        int right[] = new int[]{1,2};
        int bottom[] = new int[]{0,2};
        SierpinskiTriangle t = new SierpinskiTriangle(0,this.disks,this.disks-1, left,  right, bottom, buildInitial(this.disks));
        s.setAttribute("font-family", "Arial");
        s.addContent(t, "Triangle");
        return s;
    }
    
    /** Builds the arraylist of arraylist integers that represent the current state of the game.
     */
    public static ArrayList<ArrayList<Integer>> buildInitial(int disks) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        a.add(new ArrayList<Integer>());
        a.add(new ArrayList<Integer>());
        a.add(new ArrayList<Integer>());
        for(int i = 0; disks > i; i++) a.get(0).add(0,new Integer(i));
        return a;
    }
    
    /** Creates definition elements for disks once here
     */
    private void createDiskDefinitions() {
        int sizeFactor;
        for(int i = 0; this.disks > i; i++) {
            SVCustom d = new SVCustom();
            d.setId("disk" + i);
            sizeFactor = 10*(i+1)/this.disks;
            SVEllipse e = new SVEllipse(new Point(0,0),20 + sizeFactor,14);
            SVEllipse esmall = new SVEllipse(new Point(0,0),11,13);
            SVText t = new SVText(new Point(0,5),""+i);
            esmall.setColor(Color.white);
            e.setColor(SierpinskiTriangle.colors.get(i));
            esmall.setBorderWidth(1);
            esmall.setBorderColor(Color.lightGray);
            
        t.setAttribute("text-anchor", "middle");
        t.setAttribute("font-size", "13");
        t.setAttribute("font-weight", "bold");
        
            d.addContent(e, "e"+i);
            d.addContent(esmall, "esmall"+i);
            d.addContent(t,"t"+i);
            this.defintions.addContent(d, "disk"+i);
        }
    }
    
    /** Creates tower element once here
     */
    private void createTowerDefinition() {
        SVCustom t = new SVCustom();
        t.setId("tower");
        SVRectangle background = new SVRectangle(new Point(-51,-67),102,79);
        background.setBorderRadius(4);
        background.setColor(Color.lightGray);
        background.setOpacity(0.15);
        SVRectangle background2 = new SVRectangle(new Point(-48,-65),96,75);
        background2.setBorderRadius(4);
        background2.setColor(Color.white);
        background2.setOpacity(0.5);
        SVRectangle base = new SVRectangle(new Point(-45,-5),90,10);
        base.setBorderRadius(3);
        base.setColor(Color.black);
        SVRectangle p1 = new SVRectangle(new Point(-33,-60),6,60);
        p1.setBorderRadius(3);
        p1.setColor(Color.black);
        SVRectangle p2 = new SVRectangle(new Point(-3,-60),6,60);
        p2.setBorderRadius(3);
        p2.setColor(Color.black);
        SVRectangle p3 = new SVRectangle(new Point(27,-60),6,60);
        p3.setBorderRadius(3);
        p3.setColor(Color.black);
        t.addContent(background, "background");
        t.addContent(background2, "background2");
        t.addContent(p1,"p1");
        t.addContent(p2,"p2");
        t.addContent(p3,"p3");
        t.addContent(base,"base");
        this.defintions.addContent(t, "tower");
    }
    
    public void save(String file) {
        SVCanvas c = new SVCanvas();
        this.createDiskDefinitions();
        this.createTowerDefinition();
        c.addXMLContent(this.defintions);
        c.addXMLContent(this.build());
        c.saveTo(file);
    }
}
