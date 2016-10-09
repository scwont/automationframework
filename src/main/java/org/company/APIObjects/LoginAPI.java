package org.company.APIObjects;


import org.company.Services.RESTRequest;

public class LoginAPI {

    private static String outputJson = "";
    private static int responseCode = 0;

    public void submitLoginViaAPI(){
        String loginJSON = "{\"username\":\"wibble@testemail.test\",\"password\": \"password\"}";
        String loginURL = "https://aws-tnz-api-v2.xstream.dk/family-accounts/login";
        RESTRequest loginPost = new RESTRequest(loginURL, loginJSON, "POST");
        outputJson = loginPost.sendRequest();
        responseCode = loginPost.getResponseCode();
    }

    public static String getOutputJson() {
        return outputJson;
    }

    public static int getResponseCode() {
        return responseCode;
    }
}
