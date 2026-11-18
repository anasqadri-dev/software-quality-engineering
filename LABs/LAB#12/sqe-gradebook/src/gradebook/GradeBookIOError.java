
package gradebook;

/**
 * Indicates that a GradeBook file operation has failed.
 */
public class GradeBookIOError extends RuntimeException {

    public GradeBookIOError(String message, Throwable cause) {
        super(message, cause);
    }
}
