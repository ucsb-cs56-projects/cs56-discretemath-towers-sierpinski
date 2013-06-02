package edu.cs56.projects.discretemath.towers_sierpinski.SVGraphics;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * SVCanvas class for holding SVG element data and saving to file.
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SVCanvas {
    /**
       xml document variable
     */
    private Document document;
    
    /**
       root svg tag
     */
    private Element root;
    
    /**
       no-arg constructor returns a canvas for svg drawing / saving 
       with root svg element
     */
    public SVCanvas() {
        try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		this.document = docBuilder.newDocument();
		this.root = this.document.createElement("svg");
                this.root.setAttribute("xmlns", "http://www.w3.org/2000/svg");
                this.root.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
		this.document.appendChild(this.root);
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  }
    } 
   
    /**
       Saves all xml data to given file
       @param file string name of file path
     */
    public void saveTo(String file) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(this.document);
            StreamResult result = new StreamResult(new File(file));
            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    
    public void addXMLContent(SVGraphics g) {
        g.appendXMLTo(this.root);
    }
    
}
