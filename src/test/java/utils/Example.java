package utils;

import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class Example {
    private String apikey = "7c20e68718e31a85764645078fddd237";
    private String url = "https://api.themoviedb.org/3/authentication/guest_session/new?api_key=";

    public String getApikey() {
        return apikey;
    }
    public String getSessionID(){
        String guestSessionID =given().when().get(url+apikey).then().extract().jsonPath().getString("guest_session_id");
        Assert.assertNotNull(guestSessionID);
        return guestSessionID;
    }
}
