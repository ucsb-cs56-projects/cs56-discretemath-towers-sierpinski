cs56-discretemath-towers-sierpinski
===================================

Java software to explore the relationship between the Towers of Hanoi and the Sierpinski Triangle

## Explanation

* The Sierpsinki Triangle is a recursive, fractal design.  
* It can be produced by removing all "even numbers" from Pascal's triangle where the number of rows is a power of 2, and connecting all adjacent odd numbers
* The legal moves in the Towers of Hanoi with n disks correspond to the Sierpsinski triangle that results from this transformation of Pascal's Triangle with 2^n rows.

The purpose of this project is to explore this relationship in software in various ways.

See for example:

http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi2DiskTransitionsTowers.png

http://www.cs.ucsb.edu/~pconrad/cs40/12F/hwk/IC11/TowersOfHanoi3DiskTransitions.png

## Running the Code

There are actually two projects in the repo.

### Running the first project (build.xml)
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
```and
```
ant EX_GUI
```
This project, at the moment, does not change the color scheme of the SVG output.
There is a colors.properties file in the root directory of the project, however, that does contain the values of the color scheme. If these hex color code values are changed, and the program is run, you will see a change in the color scheme of the SVG output.

### Running the second project (build2.xml)
Use the following command:
```
ant -f build2.xml -D#=numdisks run
```
where numdisks in the number of disks you want to have

This project uses a swing GUI but does not use the SVGraphics like the other project. Although it does allow 2 different zoom sizes ("large" and "small") at which the graphics can be displayed.

Below is a view of the program running with numdisks set to 2.

![](http://imgur.com/Rtdpv11.png)
