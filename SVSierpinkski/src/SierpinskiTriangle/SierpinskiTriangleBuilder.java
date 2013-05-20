package SierpinskiTriangle;

import SVGraphics.*;
import java.awt.Point;

/**
 * SierpinskiTriangleBuilder class used with SierpinskiTriangle class to build 
 * Towers of Hanoi representation
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SierpinskiTriangleBuilder {
    private int disks;
    public SierpinskiTriangleBuilder(int disks) {
        this.disks = disks;
    }
    
    public SVCustom build(){
        SVCustom s = new SVCustom(new Point(0, (int)Math.pow(2, this.disks)*104 - 104));
        int left[] = new int[]{0,1};
        int right[] = new int[]{1,2};
        int bottom[] = new int[]{0,2};
        SierpinskiTriangle t = new SierpinskiTriangle(this.disks,this.disks-1, left,  right, bottom);
        s.setAttribute("font-family", "Arial");
        s.addContent(t, "Triangle");
        return s;
    }
}
