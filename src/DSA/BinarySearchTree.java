package DSA;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Student student) {
        if (search(student.studentCode) != null) {
            System.out.println("Student with the same student code already exists. Please enter a different code.");
            return;
        }
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        if (student.studentCode.compareTo(node.student.studentCode) < 0) {
            node.left = insertRec(node.left, student);
        } else if (student.studentCode.compareTo(node.student.studentCode) > 0) {
            node.right = insertRec(node.right, student);
        } else {
            System.out.println("Duplicate student code; cannot insert.");
        }
        return node;
    }


    public Student searchUsingLinear(String studentCode) {
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);  
        return LinearSearch.search(students, studentCode); 
    }

    public void sortAndDisplayStudents(String algorithm) {
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);
    
        if (algorithm.equals("bubble")) {
            bubbleSort(students);
        } else if (algorithm.equals("quick")) {
            quickSort(students, 0, students.size() - 1);
        }
    

        System.out.println(Student.getTableHeader());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println(Student.getTableFooter());
    }

    private void collectStudents(TreeNode node, List<Student> students) {
        if (node != null) {
            collectStudents(node.left, students);
            students.add(node.student);
            collectStudents(node.right, students);
        }
    }

    private void bubbleSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    private void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getScore() > pivot.getScore()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    public void update(String studentCode, String newName, double newScore) {
        Student student = search(studentCode);
        if (student != null) {
            student.name = newName;
            student.score = newScore;
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void delete(String studentCode) {
        root = deleteRec(root, studentCode);
    }

    private TreeNode deleteRec(TreeNode node, String studentCode) {
        if (node == null) {
            System.out.println("Student not found.");
            return null;
        }

        if (studentCode.compareTo(node.student.studentCode) < 0) {
            node.left = deleteRec(node.left, studentCode);
        } else if (studentCode.compareTo(node.student.studentCode) > 0) {
            node.right = deleteRec(node.right, studentCode);
        } else {
            System.out.println("Student deleted successfully.");
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            node.student = findMin(node.right);
            node.right = deleteRec(node.right, node.student.studentCode);
        }
        return node;
    }

    private Student findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.student;
    }

    public Student search(String studentCode) {
        return searchRec(root, studentCode);
    }

    private Student searchRec(TreeNode node, String studentCode) {
        if (node == null) {
            return null;
        }
        if (studentCode.equals(node.student.studentCode)) {
            return node.student;
        }
        if (studentCode.compareTo(node.student.studentCode) < 0) {
            return searchRec(node.left, studentCode);
        } else {
            return searchRec(node.right, studentCode);
        }
    }
}
