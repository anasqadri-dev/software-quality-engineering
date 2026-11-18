package edu.sqe.gradebook;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class ScoreValidationApiTest extends ApiTestBase {

    private void registerStudent(String studentId) {
        given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "studentId", studentId,
                        "name", "Test Student"
                ))
        .when()
                .post("/students")
        .then()
                .statusCode(201);
    }

    private int submitScore(String studentId, int score) {
        return given()
                .contentType(ContentType.JSON)
                .body(Map.of("score", score))
        .when()
                .post("/students/" + studentId + "/scores")
        .then()
                .extract()
                .statusCode();
    }

    @Test
void scoreBoundariesShouldBeValidated() {
    registerStudent("S4001");

    // -1 should be rejected
    assertEquals(400, submitScore("S4001", -1));

    // 0 should be accepted
    assertEquals(201, submitScore("S4001", 0));

    // 100 should be accepted
    assertEquals(201, submitScore("S4001", 100));

    // 101 should be rejected
    assertEquals(400, submitScore("S4001", 101));
}

    @Test
void maximumFiveScoresShouldBeAllowed() {
    registerStudent("S4002");

    // First five scores should be accepted
    for (int i = 1; i <= 5; i++) {
        assertEquals(201, submitScore("S4002", 80));
    }

    // Sixth score should be rejected
    assertEquals(400, submitScore("S4002", 95));
}
@Test
void nonIntegerScoreShouldReturn400() {
    registerStudent("S4003");

    given()
            .contentType(ContentType.JSON)
            .body(Map.of("score", "85"))
    .when()
            .post("/students/S4003/scores")
    .then()
            .statusCode(400);
}
}
