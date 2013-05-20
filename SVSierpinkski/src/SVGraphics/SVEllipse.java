package SVGraphics;

import java.awt.Color;
import java.awt.Point;

/**
 * SVEllipse class extends SVDefault and represents ellipse element
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */

public class SVEllipse extends SVDefault {
    
    /** no-arg constructor calls super with ellipse tag name
     */
    public SVEllipse() {
        super("ellipse");
    }
    
    /** one-arg constructor calls super with ellipse tag name and location
        @param location 
     */
    public SVEllipse(Point location) {
        super("ellipse",location);
    }
    
    /** two-arg constructor calls super with ellipse tag name, location,
        and width and height int values
        @param location 
        @param width  
        @param height 
     */
    public SVEllipse(Point location, int width, int height) {
        super("ellipse",location);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    /** Returns ellipse height
        @return height of ellipse
     */
    public int getHeight() {
        String s = this.getAttribute("ry");
        if(s == null) return 0; else return Integer.parseInt(s)*2;
    }
    
    /** Sets ellipse height
        @param height
     */
    public void setHeight(int height) {
        this.setAttribute("ry", height/2 + "");
    }
    
    /** Returns ellipse height
        @return height of ellipse
     */
    public int getWidth() {
        String s = this.getAttribute("rx");
        if(s == null) return 0; else return Integer.parseInt(s)*2;
    }
    
    /** Sets ellipse height
        @param height
     */
    public void setWidth(int width) {
        this.setAttribute("rx", width/2 + "");
    }
    
}
