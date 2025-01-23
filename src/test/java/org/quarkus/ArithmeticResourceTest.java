package org.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ArithmeticResourceTest {

@Test
@TestSecurity(user = "jonas",roles = "user")
public void testAddEndpoint() {
        OperationRequest request = new OperationRequest(5, 3);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/add")
                .then()
                .statusCode(200)
                .body("result", is(8));
    }

    @Test
    @TestSecurity(user = "jonas",roles = "user")
    public void testSubtractEndpoint() {
        OperationRequest request = new OperationRequest(5, 3);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/subtract")
                .then()
                .statusCode(200)
                .body("result", is(2));
    }

    @Test
    @TestSecurity(user = "jonas",roles = "user")
    public void testMultiplyEndpoint() {
        OperationRequest request = new OperationRequest(5, 3);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/multiply")
                .then()
                .statusCode(200)
                .body("result", is(15));
    }

    @Test
    @TestSecurity(user = "jonas",roles = "user")
    public void testDivideEndpoint() {

        OperationRequest request = new OperationRequest(6,3);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/divide")
                .then()
                .statusCode(200)
                .body("result", is(2.00F));
    }

    @Test
    @TestSecurity(user = "jonas",roles = "user")
    public void testDivideEndpointFloat() {

        OperationRequest request = new OperationRequest(15,2);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/divide")
                .then()
                .statusCode(200)
                .body("result", is(7.50F));
    }

    @Test
    @TestSecurity(user = "jonas",roles = "user")
    public void testDivideByZero() {
        OperationRequest request = new OperationRequest(6, 0);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/divide")
                .then()
                .statusCode(400);
    }

    @Test
    @TestSecurity(user = "unauthorized",roles = "unauthorized")
    public void testUnauthorizedAccess() {
        OperationRequest request = new OperationRequest(5, 3);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/arithmetic/add")
                .then()
                .statusCode(403);
    }
}
