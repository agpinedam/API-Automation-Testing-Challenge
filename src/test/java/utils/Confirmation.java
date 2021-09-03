package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class Confirmation {

    public void assertSuccessTrue(Response response){
        boolean success = response.jsonPath().getBoolean("success");
        Assert.assertTrue(success,"RequestList should be successful");
    }
    public void assertGuestSessionId(Response response){
        String guestSessionId = response.jsonPath().getString("guest_session_id");
        Assert.assertNotNull(guestSessionId);
    }
    public void assertDateExpires(Response response){
        String dateExpires = response.jsonPath().getString("expires_at");
        Assert.assertNotNull(dateExpires);
    }
    public void assertRequestToken(Response response){
        String requestToken = response.jsonPath().getString("request_token");
        Assert.assertNotNull(requestToken);
    }
    public void assertSessionId(Response response){
        String sessionId = response.jsonPath().getString("session_id");
        Assert.assertNotNull(sessionId);
    }
    public void assertStatusMessage(Response response, String message){
        String statusMessage = response.jsonPath().getString("status_message");
        Assert.assertEquals(statusMessage,message);
    }
    public void assertStatusCode(Response response, int statusCode){
        int status= response.jsonPath().getInt("status_code");
        Assert.assertEquals(status,statusCode);

    }
    public void assertListId(Response response){
        String listId= response.jsonPath().getString("list_id");
        Assert.assertNotNull(listId);

    }
    public void assertCreatedBy(Response response, String user){
        String createdBy= response.jsonPath().getString("created_by");
        Assert.assertEquals(createdBy,user);
    }
    public void assertItemCount(Response response){
        int itemCount = response.jsonPath().getInt("item_count");
        Assert.assertEquals(itemCount,1);
    }
    public void assertListName(Response response){
        String listName = response.jsonPath().getString("name");
        Assert.assertNotNull(listName);
    }
    public void assertMovieId(Response response, String movieId){
        String id = response.jsonPath().getString("id");
        Assert.assertEquals(id,movieId);
    }
    public void assertMovieName(Response response){
        String movieName = response.jsonPath().getString("title");
        Assert.assertNotNull(movieName);
    }
    public void AssertMovieOverview(Response response){
        String movieOverview = response.jsonPath().getString("overview");
        Assert.assertNotNull(movieOverview);
    }
}
