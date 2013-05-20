package SierpinskiTriangle;
import SVGraphics.*;
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
        this.setAttribute("text-anchor", "middle");
        this.setAttribute("font-size", "14");
        this.setAttribute("font-weight", "bold");
        int total = a.get(0).size() + a.get(1).size() + a.get(2).size();
        SVRectangle base = new SVRectangle(new Point(-45,-5),90,10);
        base.setBorderRadius(3);
        base.setColor(Color.black);
        Peg p1 = new Peg(a.get(0),total);
        p1.setLocation(new Point(-30,-80));
        Peg p2 = new Peg(a.get(1),total);
        p2.setLocation(new Point(0,-80));
        Peg p3 = new Peg(a.get(2),total);
        p3.setLocation(new Point(30,-80));
        addContent(p1,"p1");
        addContent(p2,"p2");
        addContent(p3,"p3");
        addContent(base,"base");
    }
}
