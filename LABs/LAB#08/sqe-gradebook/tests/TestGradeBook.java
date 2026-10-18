import gradebook.GradeBook;

public class TestGradeBook {

    public static void main(String[] args) {

        System.out.println("45  -> " + GradeBook.letterGrade(45));
        System.out.println("65  -> " + GradeBook.letterGrade(65));
        System.out.println("75  -> " + GradeBook.letterGrade(75));
        System.out.println("85  -> " + GradeBook.letterGrade(85));
        System.out.println("95  -> " + GradeBook.letterGrade(95));

        try {
            GradeBook.letterGrade(-10);
            System.out.println("-10 -> FAIL: Invalid score accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("-10 -> PASS: Invalid score rejected.");
        }

        try {
            GradeBook.letterGrade(150);
            System.out.println("150 -> FAIL: Invalid score accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("150 -> PASS: Invalid score rejected.");
        }
    }
}