package com.example.attendancemonitoringsystemupes.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemonitoringsystemupes.Person;
import com.example.attendancemonitoringsystemupes.R;
import com.example.attendancemonitoringsystemupes.apiCalls.CoursesTaskApi;
import com.example.attendancemonitoringsystemupes.apiCalls.DetailsApiCall;

import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private TextView tvsapId;

    private Button course1;

    private Button course2;

    private Button course3;

    private Button course4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvName = findViewById(R.id.tv_name);
        tvType = findViewById(R.id.tvType);
        tvsapId = findViewById(R.id.sap_id);
        String token = getIntent().getStringExtra("result_string");
        MainActivity.token=token;
        Person person = parseJSONtoDetails(token);
        tvName.setText("Name: " + person.getName());
        tvType.setText("Type: " + person.getType());
        tvsapId.setText("Address: " + person.getSapId());
        String type="";
        goToActivity(course1,type);
        goToActivity(course1,type);
        goToActivity(course1,type);
        goToActivity(course1,type);

    }
    void goToActivity(Button button,String type)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                CoursesTaskApi coursesTaskApi = new CoursesTaskApi(this);
//                String result="";
//                try {
//                    result = coursesTaskApi.execute(MainActivity.token,buttonName).get();
//                } catch (ExecutionException e) {
//                    Toast.makeText(DetailsActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    Toast.makeText(DetailsActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
                Intent intent;
                if(type=="Faculty") {

                    intent = new Intent(DetailsActivity.this, AttendanceFaculty.class);
                }
                else
                {
                    intent = new Intent(DetailsActivity.this, StudentAttendanceShow.class);
                }
                startActivity(intent);
            }
        });
    }
    Person parseJSONtoDetails(String token)
    {
//        DetailsApiCall detailsApiCall = new DetailsApiCall(this);
//        try {
//            String details = detailsApiCall.execute(token).get();//take details json from courseTaskApi
//        } catch (ExecutionException e) {
//            Toast.makeText(this, "API Fail", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            Toast.makeText(this, "API Fail", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }

        Person person = new Person("Karmesh",  "Student", "500075888");
//        AddButtonToLayout("Operating System");
//        AddButtonToLayout("DBMS");
//        AddButtonToLayout("GPU");
        return person;
    }
    void AddButtonToLayout(String buttonName)
    {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        Button button = new Button(this);
        button.setText(buttonName);
        button.setTextSize(20);
        button.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayout.addView(button, params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CoursesTaskApi coursesTaskApi = new CoursesTaskApi(this);
                String result="";
                try {
                    result = coursesTaskApi.execute(MainActivity.token,buttonName).get();
                } catch (ExecutionException e) {
                    Toast.makeText(DetailsActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Toast.makeText(DetailsActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                String type="Faculty";
                Intent intent;
                if(type=="Faculty") {

                    intent = new Intent(DetailsActivity.this, AttendanceFaculty.class);
                }
                else
                {
                    intent = new Intent(DetailsActivity.this, StudentAttendanceShow.class);
                }
                intent.putExtra("result_string",buttonName);
                startActivity(intent);
            }
        });
    }


}