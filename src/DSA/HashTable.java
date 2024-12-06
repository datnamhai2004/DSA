package DSA;

public class HashTable {
    private static final int SIZE = 10;
    private Student[] table;

    public HashTable() {
        table = new Student[SIZE];
    }

    private int hash(String studentCode) {
        return studentCode.hashCode() % SIZE;
    }

    public void insert(Student student) {
        int index = hash(student.getStudentCode());
        while (table[index] != null) {
            index = (index + 1) % SIZE;
        }
        table[index] = student;
    }

    public Student search(String studentCode) {
        int index = hash(studentCode);
        while (table[index] != null) {
            if (table[index].getStudentCode().equals(studentCode)) {
                return table[index];
            }
            index = (index + 1) % SIZE;
        }
        return null;
    }

    public void delete(String studentCode) {
        int index = hash(studentCode);
        while (table[index] != null) {
            if (table[index].getStudentCode().equals(studentCode)) {
                table[index] = null;
                return;
            }
            index = (index + 1) % SIZE;
        }
    }

    public void sortAndDisplayStudents(String algorithm) {
        System.out.println("Sorting students using " + algorithm + "...");
    }
}
