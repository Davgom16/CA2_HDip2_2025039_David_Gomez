/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_hdip2_2025039_david_gomez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dav_g
 */
public class CA2_HDip2_2025039_David_Gomez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner inputScanner = new Scanner(System.in); // Scanner for user input

        System.out.print("Enter the file name to read: ");
        String fileName = inputScanner.nextLine();

        // The text file is in the project root folder
        File file = new File(fileName);

        try (Scanner fileScanner = new Scanner(file)) { // Scanner for file

            // Read the file line by line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                
            }
        // message showing that  the doc was read.    
            System.out.println("Document was successfully read");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
            e.printStackTrace();
        }
    }
    
    
    
}
