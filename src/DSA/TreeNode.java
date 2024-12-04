package DSA;

public class TreeNode {
    Student student;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(Student student) {
        this.student = student;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
