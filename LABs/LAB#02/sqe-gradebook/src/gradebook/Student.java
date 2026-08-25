package gradebook;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String rollNo;
    private List<Double> scores;

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.scores = new ArrayList<>();
    }
}