package edu.sqe.gradebook;
import java.util.Map;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeApiTest extends ApiTestBase {

    @Test
    void gradeWithoutScoresShouldReturn400() {

        given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "studentId", "S5001",
                        "name", "No Score Student"
                ))
        .when()
                .post("/students")
        .then()
                .statusCode(201);

        given()
        .when()
                .get("/students/S5001/grade")
        .then()
                .statusCode(400)
                .body("error", equalTo("No scores recorded for this student"));
    }

    @Test
    void gradeScaleBoundariesShouldReturnCorrectGrades() {

        int[] scores = {
                0, 59, 60, 69, 70,
                79, 80, 89, 90, 100
        };

        String[] grades = {
                "F", "F", "D", "D", "C",
                "C", "B", "B", "A", "A"
        };

        for (int i = 0; i < scores.length; i++) {

            given()
            .when()
                    .get("/grade-scale?score=" + scores[i])
            .then()
                    .statusCode(200)
                    .body("score", equalTo(scores[i]))
                    .body("letterGrade", equalTo(grades[i]));
        }
    }

    @Test
    void invalidGradeScaleScoresShouldReturn400() {

        assertEquals(400, given()
                .when()
                .get("/grade-scale?score=-1")
                .then()
                .extract()
                .statusCode());

        assertEquals(400, given()
                .when()
                .get("/grade-scale?score=101")
                .then()
                .extract()
                .statusCode());
    }
    @Test
void letterGradeShouldRejectScoresOutsideRange() {

    IllegalArgumentException lowerException = assertThrows(
            IllegalArgumentException.class,
            () -> GradebookService.letterGrade(-1)
    );

    assertEquals(
            "score must be between 0 and 100",
            lowerException.getMessage()
    );

    IllegalArgumentException upperException = assertThrows(
            IllegalArgumentException.class,
            () -> GradebookService.letterGrade(101)
    );

    assertEquals(
            "score must be between 0 and 100",
            upperException.getMessage()
    );
}
}
