import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Approve;

import static io.restassured.RestAssured.given;

public class GetSessionID {
    Approve approve = new Approve();
    String apikey="7c20e68718e31a85764645078fddd237";
    String token1;

    @Test
    public void createSession(){
        String ur ="https://api.themoviedb.org/3/authentication/token/new?api_key="+apikey;
        Response response1 = given().when().get(ur).then().extract().response();
        token1=response1.jsonPath().getString("request_token");
        System.out.println(token1);
        String approveUrl ="https://www.themoviedb.org/authenticate/"+token1;
        approve.setUp(approveUrl);
    }
    @Test
    public void createSessionId(){
        String url ="https://api.themoviedb.org/3/authentication/session/new?api_key="+apikey;
        String json ="{\n"+
                "  \"request_token\": \""+token1+"\"\n}";
        System.out.println(json);
        //Response response3 = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
    }
}
