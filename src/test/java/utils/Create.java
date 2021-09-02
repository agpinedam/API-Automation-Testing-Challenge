package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Create {

    public String Token(String apiKey){
        String token =given().when().get("https://api.themoviedb.org/3/authentication/token/new?api_key="+apiKey)
                .then().extract().jsonPath().getString("request_token");
        return token;
    }
    public String jsonLogin(String user,String password,String token){
        String json="{\n"+
                "  \"username\": \""+user+"\",\n"+
                "  \"password\": \""+password+"\",\n"+
                "  \"request_token\": \""+token+"\"\n}";
        return json;
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
        String json ="{\n"+
                "  \"name\": \""+nameList+"\",\n"+
                "  \"description\": \""+description+"\",\n"+
                "  \"language\": \"en\"\n}";
        return json;
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
    public String SessionWithLogin(String user,String password ,String apiKey){
        String token = Token(apiKey);
        String json = jsonLogin(user,password,token);
        given().contentType("application/json").body(json).when()
                .post("https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key="+apiKey)
                .then();
        return token;
    }
    public String SessionId(String user, String password, String apiKey){
        String token =SessionWithLogin(user,password,apiKey);
        String json = jsonToken(token);
        Response response = given().contentType("application/json")
                .body(json).when().post("https://api.themoviedb.org/3/authentication/session/new?api_key="+apiKey)
                .then().extract().response();
        String sessionId=response.jsonPath().getString("session_id");
        return sessionId;
    }
    public Response emptyList(String user,String password, String apiKey){
        String sessionId = SessionId(user,password,apiKey);
        String json = jsonList("Create List Java o.o ","This list was created in an automation test");
        Response response = given().contentType("application/json").body(json).when()
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
