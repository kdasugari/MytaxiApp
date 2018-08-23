package com.mytaxi.android_demo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http_Request {

    public void http_request() throws Exception {
        try {
            Http_Request obj = new Http_Request();
            obj.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void call_me() throws Exception {
        String url = "https://randomuser.me/api/?seed=a1f30d446f820665";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Chrome/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println(myResponse);
        JSONArray results = myResponse.getJSONArray("results");
        JSONObject login = results.getJSONObject(0).getJSONObject("login");

        String username = login.getString("username");
        String password = login.getString("password");
        LoginTest Login = new LoginTest();

          }

}
