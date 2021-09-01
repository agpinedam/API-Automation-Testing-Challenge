import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class List {
    String apikey="7c20e68718e31a85764645078fddd237";
    String user ="angie.giceth";
    String pasword ="123456";
    String token ="588c6255da7b10a295b7ec499e4b305d63b034aa";
    String sessionId="d9e6c2323368d209059e690382aaee02b22948b4";
    String name ="desde java";

    @Test
    public void createList(){
        String url ="https://api.themoviedb.org/3/list?api_key="+apikey+"&session_id="+sessionId;
        String json ="{\n"+
                "  \"name\": \""+name+"\",\n"+
                "  \"description\": \"Qual quiercosa\",\n"+
                "  \"language\": \"en\"\n}";
        System.out.println(json);
        System.out.println(given().contentType("application/json").body(json).when().post(url).then().log().body());
    }
}
