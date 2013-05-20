package SierpinskiTriangle;
import SVGraphics.*;
import java.awt.Color;
import java.awt.Point;
/**
 *
 * @author martinwolfenbarger
 */
public class Tower extends SVCustom{
    
    public Tower(int left[], int middle[], int right[]) {
        super();
        setAttribute("scale", ".6");
        this.setAttribute("text-anchor", "middle");
        this.setAttribute("font-size", "14");
        this.setAttribute("font-weight", "bold");
        int total = left.length + middle.length + right.length;
        SVRectangle base = new SVRectangle(new Point(-45,-5),90,10);
        base.setBorderRadius(3);
        base.setColor(Color.black);
        Peg p1 = new Peg(left,total);
        p1.setLocation(new Point(-30,-80));
        Peg p2 = new Peg(middle,total);
        p2.setLocation(new Point(0,-80));
        Peg p3 = new Peg(right,total);
        p3.setLocation(new Point(30,-80));
        addContent(p1,"p1");
        addContent(p2,"p2");
        addContent(p3,"p3");
        addContent(base,"base");
    }
}
