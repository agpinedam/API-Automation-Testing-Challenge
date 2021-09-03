import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Movies extends Hooks {

    String movieId= "372058";

    @Test
    public void getMovieDetails(){
        Response response =given().when().get("/movie/"+movieId+"?api_key="+apikey).then().extract().response();
        confirmation.assertMovieId(response,movieId);
        confirmation.assertMovieName(response);
        confirmation.AssertMovieOverview(response);
    }
    @Test
    public void rateMovie(){
        String sessionId = objectBodyFactory.sessionId(user,password,apikey);
        String json = objectBodyFactory.jsonRateMovie("10.0");
        Response response = given().contentType("application/json").body(json)
                .when().post("/movie/"+movieId+"/rating?api_key="+apikey+"&session_id="+sessionId).then().extract().response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,1);
        confirmation.assertStatusMessage(response,"Success.");
    }
    @Test
    public void deleteRateMovie(){
        String sessionId = objectBodyFactory.sessionId(user,password,apikey);
        String url ="https://api.themoviedb.org/3/movie/"+movieId+"/rating?api_key="+apikey+"&session_id="+sessionId;
        //           https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>&session_id=hhkhkj
        String json ="{\n"+
                "  \"value\": \"10.0\"\n}";
        System.out.println(given().contentType("application/json").body(json).when().delete(url).then().log().body());
    }
}
