package DSA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

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
                    addInitialStudents(bst, scanner);
                    break;
                case 2:
                    displayRankingTable(bst, scanner);
                    break;
                case 3:
                    updateStudent(bst, scanner);
                    break;
                case 4:
                    deleteStudent(bst, scanner);
                    break;
                case 5:
                    searchStudent(bst, scanner);
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addInitialStudents(BinarySearchTree bst, Scanner scanner) {
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
                if (bst.search(studentCode) != null) {
                    System.out.println("Duplicate student code detected. Please enter a different code.");
                } else {
                    break;
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
            bst.insert(new Student(name, studentCode, score));
        }
    }

    private static void displayRankingTable(BinarySearchTree bst, Scanner scanner) {
        System.out.println("");
        System.out.println("1. BUBBLE SORT");
        System.out.println("2. QUICK SORT");
        System.out.print("Enter Sorting Algorithm: ");
        String algorithm = scanner.nextLine();
        bst.sortAndDisplayStudents(algorithm);
    }

    private static void updateStudent(BinarySearchTree bst, Scanner scanner) {
        System.out.print("Enter student code to update: ");
        String studentCode = scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new score: ");
        double newScore = scanner.nextDouble();
        scanner.nextLine();

        bst.update(studentCode, newName, newScore);
    }

    private static void deleteStudent(BinarySearchTree bst, Scanner scanner) {
        System.out.print("Enter student code to delete: ");
        String studentCode = scanner.nextLine();
        bst.delete(studentCode);
    }

    private static void searchStudent(BinarySearchTree bst, Scanner scanner) {
        System.out.print("Enter student code to search: ");
        String studentCode = scanner.nextLine();
        Student student = bst.searchUsingLinear(studentCode);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }
}
