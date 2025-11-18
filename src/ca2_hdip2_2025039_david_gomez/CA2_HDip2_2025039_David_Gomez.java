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
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

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
    
    // Recursive binary search for Strings
    public static int binarySearch(String[] arr, int left, int right, String target) {
        if (left > right) {
            return -1; // Not found
        }

        int mid = left + (right - left) / 2;
        int comparison = arr[mid].compareToIgnoreCase(target);

        if (comparison == 0) {
            return mid; // Found
        } else if (comparison > 0) {
            return binarySearch(arr, left, mid - 1, target); // Search left half
        } else {
            return binarySearch(arr, mid + 1, right, target); // Search right half
        }
    }
    
    // Simple Employee data structure
    public static class Employee {
        String name;
        String manager;
        String department;

        public Employee(String name, String manager, String department) {
            this.name = name;
            this.manager = manager;
            this.department = department;
        }
    }
    
            // Node class for binary tree
        public static class TreeNode {
            String name;
            String manager;
            String department;
            TreeNode left, right;

            public TreeNode(String name, String manager, String department) {
                this.name = name;
                this.manager = manager;
                this.department = department;
            }
        }

        // Binary tree with level-order insertion
        public static class BinaryTree {
            TreeNode root;
            int nodeCount = 0;

            // Insert node in level-order (≤2 children)
            public void insert(TreeNode newNode) {
                nodeCount++;
                if (root == null) {
                    root = newNode;
                    return;
                }

                Queue<TreeNode> q = new LinkedList<>();
                q.add(root);

                while (!q.isEmpty()) {
                    TreeNode current = q.poll();
                    if (current.left == null) {
                        current.left = newNode;
                        return;
                    } else {
                        q.add(current.left);
                    }

                    if (current.right == null) {
                        current.right = newNode;
                        return;
                    } else {
                        q.add(current.right);
                    }
                }
            }

            // BFS traversal
            public void bfs() {
                if (root == null) return;
                Queue<TreeNode> q = new LinkedList<>();
                q.add(root);

                System.out.println("\n===== Employee Hierarchy (Level-Order) =====");
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    System.out.println(cur.name + " | " + cur.manager + " | " + cur.department);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
            }

            // Compute tree height
            public int getHeight(TreeNode node) {
                if (node == null) return 0;
                return 1 + Math.max(getHeight(node.left), getHeight(node.right));
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
                    int j = 1;
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
                            System.out.println(j + " " +name);
                            j = j + 1;
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found!");
                    }                    
                    break;
                case 2:
                    System.out.println("\nYou chose: SEARCH");

                try (Scanner fileScanner = new Scanner(new File(fileName))) {
                    // Skip header line
                    if (fileScanner.hasNextLine()) fileScanner.nextLine();

                    // Read all employees into a list
                    List<Employee> employeeList = new ArrayList<>();

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length >= 3) {
                            String name = parts[0].trim();
                            String manager = parts[1].trim();
                            String department = parts[2].trim();
                            employeeList.add(new Employee(name, manager, department));
                        }
                    }

                    // Extract only the names for searching and sort both lists
                    String[] names = new String[employeeList.size()];
                    for (int i = 0; i < employeeList.size(); i++) {
                        names[i] = employeeList.get(i).name;
                    }

                    // Sort names alphabetically (insertion sort)
                    InsertionSort.insertionSort(names);

                    // Ask for user input
                    System.out.print("Enter the name to search: ");
                    String targetName = scanner.nextLine().trim();

                    // Perform binary search
                    int result = binarySearch(names, 0, names.length - 1, targetName);

                    if (result == -1) {
                        System.out.println("Name not found in the list.");
                    } else {
                        // Find the matching employee’s full info
                        for (Employee emp : employeeList) {
                            if (emp.name.equalsIgnoreCase(names[result])) {
                                System.out.println("\n Employee found!");
                                System.out.println("Name: " + emp.name);
                                System.out.println("Manager: " + emp.manager);
                                System.out.println("Department: " + emp.department);
                                break;
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Error: File not found!");
                }

                break;
                case 3:
                    
                    System.out.println("\nYou chose: ADD RECORDS");
                    System.out.print("\nEnter the employee name to add: ");
                    String newName = scanner.nextLine().trim();
                    boolean recordAdded = false;
                    
                    while (!recordAdded) {
                        
                        System.out.println("\n===== Select a Manager & Department=====");
                        System.out.println("1. Robert King, Sales");
                        System.out.println("2. Laura Green, Customer Service");
                        System.out.println("3. James Smith, Finance");
                        System.out.println("4. Emily White, Human Resources");
                        System.out.println("5. William Harris, IT");
                        System.out.println("6. Return to main manu");

                        System.out.print("Choose an option (1-6): ");

                        // Check for invalid input (non-number)
                        if (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Please enter a number (1-6).");
                            scanner.nextLine(); // clear invalid input
                            continue;
                        }

                        int man_dept = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        String department = "";
                        String manager = "";
                        
                        
                        switch (man_dept) {
                            case 1:
                                manager = "Robert King";
                                department = "Sales";
                                break;

                            case 2:
                                manager = "Laura Green";
                                department = "Customer Service";
                                break;

                            case 3:
                                manager = "James Smith";
                                department = "Finance";
                                break;

                            case 4:
                                manager = "Emily White";
                                department = "Human Resources";
                                break;

                            case 5:
                                manager = "William Harris";
                                department = "IT";
                                break;
                                
                            case 6:
                                System.out.println("Returning to Main Menu...");
                                recordAdded = true; // exit loop
                                continue;

                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;

                        }
                        
                        // Only write if user chose a valid option
                        try (FileWriter writer = new FileWriter(fileName, true)) { // append mode
                            writer.write("\n" + newName + "," + manager + "," + department);
                            System.out.println("Record successfully added to the file!");
                            recordAdded = true;
                        } catch (IOException e) {
                            System.out.println("Error writing to the file!");
                            e.printStackTrace();
                        }
                    }
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
