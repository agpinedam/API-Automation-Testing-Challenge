import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Hooks;

import static io.restassured.RestAssured.given;

public class List extends Hooks {

    boolean confirm=true;


    @Test
    public void createList(){
        String sessionId= create.SessionId(user,password,apikey);
        System.out.println(sessionId);
        String json=create.jsonList("Create list aaa","Create list form java asda");

        Response response = given().contentType("application/json").body(json).when()
                .post("/list?api_key="+apikey+"&session_id="+sessionId).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertStatusMessage(response,"The item/record was created successfully.");
        confirmation.AssertStatusCode(response,1);
        confirmation.AssertListId(response);
        System.out.println(create.listId(response));
        System.out.println("Created list");
    }
    @Test
    public void addMovieToAList(){
        String sessionId= create.SessionId(user,password,apikey);
        Response createList = create.emptyList(user,password,apikey);
        String json = create.jsonMediaId("129");
        Response response = given().contentType("application/json").body(json).when()
                .post("/list/"+create.listId(createList)+"/add_item?api_key="+apikey+"&session_id="+sessionId).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertStatusCode(response,12);
        confirmation.AssertStatusMessage(response,"The item/record was updated successfully.");
        System.out.println("Add a move");
    }
    @Test
    public void getListDetails(){
        String sessionId= create.SessionId(user,password,apikey);
        int listId = create.idListWithMovie(user,password,apikey,sessionId);
        System.out.println(given().when().get("/list/"+listId+"?api_key="+apikey).then().log().body());
    }
    @Test
    public void clearList(){
        String sessionId= create.SessionId(user,password,apikey);
        int listId = create.idListWithMovie(user,password,apikey,sessionId);
        Response response = given().when().post("/list/"+listId+"/clear?api_key="+apikey+"&session_id="+sessionId+"&confirm="+confirm)
                .then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertStatusCode(response,12);
        confirmation.AssertStatusMessage(response,"The item/record was updated successfully.");
        System.out.println("Clear list");
    }
    @Test
    public void deletedList(){
        String sessionId= create.SessionId(user,password,apikey);
        int listId = create.idListWithMovie(user,password,apikey,sessionId);
        Response response= given().when().delete("/list/"+listId+"?api_key="+apikey+"&session_id="+sessionId).then().extract().response();
        confirmation.AssertSuccessTrue(response);
        confirmation.AssertStatusCode(response,12);
        confirmation.AssertStatusMessage(response,"The item/record was updated successfully.");
    }

}
