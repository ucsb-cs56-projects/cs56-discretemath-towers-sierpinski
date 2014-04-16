cs56-discretemath-towers-sierpinski
===================================

project history
===============
```
 W14 | bkiefer13 5pm | bzimm | Graphical hangman game
```

## High-level Description (User's View)
In the current state, this project is able to launch a Pascal Triangle GUI, a Sierpinski Triangle / Tower of Hanoi GUI, and generate a XML file which can be opened in the browser to show another Sierpinski Triangle / Tower of Hanoi. This project is meant to be Java software that explores the relationships among the Towers of Hanoi, Sierpinski's Triangle, and Pascal's Triangle. An issue that still needs to be done is creating a GUI for the XML file. A possible issue is adding color themes for the Pascal Triangle GUI.

## Internal Documentation (Developer's View)
* Although this project is listed under the Discrete Math category, a lot of possible future issues also involve GUI work. There is not too much mathematics involved in the programming if you're rusty on math. 
* For creating the GUI for the XML file, JEditorPane does not support XML (only HTML) so a third party library would most likely be used.
* For the Pascal's Triangle GUI the numbers in Pascal's Triangle are hard coded in. A future issue could be to generate these numbers.

## Explanation

* The Sierpsinki Triangle is a recursive, fractal design.  
* It can be produced by removing all "even numbers" from Pascal's triangle where the number of rows is a power of 2, and connecting all adjacent odd numbers
* The legal moves in the Towers of Hanoi with n disks correspond to the Sierpsinski triangle that results from this transformation of Pascal's Triangle with 2^n rows.
* Simply put: the sum of the numbers in the nth row minus 1 == the total number of moves required to move an ordered stack of nth discs from one column to another.  
(Note: The starting row in Pascal's Triangle is the 0th row)  
Example:  
If we had 0 discs, we need 0 moves. ((1) - 1)  
If we had 1 disc, we need 1 move. ((1+1) - 1)  
If we had 2 discs, we need 3 moves. ((1+2+1) - 1)  
If we had 3 discs, we need 7 moves. ((1+3+3+1) - 1)  

See for example: (Pascal's Triangle)

![](http://oi59.tinypic.com/30d8qiq.jpg) 


The purpose of this project is to explore this relationship in software in various ways.

See for example: (Sierpinski's Triangle and Tower of Hanoi)  

2 Disks:  
![](http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi2DiskTransitionsTowers.png)  

3 Disks:  
![](http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi3DiskTransitions.png)  

## Running the Code

There are 3 projects in this repository.

### Running the first project (build.xml)

Simply use the following command to run:
```
ant runPascal
```

A future issue could be to allow the user to enter in how many rows they want in Pascal's Triangle (You might want to be able to generate the numbers in Pascal's Triangle before doing this). The code to compile and run the Pascal Triangle GUI is located in the build.xml file which is primarily used for the second project. Consider creating a separate build.xml file specifically for this project. 

### Running the second project (build.xml)

Simply use the following command:

```
ant run
```

You will then be prompted for the input arguments (\<number_of_disks\> \<width\> \<height\> \<save_file_path\>)
where \<width\> and \<height\> are the width and height in pixels of the resulting Scalable Vector Graphics image, and \<save_file_path\> is the name of the output file which will be saved in the root directory of the project. Note that the \<save_file_path\> you choose should end in either .xml or .html, this way it can be opened by any browser that supports html5.

A good sample set of inputs is:
```
3 1000 1000 output.html
```
When the program finishes executing, you will see a file titled output.html in your project's root directory. Open this file in a browser and take a look at the result. Also notice that you can zoom in and out of the image in your browser as much as you like and it will not pixelate. This is becuase it is not actually a picture, but a Scalable Vector Graphics image.

When opening the output.html file in your browser you will see something like this:
(This one is with only 2 disks)

![](http://i.imgur.com/Huqj1wL.png)

Within this project there is also a color picker tool which you can run using
```
ant EX_DEF
```
and
```
ant EX_GUI
```

### Running the third project (build2.xml)
Use the following command:
```
ant -f build2.xml -D#=numdisks run
```
where numdisks in the number of disks you want to have

This project uses a swing GUI but does not use the SVGraphics like the other project. Although it does allow 2 different zoom sizes ("large" and "small") at which the graphics can be displayed.

Below is a view of the program running with numdisks set to 2.

![](http://imgur.com/Rtdpv11.png)
