public class Student {
    public void addScore(int score){
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
    }
}