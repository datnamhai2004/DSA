package DSA;

public class Student {
    String name;
    String studentCode;
    double score;
    String rank;

    public Student(String name, String studentCode, double score) {
        this.name = name;
        this.studentCode = studentCode;
        this.score = score;
        assignRank();
    }

    public void assignRank() {
        if (score < 5.0) {
            rank = "Fail";
        } else if (score < 6.5) {
            rank = "Medium";
        } else if (score < 7.5) {
            rank = "Good";
        } else if (score < 9.0) {
            rank = "Very Good";
        } else {
            rank = "Excellent";
        }
    }

    public String getStudentCode() {
        return studentCode;
    }
    
    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-13s | %-10.2f | %-12s |", studentCode, name, score, rank);
    }


    public static String getTableHeader() {
        return "+-----------------+---------------+------------+--------------+\n" +
               "|  Student Code   |     Name      |   Score    |     Rank     |\n" +
               "+-----------------+---------------+------------+--------------+";
    }

    public static String getTableFooter() {
        return "+-----------------+---------------+------------+--------------+";
    }
}
