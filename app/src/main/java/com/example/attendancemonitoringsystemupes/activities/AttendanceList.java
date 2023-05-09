package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.activities.adapterclass.AbsentStudentsAdapter;
import com.example.attendancemonitoringsystemupes.activities.adapterclass.PresentStudentsAdapter;
import com.example.attendancemonitoringsystemupes.apiCalls.CoursesTaskApi;
import com.example.attendancemonitoringsystemupes.apiCalls.LoginTask;
import com.example.attendancemonitoringsystemupes.apiCalls.SendAttendanceListApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AttendanceList extends AppCompatActivity {

    private TextView studentList;
    private Button check,sendAttendance,commitAttendance;
    private String jsonString;
    private PresentStudentsAdapter presentStudentsAdapter;
    private AbsentStudentsAdapter absentStudentsAdapter;
    private RecyclerView presentStudentsRecyclerView, absentStudentsRecyclerView;
    private List<Student> presentStudentsList, absentStudentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);
        presentStudentsRecyclerView = findViewById(R.id.presentStudentsRecyclerView);
        absentStudentsRecyclerView = findViewById(R.id.absentStudentsRecyclerView);
        check=findViewById(R.id.check);
        sendAttendance=findViewById(R.id.sendAttendance);
        commitAttendance=findViewById(R.id.commitAttendance);
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
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, absentStudents.get(pos).getStudentName() + " clicked!", Toast.LENGTH_SHORT).show();

                for (Student student : absentStudentsList) {
                    Log.d("msg",student.getStudentName() + " - " + student.isAttendanceStatus());
                }

            }
        });

        sendAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, absentStudents.get(pos).getStudentName() + " clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(AttendanceList.this, "Attendance Has Been Sent", Toast.LENGTH_SHORT).show();
               // sendAttendanceToApi();
            }
        });
        commitAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // sendAttendanceToApi();
                Toast.makeText(AttendanceList.this, "Attendance Has been Successfully Committed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AttendanceList.this, LoginTask.class);
                startActivity(intent);
            }
        });
    }
    void sendAttendanceToApi()
    {
        String result="";
        for (Student student : absentStudentsList)
        {
            String temp=student.getStudentName() + " - " + student.isAttendanceStatus();
            result+=temp;
        }
        SendAttendanceListApi sendAttendanceListApi = new SendAttendanceListApi(this);
        try {
            result = sendAttendanceListApi.execute(MainActivity.token,result).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void parseJson()
    {
        presentStudentsList.add(new Student(500075888,"Chirag Sankhala",true));
        presentStudentsList.add(new Student(500075232,"Tanmay Singhal",true));
        presentStudentsList.add(new Student(500075000,"Jay Gupta",true));

        absentStudentsList.add(new Student(500075888,"Amritansh Sharma",false));
        absentStudentsList.add(new Student(500075232,"Devansh Pathak",false));
        absentStudentsList.add(new Student(500075000,"Ram Sharma",false));

    }

}