package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendancemonitoringsystemupes.apiCalls.LoginTask;
import com.example.attendancemonitoringsystemupes.R;

public class MainActivity extends AppCompatActivity {


    private EditText etUsername, etPassword;
    private Button btnLogin;
    public static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                //JSON code here
                Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                if(username.equals("Student")) {
                    intent.putExtra("type","student");
                    startActivity(intent);
                }
                else if(username.equals("Faculty"))
                {
                    intent.putExtra("type","faculty");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please Fill Correct Details", Toast.LENGTH_SHORT).show();
                }
              //  new LoginTask(MainActivity.this).execute(username, password);

//                if (username.equals("admin") && password.equals("password")) {
//                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}