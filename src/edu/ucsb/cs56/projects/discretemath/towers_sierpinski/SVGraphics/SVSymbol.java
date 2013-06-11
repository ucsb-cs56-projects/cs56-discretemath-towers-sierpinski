package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SVSymbol class extends SVCustom is a definition inside <defs> tag and has <symbol> tag
 * allowing for image and element reuse and viewBox use
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/31
 */
public class SVSymbol extends SVCustom{
    /** Holds graphics content with corresponding keys
     */
    private Map<String, SVGraphics> content = new LinkedHashMap<String, SVGraphics>();
    
    /** no-arg constructor calls super and sets tag to symbol
     */
    public SVSymbol(String id) {
        super();
        setTag("symbol");
        setId(id);
    }
    
    /** no-arg constructor calls super, sets tag to symbol, and sets location
     */
    public SVSymbol(String id, Point location) {
        super();
        setLocation(location);
        setTag("symbol");
        setId(id);
    }
}
