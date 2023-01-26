package com.example.attendancemonitoringsystemupes.apiCalls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.attendancemonitoringsystemupes.activities.AttendanceFaculty;
import com.example.attendancemonitoringsystemupes.activities.StudentAttendanceShow;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoursesTaskApi extends AsyncTask<String, Void, String> {

    private static final String LOGIN_URL = "API URL Getting Timetable Information";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    Context context;
    public CoursesTaskApi(Context c) {
        context=c;
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String password = params[1];

        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            StringBuilder requestParams = new StringBuilder();
            requestParams.append(USERNAME).append("=").append(username).append("&");
            requestParams.append(PASSWORD).append("=").append(password);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(requestParams.toString());
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                Log.e("LoginTask", "POST request failed. Response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            Log.e("LoginTask", "Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // parse the response from the API and check if login was successful
            // result could be a JSON object or a plain string, depends on your API
            // you can check the result and show a message to the user
            // if login success then navigate to next activity otherwise show error message
            //Received json we can parse here
           //Add Code
            String type="";
            Intent intent;
            if(type=="Faculty") {
                intent = new Intent(context, AttendanceFaculty.class);
            }
            else
            {
                intent = new Intent(context, StudentAttendanceShow.class);
            }
            intent.putExtra("result_string", result);
            context.startActivity(intent);
        } else {
            // show an error message to the user
        }
    }
}
