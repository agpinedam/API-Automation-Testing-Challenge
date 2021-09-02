import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Hooks;

import static io.restassured.RestAssured.given;


public class Authentication extends Hooks {

    @Test
    public void createGuestSession(){
        Response response = given().when().get("/authentication/guest_session/new?api_key="+apikey).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertGuestSessionId(response);
        confirmation.AssertDateExpires(response);
        System.out.println("Create guest");
    }
    @Test
    public void createRequestToken(){
        Response response = given().when().get("/authentication/token/new?api_key="+apikey).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertDateExpires(response);
        confirmation.AssertRequestToken(response);
        System.out.println("Request token");
    }
    public void createToken(){
        System.out.println("Se ha creado el token");
    }
    //Create request token
    @Test
    public void createSessionWithLogin(){
        String token= create.Token(apikey);
        String json = create.jsonLogin(user,password,token);
        Response response = given().contentType("application/json").body(json).when()
                .post("/authentication/token/validate_with_login?api_key="+apikey).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertDateExpires(response);
        confirmation.AssertRequestToken(response);
    }
    @Test
    public void createSession(){
        String token = create.SessionWithLogin(user,password,apikey);
        String json = create.jsonToken(token);
        Response response = given().contentType("application/json").body(json).when().post("/authentication/session/new?api_key="+apikey)
                .then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertSessionId(response);
        System.out.println("Create session");
    }
    @Test
    public void deletedSession(){
        String sessionId = create.SessionId(user,password,apikey);
        String json = create.jsonSessionId(sessionId);
        Response response = given().contentType("application/json").body(json).when()
                .delete("/authentication/session?api_key="+apikey).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        System.out.println("Deleted session");
    }
}
