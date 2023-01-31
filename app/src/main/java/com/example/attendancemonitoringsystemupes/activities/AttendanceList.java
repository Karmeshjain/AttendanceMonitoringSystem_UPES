package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.attendancemonitoringsystemupes.R;

import java.io.IOException;
import java.io.InputStream;

public class AttendanceList extends AppCompatActivity {

    private TextView studentList;
    private String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);
//        Intent intent = getIntent();
//        String result = intent.getStringExtra("result_string");
       studentList=findViewById(R.id.showAttendance);
//        studentList.setText(result);

        try {
            InputStream is = getResources().openRawResource(R.raw.attendancelist);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       Log.d("json",jsonString);

    }
}