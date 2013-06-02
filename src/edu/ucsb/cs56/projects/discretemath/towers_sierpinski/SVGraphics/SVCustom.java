package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import org.w3c.dom.Element;

/**
 * SVCustom class extends SVGraphics allowing customized SVG content 
 * using g tag
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SVCustom extends SVGraphics{
    
    /** Holds graphics content with corresponding keys
     */
    private Map<String, SVGraphics> content = new LinkedHashMap<String, SVGraphics>();
    
    /** no-arg constructor calls super and sets tag to g
     */
    public SVCustom() {
        super();
        setTag("g");
    }
    
    /** no-arg constructor calls super, sets tag to g, and sets location
     */
    public SVCustom(Point location) {
        super();
        setLocation(location);
        setTag("g");
    }
    
    /** calls super and adds all of this element's sub xml elements
       @param e parent xml element
     */
    public void addAttributes(Element e) {
        super.addAttributes(e);
        for (Map.Entry<String, SVGraphics> entry : this.content.entrySet())
            entry.getValue().appendXMLTo(e);
    }
    
    
    /** adds graphic to content map with given key
       @param g graphic element
       @param id key for graphic element
     */
    public void addContent(SVGraphics g, String id) {
        this.content.put(id, g);
    }
    
    /** returns element from content map
       @param id key for graphic element
     */
    public void getContentWithId(String id) {
        this.content.get(id);
    }
    
}
