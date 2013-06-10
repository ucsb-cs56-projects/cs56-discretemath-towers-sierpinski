package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.awt.Color;
import java.awt.Point;

/**
 * SVDefault abstract class for default SVG elements
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
abstract class SVDefault extends SVGraphics{
    
    /** 1-arg super constructor called in addition to setting tag value to
       given parameter
       @param tag string value of tag type
     */
    public SVDefault(String tag) {
        super();
        setTag(tag);
    }
    
    /** 2-arg super constructor called in addition to setting tag value to
       given parameter and location
       @param tag string value of tag type
     */
    public SVDefault(String tag, Point location) {
        super();
        setTag(tag);
        setLocation(location);
    }
    
    /** Returns svg color attribute
        @return String value of this attribute, null if DNE
     */
    public String getColor() {
        return this.getAttribute("fill");
    }
    
    /** Returns svg border color attribute
        @return String value of this attribute, null if DNE
     */
    public String getBorderColor() {
        return this.getAttribute("stroke");
    }
    
    /** Returns int value of border width
        @return int representing border width, 0 if DNE
     */
    public int getBorderWidth() {
        String s = this.getAttribute("stroke-width");
        if(s == null) return 0; else return Integer.parseInt(s);
    }
    
    /** sets fill color corresponding to this element
       @param c java color
     */
    public void setColor(Color c) {
        if(c != null) this.setAttribute("fill", "rgb("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")");
        else this.removeAttribute("fill");
    }
    
    /** sets border color corresponding to this element
       @param c java color
     */
    public void setBorderColor(Color c) {
        if(c != null) this.setAttribute("stroke", "rgb("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")");
        else this.removeAttribute("stroke");
    }
    
    /** sets border width
       @param width int
     */
    public void setBorderWidth(int width) {
        this.setAttribute("stroke-width", width+"");
    }
}
