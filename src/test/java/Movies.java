import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Movies {
    String apikey="7c20e68718e31a85764645078fddd237";
    String movieID= "372058";
    String token ="588c6255da7b10a295b7ec499e4b305d63b034aa";
    String sessionId="11c7e77e7e8c727ae611a1cc312233119740f040";
    String name ="desde java";
    Boolean confirm = true;

    @Test
    public void getDetails(){
        String url ="https://api.themoviedb.org/3/movie/"+movieID+"?api_key="+apikey;
        System.out.println(given().when().get(url).then().log().body());
    }
    @Test
    public void rateMovie(){
        String url ="https://api.themoviedb.org/3/movie/"+movieID+"/rating?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"value\": \"10.0\"\n}";
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
    }
    @Test
    public void delaterateMovie(){
        String url ="https://api.themoviedb.org/3/movie/"+movieID+"/rating?api_key="+apikey+"&session_id="+sessionId;
        //           https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>&session_id=hhkhkj
        String json ="{\n"+
                "  \"value\": \"10.0\"\n}";
        System.out.println(given().contentType("application/json").body(json).when().delete(url).then().log().body());
    }
}
