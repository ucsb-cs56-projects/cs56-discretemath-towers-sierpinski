/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SierpinskiTriangle;

import SVGraphics.*;
import SVGraphics.SVRectangle;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author martinwolfenbarger
 */
public class Peg extends SVCustom{
    public Peg(int disks[], int total) {
        super();
        SVRectangle r = new SVRectangle(new Point(-3,20),6,60);
        r.setBorderRadius(3);
        r.setColor(Color.black);
        addContent(r, "a");
        if(total > 0) this.addDisks(disks, total);
    }
    private void addDisks(int disks[], int total) {
        for(int i = 0; disks.length > i; i++) {
            SVEllipse e = new SVEllipse(new Point(0,-1*i*12+69),30-i*3,13);
            SVText t = new SVText(new Point(0,-1*i*12+74),""+disks[i]);
            e.setColor(Color.CYAN);
            addContent(e, "e"+i);
            addContent(t,"t"+i);
        }
    }
}
