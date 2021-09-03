import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Hooks;

import static io.restassured.RestAssured.given;

public class List extends Hooks {

    boolean confirm=true;

    @Test
    public void createList(){
        String sessionId= objectBodyFactory.sessionId(user,password,apikey);
        System.out.println(sessionId);
        String json= objectBodyFactory.jsonList("ObjectBodyFactory list",
                new Faker().letterify("random ??????? description ??????? ??????????"));
        System.out.println(json);
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/list?api_key="+apikey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        System.out.println(response.then().log().body());
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusMessage(response,"The item/record was created successfully.");
        confirmation.assertStatusCode(response,1);
        confirmation.assertListId(response);
    }
    @Test
    public void addMovieToAList(){
        String sessionId= objectBodyFactory.sessionId(user,password,apikey);
        Response createList = objectBodyFactory.emptyList(user,password,apikey);
        String json = objectBodyFactory.jsonMediaId("129");
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/list/"+ objectBodyFactory.listId(createList)+"/add_item?api_key="+apikey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,12);
        confirmation.assertStatusMessage(response,"The item/record was updated successfully.");
    }
    @Test
    public void getListDetails(){
        String sessionId= objectBodyFactory.sessionId(user,password,apikey);
        int listId = objectBodyFactory.idListWithMovie(user,password,apikey,sessionId);
        Response response = given()
                .when()
                .get("/list/"+listId+"?api_key="+apikey)
                .then()
                .extract()
                .response();
        confirmation.assertCreatedBy(response,user);
        confirmation.assertItemCount(response);
        confirmation.assertListName(response);
        System.out.println("get details");
    }
    @Test
    public void clearList(){
        String sessionId= objectBodyFactory.sessionId(user,password,apikey);
        int listId = objectBodyFactory.idListWithMovie(user,password,apikey,sessionId);
        Response response = given()
                .when()
                .post("/list/"+listId+"/clear?api_key="+apikey+"&session_id="+sessionId+"&confirm="+confirm)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,12);
        confirmation.assertStatusMessage(response,"The item/record was updated successfully.");
    }
    @Test
    public void deletedList(){
        String sessionId= objectBodyFactory.sessionId(user,password,apikey);
        int listId = objectBodyFactory.idListWithMovie(user,password,apikey,sessionId);
        Response response= given()
                .when()
                .delete("/list/"+listId+"?api_key="+apikey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,12);
        confirmation.assertStatusMessage(response,"The item/record was updated successfully.");
    }

}
