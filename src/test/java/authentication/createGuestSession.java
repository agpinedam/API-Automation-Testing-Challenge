package authentication;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Example;


public class createGuestSession {
    Example data = new Example();
    @Given(": I have an valid api key")
    public void iHaveAnValidApiKey() {
        String key = data.getApikey();
        System.out.println(key);
    }

    @When(": I send a query with the api key")
    public void iSendAQueryWithTheApiKey() {
    }

    @Then(": I get a confirmation")
    public void iGetAConfirmation() {
    }

    @And(": A guest session ID")
    public void aGuestSessionID() {
        data.getSessionID();
    }

    @And(": A date of session ID expiration")
    public void aDateOfSessionIDExpiration() {
    }

}
