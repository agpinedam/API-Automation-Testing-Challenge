package utils;

import org.testng.annotations.BeforeClass;

public class HookSessionId extends Hooks{
    protected String sessionId;
    protected String movieId;
    protected String tvId;
    protected boolean confirm;

    @BeforeClass
    public void setupSessionId(){
        sessionId=requestHelpers.sessionId(user,password,apikey);
        movieId= "372058";
        tvId ="65648-kaichou-wa-maid-sama";
        confirm=true;
    }


}
