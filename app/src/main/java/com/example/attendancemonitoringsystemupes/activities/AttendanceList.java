package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.activities.adapterclass.AbsentStudentsAdapter;
import com.example.attendancemonitoringsystemupes.activities.adapterclass.PresentStudentsAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AttendanceList extends AppCompatActivity {

    private TextView studentList;
    private String jsonString;
    private PresentStudentsAdapter presentStudentsAdapter;
    private AbsentStudentsAdapter absentStudentsAdapter;
    private RecyclerView presentStudentsRecyclerView, absentStudentsRecyclerView;
    private List<Student> presentStudentsList, absentStudentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);
        presentStudentsRecyclerView = findViewById(R.id.presentStudentsRecyclerView);
        absentStudentsRecyclerView = findViewById(R.id.absentStudentsRecyclerView);
        presentStudentsList = new ArrayList<>();
        absentStudentsList = new ArrayList<>();
        parseJson();
        presentStudentsAdapter = new PresentStudentsAdapter(presentStudentsList);
        absentStudentsAdapter = new AbsentStudentsAdapter(absentStudentsList);
        presentStudentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presentStudentsRecyclerView.setAdapter(presentStudentsAdapter);

        absentStudentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        absentStudentsRecyclerView.setAdapter(absentStudentsAdapter);
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
    void parseJson()
    {
      presentStudentsList.add(new Student(500075888,"Karmesh",true));
        presentStudentsList.add(new Student(500075232,"Karmeshjain",true));
        presentStudentsList.add(new Student(500075000,"Karmeshduggar",true));

        absentStudentsList.add(new Student(500075888,"Jay2",true));
        absentStudentsList.add(new Student(500075232,"Jay3",true));
        absentStudentsList.add(new Student(500075000,"Jay5",true));


    }

}