package utils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class Hooks {
    protected String apikey;
    protected String user;
    protected String password;
    protected Confirmation confirmation = new Confirmation();
    protected Create create = new Create();

    @BeforeSuite
    public void setup() throws IOException {
        Credentials credentials = Credentials.getCredentials();
        apikey=credentials.getApiKey();
        user =credentials.getUserName();
        password =credentials.getPassword();
        RestAssured.baseURI="https://api.themoviedb.org/3";
        System.out.println("Setup de la suit");
    }
}
