package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CalleeServiceTest {

    @Test
    public void sayMyName() {
        given()
                .auth().basic(new String(Base64.getDecoder().decode("YWRtaW4=")), new String(Base64.getDecoder().decode("YWRtaW4=")))
                .when().get("/callees/sayMyName?name=Heisenberg")
                .then()
                .statusCode(200);
    }

    @Test
    public void sayMyOtherName() {
        given()
                .auth().basic(new String(Base64.getDecoder().decode("YWRtaW4=")), new String(Base64.getDecoder().decode("YWRtaW4=")))
                .when().get("/callees/sayMyOtherName/Andreas")
                .then()
                    .statusCode(200);
    }

}