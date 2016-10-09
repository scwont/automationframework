package AutomationUnitTests;

import org.company.Services.RESTRequest;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ServiceTest {

    @Test
    public void ServiceTest() {
        String loginJSON = "{\"username\":\"wibble@testemail.test\",\"password\": \"password\"}";
        String loginURL = "https://aws-tnz-api-v2.xstream.dk/family-accounts/login";
        RESTRequest loginPost = new RESTRequest(loginURL, loginJSON, "POST");
        String output = loginPost.sendRequest();
        assertEquals(404, loginPost.getResponseCode());
    }


}
