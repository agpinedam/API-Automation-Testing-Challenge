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
}
