public class Student {
    private String studentName;
    private int age;
    
    public Student(String studentName, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        this.studentName = studentName;
        this.age = age;
    }


    /**
     * Adds a score to the student.
    **/
    public void addScore(int score){
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
    }
}