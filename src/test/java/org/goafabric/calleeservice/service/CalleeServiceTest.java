package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CalleeServiceTest {

    @Test
    public void sayMyName() {
        given()
          .when().get("/callees/sayMyName?name=Andreas")
          .then()
             .statusCode(200);
                //.body(is("true"));
    }

    @Test
    public void sayMyOtherName() {
        given()
                .when().get("/callees/sayMyOtherName/Andreas")
                .then()
                .statusCode(200);
        //.body(is("true"));
    }

}