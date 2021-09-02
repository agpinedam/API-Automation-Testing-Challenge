package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class Confirmation {

    public void AssertSuccessTrue(Response response){
        boolean success = response.jsonPath().getBoolean("success");
        Assert.assertTrue(success);
    }
    public void AssertSuccessFalse(Response response){
        boolean success = response.jsonPath().getBoolean("success");
        Assert.assertFalse(success);
    }
    public void AssertGuestSessionId (Response response){
        String guestSessionId = response.jsonPath().getString("guest_session_id");
        Assert.assertNotNull(guestSessionId);
    }
    public void AssertDateExpires (Response response){
        String guestSessionId = response.jsonPath().getString("expires_at");
        Assert.assertNotNull(guestSessionId);
    }
    public void AssertRequestToken (Response response){
        String guestSessionId = response.jsonPath().getString("request_token");
        Assert.assertNotNull(guestSessionId);
    }
    public void AssertSessionId (Response response){
        String guestSessionId = response.jsonPath().getString("session_id");
        Assert.assertNotNull(guestSessionId);
    }
}
