package utils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class Hooks {
    protected String apikey;
    protected String user;
    protected String password;
    protected Confirmation confirmation = new Confirmation();
    protected RequestHelpers requestHelpers = new RequestHelpers();
    protected ObjectBodyFactory objectBodyFactory = new ObjectBodyFactory();

    @BeforeClass
    public void setup() throws IOException {
        Credentials credentials = Credentials.getCredentials();
        apikey=credentials.getApiKey();
        user =credentials.getUserName();
        password =credentials.getPassword();
        RestAssured.baseURI="https://api.themoviedb.org/3";
    }
}
