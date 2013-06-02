package edu.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.awt.Point;
import org.w3c.dom.Element;
import java.awt.Color;

/**
 * SVText class extends SVDefault and represents text element
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */

public class SVText extends SVDefault {
    /** Content that will be inside text element
     */
    private String content;
    
    /** no-arg constructor calls super with text tag name.
       Sets default color to black
     */
    public SVText() {
        super("text");
        this.setColor(Color.black);
    }
    
    /** one-arg constructor calls super with text tag name, location, and content
        @param location
        @param content
     */
    public SVText(Point location, String content) {
        super("text",location);
        this.setColor(Color.black);
        this.setContent(content);
    }
    
    /** Returns text's content
        @return string content
     */
    public String getContent() {
        return this.content;
    }
    
    /** Sets text's content
        @param content String
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /** calls super appendXML but also adds text content
       @param e parent xml element
     */
    public void addAttributes(Element e) {
        super.addAttributes(e);
        e.setTextContent(getContent());
    }
}
