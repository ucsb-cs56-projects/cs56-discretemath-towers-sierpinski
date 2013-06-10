package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.awt.Color;

/**
 * Allows one to set colors in colors.properties file.
 *
 * @author Martin Wolfenbarger
 * @version 2013/06/7 
 */
public class SetColors{
    /** Sets colors in colors.properties file.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input_str;
        System.out.println("This program allows you to set the disk colors by overwriting the colors.properties file. " + 
                           "Enter 0 to exit or anything else to continue. ");
        input_str = in.nextLine();
        Properties prop = new Properties();
        int i = 0;
        try {
            while(!input_str.equals("0")) {
                System.out.println("Enter a hexidecimal color for disk " + i + ", or 0 to exit");
                input_str = in.nextLine();
                if(input_str.equals("0")) break;
                try {
                    if(input_str.length() != 6) throw new NumberFormatException();
                    Color c = Color.decode("0x" + input_str);
                    prop.setProperty("" + i, input_str);
                    i++;
                } catch (NumberFormatException nfe) {
                    System.out.println("Couldn't decode that color. Make sure to enter a hexidecimal color (ex. FF3300).");
                }
            }
            prop.store(new FileOutputStream("colors.properties"), null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
}