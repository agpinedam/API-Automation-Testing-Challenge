import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class Authentication {
    String apikey="7c20e68718e31a85764645078fddd237";
    String user ="angie.giceth";
    String pasword ="123456";
    //String tok = "465129daf55d89baef26b19aa2091c7a3e6d99a7";

    @Test
    public void createGuestSession(){
        String url ="https://api.themoviedb.org/3/authentication/guest_session/new?api_key="+apikey;
        Response response = given().when().get(url).then().extract().response();
        System.out.println(given().when().get(url).then().log().body());
        Boolean success = response.jsonPath().getBoolean("success");
        Assert.assertTrue(success);
        String guestSessionID =response.jsonPath().getString("guest_session_id");
        assertNotNull(guestSessionID);
        String expiresAt=response.jsonPath().getString("expires_at");
        assertNotNull(expiresAt);
    }
    @Test
    public void createRequestToken(){
        String url ="https://api.themoviedb.org/3/authentication/token/new?api_key="+apikey;
        Response response = given().when().get(url).then().extract().response();
        System.out.println(given().when().get(url).then().log().body());
        String expiresAt = response.jsonPath().getString("expires_at");
        assertNotNull(expiresAt);
        String token =response.jsonPath().getString("request_token");
        System.out.println(token);
        assertNotNull(token);
    }
    @Test
    public void createSessionWithLoggin(){
        String ur ="https://api.themoviedb.org/3/authentication/token/new?api_key="+apikey;
        Response response1 = given().when().get(ur).then().extract().response();
        String token=response1.jsonPath().getString("request_token");
        assertNotNull(token);
        //createSessionWithLoggin
        String json ="{\n"+
                "  \"username\": \""+user+"\",\n"+
                "  \"password\": \""+pasword+"\",\n"+
                "  \"request_token\": \""+token+"\"\n}";
        System.out.println(json);
        String url ="https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key="+apikey;
        Response response = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        String expiresAt = response.jsonPath().getString("expires_at");
        assertNotNull(expiresAt);
        String respondToken = response.jsonPath().getString("request_token");
        assertEquals(respondToken,token);
    }
    @Test
    public void createSession(){
        String ur ="https://api.themoviedb.org/3/authentication/token/new?api_key="+apikey;
        Response response1 = given().when().get(ur).then().extract().response();
        String token=response1.jsonPath().getString("request_token");
        assertNotNull(token);
        //createSessionWithLoggin
        String json ="{\n"+
                "  \"username\": \""+user+"\",\n"+
                "  \"password\": \""+pasword+"\",\n"+
                "  \"request_token\": \""+token+"\"\n}";
        System.out.println(json);
        String url ="https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key="+apikey;
        Response response = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        String expiresAt = response.jsonPath().getString("expires_at");
        assertNotNull(expiresAt);
        String respondToken = response.jsonPath().getString("request_token");
        assertEquals(respondToken,token);
        String u ="https://api.themoviedb.org/3/authentication/session/new?api_key="+apikey;
        String json2 ="{\n"+
                "  \"request_token\": \""+respondToken+"\"\n}";
        System.out.println(json2);
        Response response3 = given().contentType("application/json").body(json2).when().post(u).then().extract().response();
        System.out.println(given().contentType("application/json").body(json2).when().post(u).then().log().body());
        //String guestSessionId = response3.jsonPath().getString("guest_session_id");
    }
    @Test
    public void deletedSession(){
        String guestSessionId = "";
        String url4 ="https://api.themoviedb.org/3/authentication/session?api_key="+apikey;
        String json3 ="{\n"+
                "  \"session_id\": \""+guestSessionId+"\"\n}";
        //System.out.println(json3);
        Response response4 = given().contentType("application/json").body(json3).when().delete(url4).then().extract().response();
        System.out.println(given().contentType("application/json").body(json3).when().delete(url4).then().log().body());
    }
}
