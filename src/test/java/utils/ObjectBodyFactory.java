import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ObjectBodyFactory {

    public String token(String apiKey){
        String token =given().when().get("https://api.themoviedb.org/3/authentication/token/new?api_key="+apiKey)
                .then().extract().jsonPath().getString("request_token");
        return token;
    }
    public String jsonLogin(String user,String password,String token){
        AuthenticationBody authenticationBody = new AuthenticationBody(password,token,user);
        return new Gson().toJson(authenticationBody);
    }
    public String jsonToken(String token){
        String json ="{\n"+
                "  \"request_token\": \""+token+"\"\n}";
        return json;
    }
    public String jsonSessionId(String sessionId){
        String json ="{\n"+
                "  \"session_id\": \""+sessionId+"\"\n}";
        return json;
    }
    public String jsonList(String nameList,String description){
        RequestList list = new RequestList(nameList,description,"en");
        return new Gson().toJson(list);
    }
    public String jsonMediaId(String mediaId){
        String json ="{\n"+
                "  \"media_id\": \""+mediaId+"\"\n}";
        return json;
    }
    public String jsonRateMovie(String value){
        String json ="{\n"+
                "  \"value\": \"10.0\"\n}";
        return json;
    }
    public String sessionWithLogin(String user, String password , String apiKey){
        String token = token(apiKey);
        String json = jsonLogin(user,password,token);
        given().contentType("application/json").body(json).when()
                .post("https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key="+apiKey)
                .then();
        return token;
    }
    public String sessionId(String user, String password, String apiKey){
        String token = sessionWithLogin(user,password,apiKey);
        String json = jsonToken(token);
        Response response = given().contentType("application/json")
                .body(json).when().post("https://api.themoviedb.org/3/authentication/session/new?api_key="+apiKey)
                .then().extract().response();
        String sessionId=response.jsonPath().getString("session_id");
        return sessionId;
    }
    public Response emptyList(String user,String password, String apiKey){
        String sessionId = sessionId(user,password,apiKey);
        String json = jsonList("ObjectBodyFactory List Java o.o ",
                new Faker().letterify("random ??????? description ??????? ??????????"));
        Response response = given()
                .contentType("application/json").body(json).when()
                .post("https://api.themoviedb.org/3/list?api_key="+apiKey+"&session_id="+sessionId).then().extract().response();
        return response;
    }
    public int idListWithMovie(String user,String password, String apiKey,String sessionId){
        Response response =emptyList(user,password,apiKey);
        int listId= response.jsonPath().getInt("list_id");
        String json = jsonMediaId("129");
        given().contentType("application/json").body(json).when()
                .post("https://api.themoviedb.org/3/list/"+listId+"/add_item?api_key="+apiKey+"&session_id="+sessionId).then();
        return listId;
    }
    public int listId(Response response){
        return response.jsonPath().getInt("list_id");
    }
}
