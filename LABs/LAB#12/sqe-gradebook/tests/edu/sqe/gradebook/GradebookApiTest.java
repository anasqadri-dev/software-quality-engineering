
package edu.sqe.gradebook;

import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class GradebookApiTest extends ApiTestBase {

    @Test
    void positiveApiWorkflow() {

        // 1. Register a student
        given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "studentId", "S2001",
                        "name", "Amina Khan"
                ))
        .when()
                .post("/students")
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("studentId", equalTo("S2001"))
                .body("name", equalTo("Amina Khan"));

        // 2. Record the first score
        given()
                .contentType(ContentType.JSON)
                .body(Map.of("score", 85))
        .when()
                .post("/students/S2001/scores")
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("score", equalTo(85))
                .body("average", equalTo(85));

        // 3. Record the second score
        given()
                .contentType(ContentType.JSON)
                .body(Map.of("score", 90))
        .when()
                .post("/students/S2001/scores")
        .then()
                .statusCode(201)
                .body("average", equalTo(88));

        // 4. Get all students
        given()
        .when()
                .get("/students")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].studentId", equalTo("S2001"));

        // 5. Get one student
        given()
        .when()
                .get("/students/S2001")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("studentId", equalTo("S2001"))
                .body("name", equalTo("Amina Khan"))
                .body("average", equalTo(88));

        // 6. Get the student's grade
        given()
        .when()
                .get("/students/S2001/grade")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("average", equalTo(88))
                .body("letterGrade", equalTo("B"));

        // 7. Check the grade scale
        given()
        .when()
                .get("/grade-scale?score=90")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("score", equalTo(90))
                .body("letterGrade", equalTo("A"));
    }
}
