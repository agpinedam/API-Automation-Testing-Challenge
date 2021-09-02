import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Hooks;

import static io.restassured.RestAssured.given;

public class List extends Hooks {

    String name ="desde java arreglado";
    Boolean confirm = true;
    String sessionId ="";

    @Test
    public void createList(){
        String sessionId = create.SessionId(user,password,apikey);
        System.out.println(sessionId);
        String json=create.jsonList("Create list","Create list form java");
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when()
                .post("/list?api_key="+apikey+"&session_id="+sessionId).then().log().body());
    }
    @Test
    public void addMovieToAList(){
        String url ="https://api.themoviedb.org/3/list?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"name\": \"List add Movie\",\n"+
                "  \"description\": \"Cualquier cosa\",\n"+
                "  \"language\": \"en\"\n}";
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        Response response1 = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        int listId = response1.jsonPath().getInt("list_id");
        String json2 ="{\n"+
                "  \"media_id\": \"238-the-godfather\"\n}";
        String url2 ="https://api.themoviedb.org/3/list/"+listId+"/add_item?api_key="+apikey+"&session_id="+sessionId;
        System.out.println(given().contentType("application/json").body(json2).when().post(url2).then().log().body());
    }
    @Test
    public void getListDetails(){
        String url ="https://api.themoviedb.org/3/list?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"name\": \"List add Movie\",\n"+
                "  \"description\": \"Cualquier cosa\",\n"+
                "  \"language\": \"en\"\n}";
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        Response response1 = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        int listId = response1.jsonPath().getInt("list_id");
        String json2 ="{\n"+
                "  \"media_id\": \"238-the-godfather\"\n}";
        String url2 ="https://api.themoviedb.org/3/list/"+listId+"/add_item?api_key="+apikey+"&session_id="+sessionId;
        System.out.println(given().contentType("application/json").body(json2).when().post(url2).then().log().body());
        String url3 = "https://api.themoviedb.org/3/list/"+listId+"?api_key="+apikey;
        System.out.println(given().when().get(url3).then().log().body());
    }
    @Test
    public void clearList(){
        String url ="https://api.themoviedb.org/3/list?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"name\": \"List clean Movie\",\n"+
                "  \"description\": \"Cualquier cosa\",\n"+
                "  \"language\": \"en\"\n}";
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        Response response1 = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        int listId = response1.jsonPath().getInt("list_id");
        String json2 ="{\n"+
                "  \"media_id\": \"238-the-godfather\"\n}";
        String url2 ="https://api.themoviedb.org/3/list/"+listId+"/add_item?api_key="+apikey+"&session_id="+sessionId;
        System.out.println(given().contentType("application/json").body(json2).when().post(url2).then().log().body());
        String url3 = "https://api.themoviedb.org/3/list/"+listId+"/clear?api_key="+apikey+"&session_id="+sessionId+"&confirm="+confirm;
        System.out.println(given().when().post(url3).then().log().body());
    }
    @Test
    public void deletedList(){
        String url ="https://api.themoviedb.org/3/list?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"name\": \"List deleted List\",\n"+
                "  \"description\": \"Cualquier cosa\",\n"+
                "  \"language\": \"en\"\n}";
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
        Response response1 = given().contentType("application/json").body(json).when().post(url).then().extract().response();
        int listId = response1.jsonPath().getInt("list_id");
        String url3 = "https://api.themoviedb.org/3/list/"+listId+"?api_key="+apikey+"&session_id="+sessionId+"&confirm="+confirm;
        System.out.println(given().when().delete(url3).then().log().body());
    }
}
