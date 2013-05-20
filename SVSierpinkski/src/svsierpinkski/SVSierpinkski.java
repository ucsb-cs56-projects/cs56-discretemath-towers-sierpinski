package svsierpinkski;

import SVGraphics.*;
import java.awt.Color;
import java.awt.Point;

/**
 * SVSierpinski class uses SVGraphics package to draw the Sierpinkski triangle 
 * representing each possible move in the Towers of Hanoi game.
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SVSierpinkski {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SVCustom s = new SVCustom(new Point(100,100));
        s.addContent(new SVText(new Point(0,0), "kakkakaka"), "text");
        SVCanvas c = new SVCanvas();
        c.addXMLContent(s);
        c.saveTo("/Users/martinwolfenbarger/Desktop/test.svg");
    }
}
