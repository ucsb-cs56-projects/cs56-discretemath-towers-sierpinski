package SierpinskiTriangle;

import SVGraphics.*;
import static java.lang.Math.pow;
import java.awt.Color;
import java.awt.Point;

/**
 * SierpinskiTriangle class extends SVCustom for building Sierpinkski Triangle 
 * representing Towers of Hanoi moves
 *
 * @author Martin Wolfenbarger
 * @version 2013/05/16 
 */
public class SierpinskiTriangle extends SVCustom{
    private int horizontal = 120;
    private int vertical = 104;
    private int disks;
    private int left[];
    private int right[];
    private int bottom[];
    private int iteration;
    private Color colors[] = new Color[]{Color.pink, Color.BLUE, Color.red, Color.gray};
    
    /** no-arg constructor calls super, sets tag to g, and sets location
     */
    public SierpinskiTriangle(int disks, int iteration, int left[], int right[], int bottom[]) {
        super();
        this.disks = disks;
        this.iteration = iteration;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.buildBottomSide();
        this.buildRightSide();
        this.buildLeftSide();
        if(this.iteration > 0) {
            this.buildLeftTriangle();
            this.buildTopTriangle();
            this.buildRightTriangle();
        }
        
    }
    
    public static int[] flip(int a[]) {
        return new int[]{a[1],a[0]};
    }
    
    public void buildLeftTriangle() {
        SierpinskiTriangle t = new SierpinskiTriangle(this.disks, this.iteration-1, this.bottom, flip(right), this.left);
        t.setLocation(new Point(0,0));
        addContent(t,"left-triangle");
    }
    
    public void buildRightTriangle() {
        Point bottom = this.getBottomSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(this.disks, this.iteration-1, flip(this.left), this.bottom, this.right);
        t.setLocation(new Point(bottom.x+this.horizontal/2,0));
        addContent(t,"right-triangle");
    }
    
    public void buildTopTriangle() {
        Point left = this.getLeftSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(this.disks, this.iteration-1, flip(this.right), flip(this.left), flip(this.bottom));
        t.setLocation(new Point(left.x+this.horizontal/4,left.y - this.vertical/2));
        addContent(t,"top-triangle");
    }
    
    public void buildLeftSide() {
        TriangleSide t = new TriangleSide(colors[this.iteration], this.iteration + " " + this.left[0] + "->" + this.left[1]);
        t.setLocation(this.getLeftSideCoords());
        t.setRotation(-60);
        addContent(t, "left-side");
    }
    
    public void buildRightSide() {
        TriangleSide t = new TriangleSide(colors[this.iteration], this.iteration + " " + this.right[0] + "->" + this.right[1]);
        t.setLocation(this.getRightSideCoords());
        t.setRotation(60);
        addContent(t, "right-side");
    }
    
    public void buildBottomSide() {
        TriangleSide t = new TriangleSide(colors[this.iteration], this.iteration + " " + this.bottom[0] + "->" + this.bottom[1]);
        t.setLocation(this.getBottomSideCoords());
        addContent(t, "bottom-side");
    }
    
    public Point getLeftSideCoords() {
        Point bottom = this.getBottomSideCoords();
        return new Point(bottom.x/2, 
                -1*(this.calculateFormula()*this.vertical)-this.vertical/2);
    }
    
    public Point getRightSideCoords() {
        Point left = this.getLeftSideCoords();
        return new Point(left.x*3, left.y);
    }
    
    public Point getBottomSideCoords() {
        return new Point(this.calculateFormula()*this.horizontal + this.horizontal/2, 0);
    }
    
    public int calculateFormula(){
        return (int)Math.pow(2, this.iteration) - 1; 
    }
}
