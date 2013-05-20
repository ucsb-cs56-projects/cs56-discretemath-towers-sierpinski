package svsierpinski;

import SVGraphics.*;
import SierpinskiTriangle.*;
import java.awt.Color;
import java.awt.Point;
import java.math.*;

/**
 * SVSierpinski class uses SVGraphics package to draw the Sierpinski triangle 
 * representing each possible move in the Towers of Hanoi game.
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SVSierpinski {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SierpinskiTriangleBuilder builder = new SierpinskiTriangleBuilder(4);
        SVCanvas c = new SVCanvas();
        c.addXMLContent(builder.build());
        //c.saveTo("/Users/martinwolfenbarger/Desktop/test.svg");
    }
}
