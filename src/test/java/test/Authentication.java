package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Hooks;

import static io.restassured.RestAssured.given;


public class Authentication extends Hooks {

    @Test
    public void createGuestSession(){
        Response response = given()
                .when()
                .get("/authentication/guest_session/new?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertGuestSessionId(response);
        confirmation.assertDateExpires(response);
        System.out.println("ObjectBodyFactory guest");
    }
    @Test
    public void createRequestToken(){
        Response response = given()
                .when()
                .get("/authentication/token/new?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertDateExpires(response);
        confirmation.assertRequestToken(response);
        System.out.println("Request token");
    }
    @Test
    public void createSessionWithLogin(){
        String token= requestHelpers.token(apikey);
        System.out.println(token);
        String json = objectBodyFactory.jsonLogin(user,password,token);
        System.out.println(json);
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/authentication/token/validate_with_login?api_key="+apikey)
                .then()
                .extract()
                .response();
        System.out.println(response.then().log().body());
        confirmation.assertSuccessTrue(response);
        confirmation.assertDateExpires(response);
        confirmation.assertRequestToken(response);
    }
    @Test
    public void createSession(){
        String token = requestHelpers.sessionWithLogin(user,password,apikey);
        String json = objectBodyFactory.jsonToken(token);
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/authentication/session/new?api_key="+apikey)
                .then()
                .extract()
                .response();
        System.out.println(response.then().log().body());
        confirmation.assertSuccessTrue(response);
        confirmation.assertSessionId(response);
    }
    @Test
    public void deletedSession(){
        String sessionId = requestHelpers.sessionId(user,password,apikey);
        String json = objectBodyFactory.jsonSessionId(sessionId);
        Response response = given()
                .contentType("application/json")
                .body(json).when()
                .delete("/authentication/session?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        System.out.println("Deleted session");
    }
}
