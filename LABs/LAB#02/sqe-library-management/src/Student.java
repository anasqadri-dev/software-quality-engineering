public class Student {
    private String name;
    private int age;
    public Student() {
        this.name = "Anas";
        this.age = 21;
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