package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;
import edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics.*;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Tower class extends SVCustom and creates a visual representation of 
 * the current state of the Towers of Hanoi game
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class Tower extends SVCustom{
    
    public Tower(ArrayList<ArrayList<Integer>> a) {
        super();
        setAttribute("scale", ".6");
        addContent(new SVUse("tower"),"tower");
        for(int j = 0; 3 > j; j++) 
            for(int i = 0; a.get(j).size() > i; i++) 
                addContent(new SVUse("disk"+a.get(j).get(i), new Point(-30+j*30,-1*i*13-12)), "disk"+a.get(j).get(i));
    }
}
