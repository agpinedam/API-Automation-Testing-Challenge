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
        String dateExpires = response.jsonPath().getString("expires_at");
        Assert.assertNotNull(dateExpires);
    }
    public void AssertRequestToken (Response response){
        String requestToken = response.jsonPath().getString("request_token");
        Assert.assertNotNull(requestToken);
    }
    public void AssertSessionId (Response response){
        String sessionId = response.jsonPath().getString("session_id");
        Assert.assertNotNull(sessionId);
    }
    public void AssertStatusMessage(Response response, String message){
        String statusMessage = response.jsonPath().getString("status_message");
        Assert.assertEquals(statusMessage,message);
    }
    public void AssertStatusCode(Response response, int statusCode){
        int status= response.jsonPath().getInt("status_code");
        Assert.assertEquals(status,statusCode);

    }
    public void AssertListId(Response response){
        String listId= response.jsonPath().getString("list_id");
        Assert.assertNotNull(listId);

    }
    public void AssertCreatedBy(Response response,String user){
        String createdBy= response.jsonPath().getString("created_by");
        Assert.assertEquals(createdBy,user);
    }
    public void AsserItemCount(Response response){
        int itemCount = response.jsonPath().getInt("item_count");
        Assert.assertEquals(itemCount,1);
    }
    public void AssertListName(Response response){
        String listName = response.jsonPath().getString("name");
        Assert.assertNotNull(listName);
    }
    public void AssertMovieId(Response response,String movieId){
        String id = response.jsonPath().getString("id");
        Assert.assertEquals(id,movieId);
    }
    public void AssertMovieName(Response response){
        String movieName = response.jsonPath().getString("title");
        Assert.assertNotNull(movieName);
    }
    public void AssertMovieOverview(Response response){
        String movieOverview = response.jsonPath().getString("overview");
        Assert.assertNotNull(movieOverview);
    }
}
