package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
//@TestSecurity(user = "admin", roles = {"admin"})
public class CalleeServiceTest {

    @Test
    public void testIsAlive() {
        given()
          .when().get("/callees/isAlive")
          .then()
             .statusCode(200)
                .body(is("true"));
    }

}