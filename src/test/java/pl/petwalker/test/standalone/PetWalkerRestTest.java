package pl.petwalker.test.standalone;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static pl.petwalker.test.standalone.TestUrl.url;

public class PetWalkerRestTest {

    @Test
    public void fetchUsers() {
        expect()
            .statusCode(200)
        .given()
            .log().all()
        .when()
            .get(url("users"))
        .then()
            .log().all();
    }

    @Test
    public void fetchUserData() {

    }

}
