package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attendancemonitoringsystemupes.apiCalls.CoursesTaskApi;
import com.example.attendancemonitoringsystemupes.Person;
import com.example.attendancemonitoringsystemupes.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private TextView tvsapId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvName = findViewById(R.id.tv_name);
        tvType = findViewById(R.id.tvType);
        tvsapId = findViewById(R.id.sap_id);
        String resultString = getIntent().getStringExtra("result_string");
        Person person = parseJSONtoDetails(resultString);
        tvName.setText("Name: " + person.getName());
        tvType.setText("Type: " + person.getType());
        tvsapId.setText("Address: " + person.getSapId());

    }
    Person parseJSONtoDetails(String result)
    {
        Person person = new Person("Karmesh",  "Student", "500075888");
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