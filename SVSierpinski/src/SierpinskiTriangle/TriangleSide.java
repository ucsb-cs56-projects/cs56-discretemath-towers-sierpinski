package SierpinskiTriangle;

import SVGraphics.*;
import java.awt.Color;
import java.awt.Point;


/**
 * TriangleSide class extends SVCustom. Used by SierpinskiTriangle class.
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class TriangleSide extends SVCustom{
    
    public TriangleSide(Color c, String text) {
        super();
        SVRectangle s = new SVRectangle(new Point(-60,-5),120,10);
        s.setBorderRadius(3);
        s.setColor(c);
        SVText t = new SVText(new Point(0, -8),text);
        t.setAttribute("text-anchor","middle");
        t.setAttribute("font-size", "14");
        t.setAttribute("font-weight", "bold");
        addContent(s, "rectangle");
        addContent(t, "text");
    }
}
