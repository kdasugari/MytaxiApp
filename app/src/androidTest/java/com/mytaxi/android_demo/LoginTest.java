package com.mytaxi.android_demo;


import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.espresso.action.ViewActions;
import android.util.Log;
import com.mytaxi.android_demo.activities.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test

    public void http_request() throws Exception {
        try {
            LoginTest obj = new LoginTest();
            obj.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void verifyLogin (String username, String password) throws InterruptedException {

        Log.e("@Test","Performing login success test");
        //Login Test
        onView(withId(R.id.edt_username)).perform(typeText(username));
        onView(withId(R.id.edt_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Log.e("@Test","Successfully logged in");
        //Performing Search
        onView(withId(R.id.textSearch)).perform(click()).perform(typeText("sa"));
        //Click on second Name from the search list
        onView(withText("Sarah Scott")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        //Call driver
        Log.e("@Test","Calling the Driver");
        onView(withId(R.id.fab)).perform(ViewActions.click());
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
        verifyLogin(username,password);
    }

}



