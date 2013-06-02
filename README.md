cs56-discretemath-towers-sierpinski
===================================

Java software to explore the relationship between the Towers of Hanoi and the Sierpinski Triangle


# Explanation

* The Sierpsinki Triangle is a recursive, fractal design.  
* It can be produced by removing all "even numbers" from Pascal's triangle where the number of rows is a power of 2, and connecting all adjacent odd numbers
* The legal moves in the Towers of Hanoi with n disks correspond to the Sierpsinski triangle that results from this transformation of Pascal's Triangle with 2^n rows.

See for example:

http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi2DiskTransitionsTowers.png

http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi3DiskTransitions.png

The purpose of this project is to explore this relationship in Software in various ways.

Goal 1:  Write a Java class with a method that

* n, the number of disks
* colors, a java.util.ArrayList<java.awt.Color> of size n
* width  width and height in pixels

Will draw on the screen in a Swing gui a representation of diagrams similar to those shown in the linnks above (which were hand drawn.)

~estimate: 360 

For now, these can be hard coded, though they should be in one place in the code so they can be easily changed:

* edgefont    a font specification for the edge labels
* diskfont    a font specification for the disk labels


(2) Add capabiltiy to save image to .png or .jpg

~estimate: 120


(3) Add widgets to allow:

* the width and height of the Jframe to by dynamically changed
* the font size for edge and disk labels to be selected from a menu

~estimate: 120


(4) Use a "properties" file to allow the user to specify the colors for the disks by writing hex colors into the properties file.

Note: a "properties" file is a standard Java "thing"---use that interface.

~estimate: 120


(5) Allow user to select colors in the GUI from a color picker, and then write those values back to the properties file.

~estimate: 120
