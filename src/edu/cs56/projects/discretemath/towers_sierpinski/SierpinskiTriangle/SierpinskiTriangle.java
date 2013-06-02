package edu.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;

import edu.cs56.projects.discretemath.towers_sierpinski.SVGraphics.*;
import static java.lang.Math.pow;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * SierpinskiTriangle class extends SVCustom for building Sierpinski Triangle 
 * representing Towers of Hanoi moves.
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
    private int id;
    private int iteration;
    public static ArrayList<Color> colors = new ArrayList<Color>();
    
    /** Takes two arguments: # of disks and file path. Creates svg image of triangle 
     *  and saves the content to given file path. Option additional parameters are 
     *  the hexadecimal value of the colors 
     * 
     *  Sample: 
     *              3 /Desktop/test.svg B8860B 4169E1 228B22
     */
    public static void main(String[] args) {
        try {
            int argLength = args.length;
            if(argLength < 2) throw new Exception("Requires at least two arguments: number_of_disks file_path");
            int length = Integer.parseInt(args[0]);
            SierpinskiTriangleBuilder builder = new SierpinskiTriangleBuilder(length);
            ArrayList<Color> colors = new ArrayList<Color>();
            for(int i = 2; argLength > i; i++) colors.add(Color.decode("0x" + args[i]));
            builder.setColors(colors);
            builder.save(args[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    /** 7-arg constructor. The logic can be greatly simplified as many of the vars passed are
       unnecessary information. The state of the game and the exact location of each disk 
       can be provided by a simple formula. For larger numbers of disks, this is necessary.
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
    
    /** Determines which peg the disk is located on.
     */
    public static int getDiskLocation(ArrayList<ArrayList<Integer>> a, int base) {
        int location = -1;
        for(int i = 0; 3 > i; i++) 
            for(int j = 0; a.get(i).size() > j; j++)
                if(a.get(i).get(j) == base) { location = i; break; }
        
        return location;
    }
    
    /** Simulates moving a disk, but on larger levels where multiple disks are moved as one.
     */
    public static ArrayList<ArrayList<Integer>> carryDisks(ArrayList<ArrayList<Integer>> a, int base, int to) {
        int from = getDiskLocation(a,base);
        if(from == to) return a;
        for(int i = 0; a.get(from).size() > i; i++) if(a.get(from).get(i) <= base) a.get(to).add(a.get(from).get(i));
        for(int i = a.get(from).size()-1; i >= 0; i--) if(a.get(from).get(i) <= base) a.get(from).remove(i);
        return a;
    }
    
    /** Determines create the visual representation of this current state.
     */
    public void buildLeftTower() {
        Tower t = new Tower(carryDisks(this.diskArray, this.iteration, this.left[0]));
        t.setLocation(new Point(0,20));
        addContent(t,"left-tower");
    }
    
    /** Determines create the visual representation of this current state.
     */
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
    
    /** Determines create the visual representation of this current state.
     */
    public static int[] flip(int a[]) {
        return new int[]{a[1],a[0]};
    }
    
    
    /** Creates a smaller triangle case
     */
    public void buildLeftTriangle() {
        
        SierpinskiTriangle t = new SierpinskiTriangle(0,this.disks, this.iteration-1, this.bottom, flip(right), 
                this.left, carryDisks(this.diskArray, this.iteration, this.left[0]));
        t.setLocation(new Point(0,0));
        addContent(t,"left-triangle");
    }
    
    /** Creates a smaller triangle case
     */
    public void buildRightTriangle() {
        Point bottom = this.getBottomSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(2,this.disks, this.iteration-1, flip(this.left), this.bottom, 
                this.right, carryDisks(this.diskArray, this.iteration, this.right[1]));
        t.setLocation(new Point(bottom.x+this.horizontal/2,0));
        addContent(t,"right-triangle");
    }
    
    /** Creates a smaller triangle case
     */
    public void buildTopTriangle() {
        Point left = this.getLeftSideCoords();
        SierpinskiTriangle t = new SierpinskiTriangle(1,this.disks, this.iteration-1, flip(this.right), flip(this.left), 
                flip(this.bottom), carryDisks(this.diskArray, this.iteration, this.left[1]));
        t.setLocation(new Point(left.x+this.horizontal/4,left.y - this.vertical/2));
        addContent(t,"top-triangle");
    }
    
    /** Creates a side of the current triangle
     */
    public void buildLeftSide() {
        TriangleSide t = new TriangleSide(this.getCurrentColor(), this.iteration + " " + this.left[0] + "->" + this.left[1]);
        t.setLocation(this.getLeftSideCoords());
        t.setRotation(-60);
        addContent(t, "left-side");
    }
    
    /** Creates a side of the current triangle
     */
    public void buildRightSide() {
        TriangleSide t = new TriangleSide(this.getCurrentColor(), this.iteration + " " + this.right[0] + "->" + this.right[1]);
        t.setLocation(this.getRightSideCoords());
        t.setRotation(60);
        addContent(t, "right-side");
    }
    
    /** Creates a side of the current triangle
     */
    public void buildBottomSide() {
        TriangleSide t = new TriangleSide(this.getCurrentColor(), this.iteration + " " + this.bottom[0] + "->" + this.bottom[1]);
        t.setLocation(this.getBottomSideCoords());
        addContent(t, "bottom-side");
    }
    
    /** Returns coordinates of where this side should on the current triangle
     */
    public Point getLeftSideCoords() {
        Point bottom = this.getBottomSideCoords();
        return new Point(bottom.x/2, 
                -1*(this.calculateFormula()*this.vertical)-this.vertical/2);
    }
    
    /** Returns coordinates of where this side should on the current triangle
     */
    public Point getRightSideCoords() {
        Point left = this.getLeftSideCoords();
        return new Point(left.x*3, left.y);
    }
    
    /** Returns coordinates of where this side should on the current triangle
     */
    public Point getBottomSideCoords() {
        return new Point(this.calculateFormula()*this.horizontal + this.horizontal/2, 0);
    }
    
    /** Calculates and returns 2^n - 1 where n is the current # of disks being handled
     */
    public int calculateFormula(){
        return (int)Math.pow(2, this.iteration) - 1; 
    }
    
    private Color getCurrentColor() {
        try {
            return colors.get(this.iteration);
        } catch (IndexOutOfBoundsException iobe) {
            return Color.BLACK;
        }
    }
}
