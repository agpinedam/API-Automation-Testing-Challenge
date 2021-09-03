package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.HookSessionId;

import static io.restassured.RestAssured.given;

public class Movies extends HookSessionId {


    @Test
    public void getMovieDetails(){
        Response response =given()
                .when()
                .get("/movie/"+movieId+"?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertMovieId(response,movieId);
        confirmation.assertMovieName(response);
        confirmation.assertMovieOverview(response);
    }
    @Test
    public void rateMovie(){
        String json = objectBodyFactory.jsonRateMovie("10.0");
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/movie/"+movieId+"/rating?api_key="+apikey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,1);
        confirmation.assertStatusMessage(response,"Success.");
    }


}
