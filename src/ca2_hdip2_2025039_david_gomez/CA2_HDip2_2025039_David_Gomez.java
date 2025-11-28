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
        String position;
        String department;

        public Employee(String name, String manager, String position, String department) {
            this.name = name;
            this.manager = manager;
            this.position = position;
            this.department = department;
        }
    }
    
            // Node class for binary tree
        public static class TreeNode {
            String name;
            String manager;
            String position;
            String department;
            TreeNode left, right;

            public TreeNode(String name, String manager, String position, String department) {
                this.name = name;
                this.manager = manager;
                this.position = position;
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
                
                int level = 0;

                System.out.println("\n===== Employee Hierarchy (Level-Order) =====");
                while (!q.isEmpty()) {
                    int levelSize = q.size();
                    System.out.print("level " + level + ":");
                    for (int i = 0; i < levelSize; i++){
                        
                        TreeNode cur = q.poll();
                        System.out.println(cur.name + " | " + cur.manager + " | " + cur.position + " | " + cur.department);
                        if (cur.left != null) q.add(cur.left);
                        if (cur.right != null) q.add(cur.right);
           
                    }
                    
                    System.out.println(); // new line for next level
                    level++;
                    
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
                    
                    System.out.println("\nYou chose: SORT");
                    try (Scanner fileScanner = new Scanner(new File(fileName))) {
                        // Skip header line
                        if (fileScanner.hasNextLine()) fileScanner.nextLine();

                        // Read all names into an array list
                        List<String> namesList = new ArrayList<>();

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine().trim();
                            
                            if (!line.isEmpty()) {
                                String[] parts = line.split(",");
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
                        
                        System.out.println("\n===== First 20 Names (A–Z) =====");
                        for (int i = 0; i < names.length && i < 20; i++) {
                            System.out.println((i + 1) + ". " + names[i]);
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
                            String position = parts[2].trim();
                            String department = parts[3].trim();
                            employeeList.add(new Employee(name, manager, position, department));
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
                                System.out.println("Position: " + emp.position);
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
                    
                    String newPosition = "";
                    boolean validPosition = false;
                    
                    while (!validPosition) {
                        System.out.println("\n===== Select Position =====");
                        System.out.println("1. Operative");
                        System.out.println("2. Manager");
                        System.out.print("Choose an option (1-2): ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Please enter 1 or 2.");
                            scanner.nextLine();
                            continue;
                        }

                        int posChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (posChoice == 1) {
                            newPosition = "Operative";
                            validPosition = true;
                        } else if (posChoice == 2) {
                            newPosition = "Manager";
                            validPosition = true;
                        } else {
                            System.out.println("Invalid choice! Try again.");
                        }
                    }
                    
                    String dept = "";
                    String man = "";
                        
                    if (newPosition.equals("Operative")) {

                        boolean managerChosen = false;
                    
                        while (!managerChosen) {

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

                            switch (man_dept) {
                                case 1:
                                    man = "Robert King";
                                    dept = "Sales";
                                    break;

                                case 2:
                                    man = "Laura Green";
                                    dept = "Customer Service";
                                    break;

                                case 3:
                                    man = "James Smith";
                                    dept = "Finance";
                                    break;

                                case 4:
                                    man = "Emily White";
                                    dept = "Human Resources";
                                    break;

                                case 5:
                                    man = "William Harris";
                                    dept = "IT";
                                    break;

                                case 6:
                                    System.out.println("Returning to Main Menu...");
                                    managerChosen = true; // exit loop
                                    continue;

                                default:
                                    System.out.println("Invalid choice! Please try again.");
                                    break;

                            }
                        }
                    }
                    
                    if (newPosition.equals("Manager")) {

                        boolean departmentChosen = false;

                        while (!departmentChosen) {
                            System.out.println("\n===== Select Department for the Manager =====");
                            System.out.println("1. Sales");
                            System.out.println("2. Customer Service");
                            System.out.println("3. Finance");
                            System.out.println("4. Human Resources");
                            System.out.println("5. IT");
                            System.out.println("6. Cancel and Return to Main Menu");

                            System.out.print("Choose an option (1-6): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Invalid input! Enter 1-6.");
                                scanner.nextLine();
                                continue;
                            }

                            int opt = scanner.nextInt();
                            scanner.nextLine();

                            switch (opt) {
                                case 1:
                                    dept = "Sales";
                                    break;
                                case 2:
                                    dept = "Customer Service";
                                    break;
                                case 3:
                                    dept = "Finance";
                                    break;
                                case 4:
                                    dept = "Human Resources";
                                    break;
                                case 5:
                                    dept = "IT";
                                    break;
                                case 6:
                                    System.out.println("Returning to Main Menu...");
                                    departmentChosen = true;
                                    break;
                                default:
                                    System.out.println("Invalid option! Try again.");
                            }
                        }
                    }

                            // Only write if user chose a valid option
                    try (FileWriter writer = new FileWriter(fileName, true)) {
                        writer.write("\n" + newName + "," + man + "," + newPosition + "," + dept);
                        System.out.println("Record successfully added!");
                    } catch (IOException e) {
                        System.out.println("Error writing to file!");
                        e.printStackTrace();
                    }

                    break;
                                     
                case 4:
                    
                    System.out.println("\nYou chose: CREATE A BINARY TREE");

                    BinaryTree tree = new BinaryTree();

                    try (Scanner fileScanner = new Scanner(new File(fileName))) {
                        // Skip header line if present
                        if (fileScanner.hasNextLine()) fileScanner.nextLine();

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length < 3) continue;

                            String name = parts[0].trim();
                            String manager = parts[1].trim();
                            String position = parts[2].trim();
                            String department = parts[3].trim();

                            TreeNode node = new TreeNode(name, manager, position, department);
                            tree.insert(node);
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found!");
                        break;
                    }

                    // Display the tree in level-order
                    tree.bfs();

                    // Show total nodes and height
                    System.out.println("\nTotal Employees (Nodes): " + tree.nodeCount);
                    System.out.println("Tree Height (approx.): " + tree.getHeight(tree.root));
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
