package pl.petwalker.web.standalone;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Random;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static pl.petwalker.web.standalone.TestUrl.url;

public class PetWalkerRestTest {

    private static final Random RANDOM = new Random();

    @Test
    public void fetchUsers() {
        expect()
            .statusCode(200)
            .body("$", hasItems("jacek", "bruce-willis"))
        .given()
            .log().all()
        .when()
            .get(url("users"))
        .then()
            .log().all();
    }

    @Test
    public void fetchUserData() {
        expect()
            .statusCode(200)
            .body("$.size()", greaterThan(1))
        .given()
            .log().all()
        .when()
            .get(url("users/jacek"))
        .then()
            .log().all();
    }

    @Test
    public void insertPosition() {
        expect()
            .statusCode(201)
        .given()
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"latitude\": " + randomizeLocation() + ",\"longtitude\": " + randomizeLocation() + "}")
            .log().all()
        .when()
            .post(url("users/jacek"))
        .then()
            .log().all();
    }

    private String randomizeLocation() {
        return RANDOM.nextInt(90) + "." + StringUtils.rightPad(Integer.toString(RANDOM.nextInt(999999)), 6, ' ');
    }

}
