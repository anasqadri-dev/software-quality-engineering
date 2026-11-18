package edu.sqe.gradebook;

import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class StudentSchemaApiTest extends ApiTestBase {

    @Test
    void studentListShouldFollowJsonSchema() {

        // Register a student
        given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                "studentId", "S6001",
                "name", "Schema Test Student"
            ))
        .when()
            .post("/students")
        .then()
            .statusCode(201);

        // Record a score
        given()
            .contentType(ContentType.JSON)
            .body(Map.of("score", 85))
        .when()
            .post("/students/S6001/scores")
        .then()
            .statusCode(201);

        // Validate the student list against JSON Schema
        given()
        .when()
            .get("/students")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(
                "schemas/student-list.schema.json"
            ));
    }
    @Test
void studentListResponseShouldBeUnderOneSecond() {

    // Warm up the endpoint before measuring
    given()
    .when()
        .get("/students");

    // Measure response time
    Response response = given()
    .when()
        .get("/students");

    long responseTime = response.getTime();

    assertEquals(200, response.statusCode());

    assertTrue(
        responseTime < 1000,
        "Response time should be under 1000 ms, but was "
                + responseTime + " ms"
    );

    System.out.println(
        "GET /students response time: " + responseTime + " ms"
    );
}
}