package DSA;

import java.util.List;

public class LinearSearch {
    public static Student search(List<Student> students, String studentCode) {
        for (Student student : students) {
            if (student.studentCode.equals(studentCode)) {
                return student; 
            }
        }
        return null;  
    }
}
