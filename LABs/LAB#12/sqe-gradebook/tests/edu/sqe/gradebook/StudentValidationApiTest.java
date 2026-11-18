
package edu.sqe.gradebook;

import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

class StudentValidationApiTest extends ApiTestBase {

    @Test
    void unknownStudentShouldReturn404() {
        given()
        .when()
                .get("/students/S9999")
        .then()
                .statusCode(404)
                .body("error", containsString("Unknown student"));
    }

    @Test
    void invalidStudentIdShouldReturn400() {
        given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "studentId", "ABC123",
                        "name", "Test Student"
                ))
        .when()
                .post("/students")
        .then()
                .statusCode(400)
                .body("error", containsString("studentId"));
    }

    @Test
    void missingStudentNameShouldReturn400() {
        given()
                .contentType(ContentType.JSON)
                .body(Map.of("studentId", "S3001"))
        .when()
                .post("/students")
        .then()
                .statusCode(400)
                .body("error", containsString("name"));
    }

    @Test
    void duplicateStudentIdShouldReturn409() {

        Map<String, String> student = Map.of(
                "studentId", "S3002",
                "name", "Test Student"
        );

        given()
                .contentType(ContentType.JSON)
                .body(student)
        .when()
                .post("/students")
        .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .body(student)
        .when()
                .post("/students")
        .then()
                .statusCode(409)
                .body("error", equalTo("studentId already exists"));
    }
    @Test
void nonStringStudentIdShouldReturn400() {
    given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                    "studentId", 1234,
                    "name", "Test Student"
            ))
    .when()
            .post("/students")
    .then()
            .statusCode(400)
            .body("error", containsString("studentId"));
}
@Test
void blankStudentNameShouldReturn400() {
    given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                    "studentId", "S3003",
                    "name", "   "
            ))
    .when()
            .post("/students")
    .then()
            .statusCode(400)
            .body("error", containsString("name"));
}
}
