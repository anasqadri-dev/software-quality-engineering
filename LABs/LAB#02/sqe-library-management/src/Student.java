public class Student {
    /**
     * Adds a score to the student.
     **/
    public void addScore(int score){
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
    }
}