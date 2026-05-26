package org.goafabric.calleeservice.controller

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import java.util.*

@QuarkusTest
open class CalleeControllerTest {
    @Test
    fun sayMyName() {
        RestAssured.given()
            .`when`().get("/callees/sayMyName?name=Heisenberg")
            .then()
            .statusCode(200)
    }

    @Test
    fun sayMyOtherName() {
        RestAssured.given()
            .`when`().get("/callees/sayMyOtherName/Andreas")
            .then()
            .statusCode(200)
    }
}