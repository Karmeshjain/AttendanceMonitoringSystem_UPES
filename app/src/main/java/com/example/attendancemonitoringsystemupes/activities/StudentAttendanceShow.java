package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.apiCalls.CoursesTaskApi;
import com.example.attendancemonitoringsystemupes.apiCalls.StudentAttendanceStatusApi;

import java.util.concurrent.ExecutionException;

public class StudentAttendanceShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_show);
        View attendanceColourView = findViewById(R.id.attendanceColour);
        Intent intent = getIntent();
        String courseName= intent.getStringExtra("result_string");
        StudentAttendanceStatusApi studentAttendanceStatusApi = new StudentAttendanceStatusApi(this);
        String result="";
        try {
            result = studentAttendanceStatusApi.execute(MainActivity.token,courseName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result=="true")
        {
            attendanceColourView.setBackgroundColor(Color.GREEN);
        }
        else
        {
            attendanceColourView.setBackgroundColor(Color.RED);
        }
    }
}