package utils;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestHelpers extends ObjectBodyFactory{
    private String domain="https://api.themoviedb.org/3";

    public String token(String apiKey){
        String token =given()
                .when()
                .get(domain+ "/authentication/token/new?api_key="+apiKey)
                .then()
                .extract()
                .jsonPath()
                .getString("request_token");
        return token;
    }
    public String sessionWithLogin(String user, String password , String apiKey){
        String token = token(apiKey);
        String json = jsonLogin(user,password,token);
        Response response= given()
                .contentType("application/json")
                .body(json).when()
                .post(domain+"/authentication/token/validate_with_login?api_key="+apiKey)
                .then()
                .extract()
                .response();
        return token;
    }
    public String sessionId(String user, String password, String apiKey){
        String token = sessionWithLogin(user,password,apiKey);
        String json = jsonToken(token);
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post(domain+"/authentication/session/new?api_key="+apiKey)
                .then()
                .extract()
                .response();
        String sessionId=response
                .jsonPath()
                .getString("session_id");
        return sessionId;
    }
    public Response emptyList(String user,String password, String apiKey){
        String sessionId = sessionId(user,password,apiKey);
        String json = jsonList("ObjectBodyFactory test.List Java o.o ",
                new Faker().letterify("random ??????? description ??????? ??????????"));
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post(domain+"/list?api_key="+apiKey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        return response;
    }
    public int idListWithMovie(String user,String password, String apiKey,String sessionId){
        Response response =emptyList(user,password,apiKey);
        int listId= response.jsonPath().getInt("list_id");
        String json = jsonMediaId("129");
        given().contentType("application/json").body(json).when()
                .post(domain+"/list/"+listId+"/add_item?api_key="+apiKey+"&session_id="+sessionId).then();
        return listId;
    }
    public int listId(Response response){
        return response.jsonPath().getInt("list_id");
    }
}
