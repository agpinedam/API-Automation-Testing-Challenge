package utils;

import com.google.gson.Gson;
import models.RequestList;
import models.RequestLogin;

public class ObjectBodyFactory {


    public String jsonLogin(String user,String password,String token){
        RequestLogin requestLogin = new RequestLogin(user,password,token);
        System.out.println();
        return new Gson().toJson(requestLogin);
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
}
