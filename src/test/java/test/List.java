package test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.HookSessionId;

import static io.restassured.RestAssured.given;

public class List extends HookSessionId {

    @Test
    public void createList(){
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
        Response createList = requestHelpers.emptyList(user,password,apikey);
        String json = objectBodyFactory.jsonMediaId("129");
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/list/"+ requestHelpers.listId(createList)+"/add_item?api_key="+apikey+"&session_id="+sessionId)
                .then()
                .extract()
                .response();
        confirmation.assertSuccessTrue(response);
        confirmation.assertStatusCode(response,12);
        confirmation.assertStatusMessage(response,"The item/record was updated successfully.");
    }
    @Test
    public void getListDetails(){
        int listId = requestHelpers.idListWithMovie(user,password,apikey,sessionId);
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
        int listId = requestHelpers.idListWithMovie(user,password,apikey,sessionId);
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
        int listId = requestHelpers.idListWithMovie(user,password,apikey,sessionId);
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
