/*
package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CalleeServiceTest {

    @Test
    public void sayMyName() {
        given()
          .when().get("/callees/sayMyName?name=Heisenberg")
          .then()
             .statusCode(200);
    }

    @Test
    public void sayMyOtherName() {
        given()
                .when().get("/callees/sayMyOtherName/Andreas")
                .then()
                    .statusCode(200);
    }

    @Test
    public void setSleepTime() {
        given()
                .when().get("/callees/setSleepTime?sleepTime=0")
                .then()
                .statusCode(200);
    }

}

 */