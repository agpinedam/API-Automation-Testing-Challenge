package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.HookSessionId;

import static io.restassured.RestAssured.given;

public class TV extends HookSessionId {
    @Test
    public void getTvDetails(){
        Response response =given()
                .when()
                .get("//tv/"+tvId+"?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertTvOverview(response);
        confirmation.assertTvName(response);
    }
}
