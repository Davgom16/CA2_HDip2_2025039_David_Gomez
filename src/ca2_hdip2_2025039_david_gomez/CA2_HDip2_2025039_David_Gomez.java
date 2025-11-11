/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_hdip2_2025039_david_gomez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dav_g
 */
public class CA2_HDip2_2025039_David_Gomez {
    
    public class InsertionSort {

    // Sorts an array of Strings alphabetically
    public static void insertionSort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j = i - 1;

            // Compare alphabetically using compareToIgnoreCase
            while (j >= 0 && arr[j].compareToIgnoreCase(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner inputScanner = new Scanner(System.in); // Scanner for user input

        System.out.print("Enter the file name to read: ");
        String fileName = inputScanner.nextLine();

        // The text file is in the project root folder
        File file = new File(fileName);
    
      Scanner scanner = new Scanner(System.in);
            while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. SORT");
            System.out.println("2. SEARCH");
            System.out.println("3. ADD RECORDS");
            System.out.println("4. CREATE A BINARY TREE");
            System.out.println("5. EXIT");

            System.out.print("Choose an option (1-5): ");
            
            // Check for invalid input (non-number)
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1-5).");
                scanner.nextLine(); // clear invalid input
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    int i = 1;
                    System.out.println("\nYou chose: SORT");
                    try (Scanner fileScanner = new Scanner(new File(fileName))) {
                        // Skip header line
                        if (fileScanner.hasNextLine()) fileScanner.nextLine();

                        // Read all names into an array list
                        List<String> namesList = new ArrayList<>();

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length >= 1) {
                                namesList.add(parts[0].trim()); // first column is Name
                            }
                        }

                        // Convert ArrayList to array
                        String[] names = namesList.toArray(new String[0]);

                        // Show unsorted names
                        System.out.println("\nBefore sorting:");
                        for (String name : names) {
                            System.out.println(name);
                        }

                        // Call the insertion sort for strings
                        InsertionSort.insertionSort(names);

                        // Show sorted names
                        System.out.println("\nAfter sorting (A–Z):");
                        for (String name : names) {
                            System.out.println(i + " " +name);
                            i = i + 1;
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found!");
                    }                    
                    break;
                case 2:
                    System.out.println("case 2 under construction");
                    break;
                case 3:
                    System.out.println("case 3 under construction");
                    break;
                case 4:
                    System.out.println("case 4 under construction");
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return; // <-- Stops the loop and ends the program
                 
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
