package SierpinskiTriangle;

import SVGraphics.*;
import java.awt.Point;
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
    public SierpinskiTriangleBuilder(int disks) {
        this.disks = disks;
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
}
