package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.util.Map;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Point;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * SVGraphics class for building SVG elements
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */

public class SVGraphics {
    /** Tag instance variable defines the type of element such as <svg> or <g>
     */
    private String tag;
    
    /** Holds attribute information for each element.
     */
    private Map<String, String> attributes;
    
    /** Holds transform attributes. Given its own
        variable rather than part of attribute Map because it can hold 
        multiple values such as scale, location, etc.
     */
    private Map<String, Object> transform;
    
    /** no-arg constructor returns a graphics element with location at (0,0),
         tag "svg", initialized attributes and transform maps.
     */
    public SVGraphics() {
        this.attributes = new HashMap<String, String>();
        this.transform = new HashMap<String, Object>();
        this.setLocation(new Point(0,0));
    }
    
    /** Returns element tag
        @return String tag
     */
    public String getTag() {
        return this.tag;
    }
    
    /** Returns attribute given key and null if key corresponds to nothing
        @param key String value for key
        @return value corresponding to key or null if DNE
     */
    public String getAttribute(String key) {
        return this.attributes.get(key);
    }
    
    /** Returns attribute given key and null if key corresponds to nothing
        @param key String value for key
        @return value corresponding to key or null if DNE
     */
    public Object getTransform(String key) {
        return this.transform.get(key);
    }
    
    /** Returns location of element
        @return Point(x coords, y coords)
     */
    public Point getLocation() {
        Point p = (Point)this.getTransform("translate");
        if(p == null) return new Point(0,0); else return p;
    }
    
    /** Returns rotation of element
        @return Integer rotation in degrees
     */
    public Integer getRotation() {
        Integer rotation = (Integer)this.getTransform("rotate");
        if(rotation == null) return new Integer(0); else return rotation;
    }
    
    /** Returns opacity of element
        @return Double typically from 0.0 to 1.0
     */
    public Double getOpacity() {
        Double opacity = Double.parseDouble(this.getAttribute("opacity"));
        if(opacity == null) return new Double(1.0); else return opacity;
    }
    
    /** Returns id
        @return String 
     */
    public String getId() {
        return this.getAttribute("id");
    }
    
    /** sets id
        @param id String 
     */
    public void setId(String id) {
        this.setAttribute("id", id);
    }
    
    /** Sets the tag variable
        @param tag name of tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    /** Sets attribute given key and value
        @param key String value for key
        @param value String value corresponding to key
     */
    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }
    
    /** Sets transform attribute given key and value
        @param key String value for key
        @param value Object value corresponding to key
     */
    public void setTransform(String key, Object value) {
        this.transform.put(key, value);
    }
    
    /** Sets location of element using transform attributes
        @param p Point(x coords, y coords)
     */
    public void setLocation(Point p) {
        this.setTransform("translate", p);
    }
    
    /** Sets rotation of element using transform attributes
        @param Integer value corresponding to rotation degree
     */
    public void setRotation(Integer rotation) {
        this.setTransform("rotate", rotation);
    }
    
    /** Sets opacity of element 
        @param double value of opacity
     */
    public void setOpacity(double opacity) {
        if(opacity != 1.0) this.setAttribute("opacity", "" + opacity);
        else this.removeTransform("opacity");
    }
    
    /** Returns transform attributes string
        @return string corresponding to value of transform attribute
     */
    public String getTransformAttributes() {
        String str = "";
        Point location = this.getLocation();
        Integer rotation = this.getRotation();
        String s = this.getAttribute("scale");
        if(!location.equals(new Point(0,0))) str += "translate(" + location.x + "," + location.y + ") ";
        if(rotation != 0) str += "rotate(" + rotation + ") ";
        if(s != null) str+= "scale(" + s + ") ";
        return str;
    }
    
    
    /** Add attributes to this element's xml tag
        @param current element's xml tag
     */
    public void addAttributes(Element current) {
        String transform = this.getTransformAttributes();
        if(!transform.equals("")) current.setAttribute("transform", this.getTransformAttributes());
        for (Map.Entry<String, String> entry : this.attributes.entrySet())
            current.setAttribute(entry.getKey(), entry.getValue());
    }
    
    
    /** adds this element's data as xml element to a parent element
       @param e parent xml element
     */
    public void appendXMLTo(Element e) {
        Element current = e.getOwnerDocument().createElement(this.getTag());
        this.addAttributes(current);
        e.appendChild(current);
    }
    
    /** Removes attribute given key
        @param key String value for key
     */
    public String removeAttribute(String key) {
        return this.attributes.remove(key);
    }
    
    /** Removes transform attribute given key
        @param key String value for key
     */
    public void removeTransform(String key) {
        this.transform.remove(key);
    }
    
}
