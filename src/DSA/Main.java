package DSA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Object dataStructure = null;

        // Chọn cấu trúc dữ liệu
        while (dataStructure == null) {
            System.out.println("\n WELCOME TO THE STUDENT MANAGEMENT SYSTEM!");
            System.out.println("\nEnter the Data Structure type, please:");
            System.out.println("1. Binary Search Tree (BST)");
            System.out.println("2. Hash Table");
            System.out.print("Choose an option: ");

            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); 
                if (option == 1) {
                    dataStructure = new BinarySearchTree();
                } else if (option == 2) {
                    dataStructure = new HashTable();
                } else {
                    System.out.println("Invalid option. Please choose 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 2.");
                scanner.nextLine(); 
            }
        }

        while (true) {
            System.out.println("\n      MENU:");
            System.out.println("1. Add Students");
            System.out.println("2. Ranking Table");
            System.out.println("3. Update Information Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = 0;
            try {
                option = scanner.nextInt(); 
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 6.");
                scanner.nextLine(); 
                continue;
            }

            switch (option) {
                case 1:
                    addStudents(dataStructure, scanner);
                    break;
                case 2:
                    displayRankingTable(dataStructure, scanner);
                    break;
                case 3:
                    updateStudent(dataStructure, scanner);
                    break;
                case 4:
                    deleteStudent(dataStructure, scanner);
                    break;
                case 5:
                    searchStudent(dataStructure, scanner);
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudents(Object dataStructure, Scanner scanner) {
        System.out.print("Enter number of students to add: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            String studentCode;
            while (true) {
                System.out.print("Student Code: ");
                studentCode = scanner.nextLine();
                if (dataStructure instanceof BinarySearchTree) {
                    BinarySearchTree bst = (BinarySearchTree) dataStructure;
                    if (bst.search(studentCode) != null) {
                        System.out.println("Duplicate student code detected. Please enter a different code.");
                    } else {
                        break;
                    }
                } else if (dataStructure instanceof HashTable) {
                    HashTable hashTable = (HashTable) dataStructure;
                    if (hashTable.search(studentCode) != null) {
                        System.out.println("Duplicate student code detected. Please enter a different code.");
                    } else {
                        break;
                    }
                }
            }

            double score = 0.0;
            while (true) {
                try {
                    System.out.print("Score: ");
                    score = scanner.nextDouble(); 
                    scanner.nextLine();
                    if (score < 0 || score > 10) {
                        System.out.println("Invalid score. Please enter a score between 0 and 10.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid score. Please enter a numeric value.");
                    scanner.nextLine(); 
                }
            }

            Student student = new Student(name, studentCode, score);

            if (dataStructure instanceof BinarySearchTree) {
                BinarySearchTree bst = (BinarySearchTree) dataStructure;
                bst.insert(student);
            } else if (dataStructure instanceof HashTable) {
                HashTable hashTable = (HashTable) dataStructure;
                hashTable.insert(student);
            }
        }
    }

    private static void displayRankingTable(Object dataStructure, Scanner scanner) {
        System.out.println("\n1. BUBBLE SORT");
        System.out.println("2. QUICK SORT");
        System.out.print("Enter Sorting Algorithm: ");
        String algorithm = scanner.nextLine();

        if (dataStructure instanceof BinarySearchTree) {
            BinarySearchTree bst = (BinarySearchTree) dataStructure;
            bst.sortAndDisplayStudents(algorithm);
        } else if (dataStructure instanceof HashTable) {
            HashTable hashTable = (HashTable) dataStructure;
            hashTable.sortAndDisplayStudents(algorithm);
        }
    }

    private static void updateStudent(Object dataStructure, Scanner scanner) {
        System.out.print("Enter student code to update: ");
        String studentCode = scanner.nextLine();

        Student student = null;

        if (dataStructure instanceof BinarySearchTree) {
            BinarySearchTree bst = (BinarySearchTree) dataStructure;
            student = bst.search(studentCode);
        } else if (dataStructure instanceof HashTable) {
            HashTable hashTable = (HashTable) dataStructure;
            student = hashTable.search(studentCode);
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        double newScore = 0.0;
        while (true) {
            try {
                System.out.print("Enter new score: ");
                newScore = scanner.nextDouble();
                scanner.nextLine();
                if (newScore < 0 || newScore > 10) {
                    System.out.println("Invalid score. Please enter a score between 0 and 10.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine();
            }
        }

        if (dataStructure instanceof BinarySearchTree) {
            BinarySearchTree bst = (BinarySearchTree) dataStructure;
            bst.delete(studentCode);
            bst.insert(new Student(newName, studentCode, newScore));
        } else if (dataStructure instanceof HashTable) {
            HashTable hashTable = (HashTable) dataStructure;
            hashTable.delete(studentCode);
            hashTable.insert(new Student(newName, studentCode, newScore));
        }
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent(Object dataStructure, Scanner scanner) {
        System.out.print("Enter student code to delete: ");
        String studentCode = scanner.nextLine();

        if (dataStructure instanceof BinarySearchTree) {
            BinarySearchTree bst = (BinarySearchTree) dataStructure;
            bst.delete(studentCode);
        } else if (dataStructure instanceof HashTable) {
            HashTable hashTable = (HashTable) dataStructure;
            hashTable.delete(studentCode);
        }
    }

    private static void searchStudent(Object dataStructure, Scanner scanner) {
        System.out.print("Enter student code to search: ");
        String studentCode = scanner.nextLine();
        Student student = null;

        if (dataStructure instanceof BinarySearchTree) {
            BinarySearchTree bst = (BinarySearchTree) dataStructure;
            student = bst.search(studentCode);
        } else if (dataStructure instanceof HashTable) {
            HashTable hashTable = (HashTable) dataStructure;
            student = hashTable.search(studentCode);
        }

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }
}
