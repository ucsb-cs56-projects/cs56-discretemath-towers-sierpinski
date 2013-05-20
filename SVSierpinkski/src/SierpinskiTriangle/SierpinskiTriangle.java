package SierpinskiTriangle;

import SVGraphics.*;
import static java.lang.Math.pow;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

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
    private ArrayList<ArrayList<Integer>> diskArray;
    private int left[];
    private int right[];
    private int bottom[];
    private int iteration;
    private int id;
    private Color colors[] = new Color[]{Color.pink, Color.BLUE, Color.red, Color.gray};
    
    /** no-arg constructor calls super, sets tag to g, and sets location
     */
    public SierpinskiTriangle(int id, int disks, int iteration, int left[], int right[], int bottom[], ArrayList<ArrayList<Integer>> a) {
        super();
        this.id = id;
        this.disks = disks;
        this.diskArray = a;
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
        }else{
            this.buildLeftTower();
            this.buildTopTower();
            this.buildRightTower();
        }
    }
    
    public static int getDiskLocation(ArrayList<ArrayList<Integer>> a, int base) {
        int location = -1;
        for(int i = 0; 3 > i; i++) 
            for(int j = 0; a.get(i).size() > j; j++)
                if(a.get(i).get(j) == base) { location = i; break; }
        
        return location;
    }
    
    public static ArrayList<ArrayList<Integer>> carryDisks(ArrayList<ArrayList<Integer>> a, int base, int to) {
        int from = getDiskLocation(a,base);
        System.out.println(from+" "+base + "  " + a.toString());
        if(from == to) return a;
        for(int i = 0; a.get(from).size() > i; i++) if(a.get(from).get(i) <= base) a.get(to).add(a.get(from).get(i));
        for(int i = a.get(from).size()-1; i >= 0; i--) if(a.get(from).get(i) <= base) a.get(from).remove(i);
        return a;
    }
    
    public void buildLeftTower() {
        Tower t = new Tower(carryDisks(this.diskArray, this.iteration, this.left[0]));
        t.setLocation(new Point(0,20));
        addContent(t,"left-tower");
    }
    
    public void buildRightTower() {
        Tower t = new Tower(carryDisks(this.diskArray, this.iteration, this.right[1]));
        addContent(t,"right-tower");
        t.setLocation(new Point(120,20));
    }
    
    public void buildTopTower() {
        Tower t = new Tower(carryDisks(this.diskArray, this.iteration, this.left[1]));
        addContent(t,"top-tower");
        t.setLocation(new Point(60,-95));
    }
    
    public static int[] flip(int a[]) {
        return new int[]{a[1],a[0]};
    }
    
    public void buildLeftTriangle() {
        
        SierpinskiTriangle t = new SierpinskiTriangle(0,this.disks, this.iteration-1, this.bottom, flip(right), 
                this.left, carryDisks(this.diskArray, this.iteration, this.left[0]));
        t.setLocation(new Point(0,0));
        addContent(t,"left-triangle");
    }
    
    public void buildRightTriangle() {
        Point bottom = this.getBottomSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(2,this.disks, this.iteration-1, flip(this.left), this.bottom, 
                this.right, carryDisks(this.diskArray, this.iteration, this.right[1]));
        t.setLocation(new Point(bottom.x+this.horizontal/2,0));
        addContent(t,"right-triangle");
    }
    
    public void buildTopTriangle() {
        Point left = this.getLeftSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(1,this.disks, this.iteration-1, flip(this.right), flip(this.left), 
                flip(this.bottom), carryDisks(this.diskArray, this.iteration, this.left[1]));
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
