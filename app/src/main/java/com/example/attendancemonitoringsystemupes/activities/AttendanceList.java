package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attendancemonitoringsystemupes.R;

public class AttendanceList extends AppCompatActivity {

    private TextView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result_string");
        studentList=findViewById(R.id.showAttendance);
        studentList.setText(result);
    }
}