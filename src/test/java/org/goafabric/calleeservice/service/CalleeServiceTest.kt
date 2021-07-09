package org.goafabric.calleeservice.service

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Test

@QuarkusTest
internal class CalleeServiceTest {
    @Test
    fun sayMyName() {
        RestAssured.given()
            .`when`()["/callees/sayMyName?name=Heisenberg"]
            .then()
            .statusCode(200)
    }

    @Test
    fun sayMyOtherName() {
        RestAssured.given()
            .`when`()["/callees/sayMyOtherName/Andreas"]
            .then()
            .statusCode(200)
    }

    @Test
    fun setSleepTime() {
        RestAssured.given()
            .`when`()["/callees/setSleepTime?sleepTime=0"]
            .then()
            .statusCode(200)
    }
}