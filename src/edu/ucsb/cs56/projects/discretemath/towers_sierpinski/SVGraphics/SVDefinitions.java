package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SVDefinition class extends SVCustom contains definitions in <defs> tag 
 * allowing for image and element reuse
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/31
 */
public class SVDefinitions extends SVCustom{
    /** Holds graphics content with corresponding keys
     */
    private Map<String, SVGraphics> content = new LinkedHashMap<String, SVGraphics>();
    
    /** no-arg constructor calls super and sets tag to g
     */
    public SVDefinitions() {
        super();
        setTag("defs");
    }
    
    /** no-arg constructor calls super, sets tag to g, and sets location
     */
    public SVDefinitions(Point location) {
        super();
        setLocation(location);
        setTag("defs");
    }
}
