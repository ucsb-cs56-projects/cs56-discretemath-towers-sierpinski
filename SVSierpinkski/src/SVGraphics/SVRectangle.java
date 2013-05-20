package SVGraphics;

import java.awt.Color;
import java.awt.Point;

/**
 * SVRectangle class extends SVDefault and represents rectangle element
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */

public class SVRectangle extends SVDefault {
    
    /** no-arg constructor calls super with rectangle tag name
     */
    public SVRectangle() {
        super("rectangle");
    }
    
    /** one-arg constructor calls super with rectangle tag name and location
        @param location 
     */
    public SVRectangle(Point location) {
        super("rectangle",location);
    }
    
    /** two-arg constructor calls super with rectangle tag name, location,
        and width and height int values
        @param location 
        @param width  
        @param height 
     */
    public SVRectangle(Point location, int width, int height) {
        super("rectangle",location);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    /** Returns rectangle height
        @return height of rectangle
     */
    public int getHeight() {
        String s = this.getAttribute("height");
        if(s == null) return 0; else return Integer.parseInt(s);
    }
    
    /** Sets rectangle height
        @param height
     */
    public void setHeight(int height) {
        this.setAttribute("height", height + "");
    }
    
    /** Returns rectangle height
        @return height of rectangle
     */
    public int getWidth() {
        String s = this.getAttribute("width");
        if(s == null) return 0; else return Integer.parseInt(s);
    }
    
    /** Sets rectangle height
        @param height
     */
    public void setWidth(int width) {
        this.setAttribute("width", width + "");
    }
    
    /** Returns rectangle border radius
        @return int border radius
     */
    public int getBorderRadius() {
        String s = this.getAttribute("rx");
        if(s == null) return 0; else return Integer.parseInt(s);
    }
    
    /** Sets rectangle border radius
        @param int radius
     */
    public void setBorderRadius(int radius) {
        this.setAttribute("rx", radius + "");
        this.setAttribute("ry", radius + "");
    }
    
}
