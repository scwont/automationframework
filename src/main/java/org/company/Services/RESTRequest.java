package org.company.Services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class RESTRequest {

    private String URL;
    private List<List<String>> properties;
    private String requestType;
    private String JSONRequest;
    private int responseCode;
    private String authentication = "";
    private String username;
    private String password;
    private String cookie = "";
    private Map<String, List<String>> headers;
    private HttpURLConnection conn;

    public RESTRequest(String URL, String JSONRequest, List<List<String>> properties, String requestType) {
        this.URL = URL;
        this.JSONRequest = JSONRequest;
        this.requestType = requestType;
        this.properties = properties;
    }

    public RESTRequest(String URL, String JSONRequest, String requestType) {
        this.URL = URL;
        this.JSONRequest = JSONRequest;
        this.requestType = requestType;
    }

    public RESTRequest(String URL, List<List<String>> properties, String requestType) {
        this.URL = URL;
        this.requestType = requestType;
        this.properties = properties;
    }

    public RESTRequest(String URL, String requestType) {
        this.URL = URL;
        this.requestType = requestType;
    }

    public String sendRequest() {
        String outputJSON = "";
        try {

            java.net.URL url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();



            if (properties != null) {
                for (List<String> property : properties) {
                    conn.addRequestProperty(property.get(0), property.get(1));
                }
            }

            //checks for authentication settings
            if (authentication.equals("Basic")) {

                String userpass = username + ":" + password;
                String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

                conn.setRequestProperty("Authorization", basicAuth);
            } else if (authentication.equals("Bearer")) {
                conn.setRequestProperty("Authorization", "Bearer " + username);
            }

            //we only do an outputstream if we are posting otherwise we get 404
            if ((requestType.toUpperCase().equals("POST")) || (requestType.toUpperCase().equals("PUT"))) {
                conn.setRequestMethod(requestType);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();

                if (JSONRequest != null) {
                    os.write(JSONRequest.getBytes());
                } else {
                    os.write(0);
                }
                os.flush();
            } else if (requestType.toUpperCase().equals("PATCH")) {
                //getting around the PATCH issue
                //TODO later on, handle headers etc
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPatch httpPatch = new HttpPatch(new URI(URL));
                StringEntity params = new StringEntity(JSONRequest, ContentType.APPLICATION_JSON);
                httpPatch.setEntity(params);
                CloseableHttpResponse response = httpClient.execute(httpPatch);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                System.out.println("PATCH response:  " + responseString);
                return responseString;

            } else {
                conn.setRequestMethod(requestType);
                conn.setDoOutput(false);
            }


            //this does the actual request
            this.responseCode = conn.getResponseCode();

            //get cookies in response
            this.cookie = conn.getHeaderField("Set-Cookie");

            this.headers = conn.getHeaderFields();

            BufferedReader in = null;

            if ((conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) && (conn.getResponseCode() != HttpURLConnection.HTTP_OK) && (conn.getResponseCode() != 302)) {

                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode() + " " + conn.getResponseMessage() + " " + conn.getContent());
            } else if (conn.getHeaderField("Content-Encoding") != null && conn.getHeaderField("Content-Encoding").contains("gzip")) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            String output;
            System.out.println("Output from Server ....");
            while ((output = in.readLine()) != null) {
                System.out.println(output);
                outputJSON += output;
            }
            System.out.println("\r\n\r\n");
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("\r\nResponse code: " + responseCode);
            System.out.println(e.getMessage());


        }

        return outputJSON;
    }


    public void setAuthentication(String type, String username, String password) {
        this.authentication = type;
        this.username = username;
        this.password = password;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public String getCookie() {
        return this.cookie;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public String getHeader(String header) {
        return conn.getHeaderField(header);
    }

}


