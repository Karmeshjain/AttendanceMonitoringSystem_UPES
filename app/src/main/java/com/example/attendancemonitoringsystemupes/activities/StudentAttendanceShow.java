package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.attendancemonitoringsystemupes.R;

public class StudentAttendanceShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_show);
        View attendanceColourView = findViewById(R.id.attendanceColour);
        Intent intent = getIntent();
        String courseName= intent.getStringExtra("result_string");

        int present=0;
        if(present==1)
        {
            attendanceColourView.setBackgroundColor(Color.GREEN);
        }
        else
        {
            attendanceColourView.setBackgroundColor(Color.RED);
        }
    }
}