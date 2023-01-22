package com.example.attendancemonitoringsystemupes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAge;
    private TextView tvGender;
    private TextView tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        tvGender = findViewById(R.id.tv_gender);
        tvAddress = findViewById(R.id.tv_address);
        String resultString = getIntent().getStringExtra("result_string");
        Person person = parseJSONtoDetails(resultString);
        tvName.setText("Name: " + person.getName());
        tvAge.setText("Age: " + person.getAge());
        tvGender.setText("Gender: " + person.getGender());
        tvAddress.setText("Address: " + person.getAddress());

    }
    Person parseJSONtoDetails(String result)
    {
        Person person = new Person("John Doe", 30, "Male", "123 Main St, Anytown USA");
        for(int i=0;i<5;i++)
        {
            AddButtonToLayout("NameOfCourse");
        }
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
            public void onClick(View view) {
                new CoursesTaskApi(DetailsActivity.this).execute(buttonName);
            }
        });
    }


}