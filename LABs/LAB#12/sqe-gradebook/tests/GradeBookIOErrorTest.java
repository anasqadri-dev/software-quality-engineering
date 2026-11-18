import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import gradebook.GradeBookIOError;

class GradeBookIOErrorTest {

    @Test
    void preservesMessageAndOriginalCause() {
        IOExceptionCause cause = new IOExceptionCause("disk unavailable");
        GradeBookIOError error = new GradeBookIOError("Unable to save roster", cause);

        assertEquals("Unable to save roster", error.getMessage());
        assertEquals(cause, error.getCause());
        assertNotNull(error.getCause());
    }

    private static final class IOExceptionCause extends Exception {
        private static final long serialVersionUID = 1L;

        private IOExceptionCause(String message) {
            super(message);
        }
    }
}
