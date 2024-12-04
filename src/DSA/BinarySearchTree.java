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
        }
        updateHeight(node);
        return balance(node);
    }
    private void updateHeight(TreeNode node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    
    private int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    private TreeNode balance(TreeNode node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T = x.right;

        x.right = y;
        y.left = T;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T = y.left;

        y.left = x;
        x.right = T;

        updateHeight(x);
        updateHeight(y);

        return y;
    }



    private int getBalanceFactor(TreeNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }



    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            TreeNode minNode = findMin(node.right);
            node.student = minNode.student;
            node.right = deleteRec(node.right, minNode.student.studentCode);
        }

        updateHeight(node);
        return balance(node);
    }
    public Student search(String studentCode) {
        return searchRec(root, studentCode);
    }

    private Student searchRec(TreeNode node, String studentCode) {
        if (node == null || node.student.studentCode.equals(studentCode)) {
            return node == null ? null : node.student;
        }
        return studentCode.compareTo(node.student.studentCode) < 0 ? searchRec(node.left, studentCode) : searchRec(node.right, studentCode);
    }

    public void sortAndDisplayStudents(String algorithm) {
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);

        if (algorithm.equalsIgnoreCase("1")) {
            bubbleSort(students);
        } else if (algorithm.equalsIgnoreCase("2")) {
            quickSort(students, 0, students.size() - 1);
        } else {
            System.out.println("Invalid algorithm choice. Defaulting to Bubble Sort.");
            bubbleSort(students);
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
            if (students.get(j).getScore() >= pivot.getScore()) {
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
}
