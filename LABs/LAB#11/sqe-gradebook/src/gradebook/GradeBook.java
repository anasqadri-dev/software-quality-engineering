package gradebook;

public class GradeBook {

    /**
     * Converts a numeric score into a letter grade.
     *
     * @param score the numeric score from 0 to 100
     * @return the corresponding letter grade
     * @throws IllegalArgumentException if the score is outside 0-100
     */
    public static char letterGrade(double score) {

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and 100");
        }

        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    /**
     * Validates a student name.
     *
     * A valid name must be non-empty, contain no more than
     * 50 characters, and contain only letters, spaces, or hyphens.
     *
     * @param name the student name
     * @throws IllegalArgumentException if the name is invalid
     */
    public static void validateName(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(
                    "Name must not be empty");
        }

        if (name.length() > 50) {
            throw new IllegalArgumentException(
                    "Name must not exceed 50 characters");
        }

        if (!name.matches("[A-Za-z -]+")) {
            throw new IllegalArgumentException(
                    "Name may contain only letters, spaces, and hyphens");
        }
    }
}