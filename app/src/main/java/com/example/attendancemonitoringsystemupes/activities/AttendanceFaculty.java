package com.example.attendancemonitoringsystemupes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.attendancemonitoringsystemupes.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AttendanceFaculty extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    Uri photoURI;
    String jsonString;
    private String courseName;
    private String facultyName;
    private String roomNo;
    private Button takePhotosButton;
    private Button submitPhotosButton;
    private TextView courseNameTextView,facultyNameTextView,roomNoTextView;

    private void takeMultiplePhotos() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
    private File createImageFile() throws IOException {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_faculty);
         courseNameTextView = findViewById(R.id.course_name);
         facultyNameTextView = findViewById(R.id.facultyName);
         roomNoTextView = findViewById(R.id.room_no);
        takePhotosButton = findViewById(R.id.course1);
        submitPhotosButton = findViewById(R.id.submitPhoto);
        Intent intent = getIntent();
        jsonString = intent.getStringExtra("result_string");
        parseJsonString(jsonString);
       // takeMultiplePhotos();
        String result=sendPhotosToApi();
        submitPhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent showList = new Intent(AttendanceFaculty.this, AttendanceList.class);
                showList.putExtra("result_string", result);
                startActivity(showList);
            }
        });
    }

    private String sendPhotosToApi()
    {
        String result="";

       return result;
    }

    private void parseJsonString(String jsonString)
    {
        courseNameTextView.setText(courseName);
        facultyNameTextView.setText(facultyName);
        roomNoTextView.setText(roomNo);
        takePhotosButton.setText("Button name from json");
        takePhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                takeMultiplePhotos();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            imageUris.add(photoURI);
            // Take another photo
            takeMultiplePhotos();
        }
    }
}