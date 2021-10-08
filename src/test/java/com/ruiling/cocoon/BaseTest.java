package com.ruiling.cocoon;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DBRider
@DBUnit(schema = "COCOON_TEST")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CocoonProjectApplication.class, webEnvironment = RANDOM_PORT)
public class BaseTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Init rest assured port
    }

    protected RequestSpecification given() {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
