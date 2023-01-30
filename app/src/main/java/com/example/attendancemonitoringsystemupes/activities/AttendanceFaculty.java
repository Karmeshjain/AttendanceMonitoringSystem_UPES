package com.example.attendancemonitoringsystemupes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.attendancemonitoringsystemupes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceFaculty extends AppCompatActivity {

    private ArrayList<Uri> imageUris = new ArrayList<>();
    private String courseName;
    private String facultyName;
    private String roomNo;
    private Button takePhotosButton;
    private Button submitPhotosButton;
    private TextView courseNameTextView,facultyNameTextView,roomNoTextView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private List<Bitmap> imageList = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private JSONObject attendanceListJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_faculty);
         courseNameTextView = findViewById(R.id.course_name);
         facultyNameTextView = findViewById(R.id.facultyName);
         roomNoTextView = findViewById(R.id.room_no);
        takePhotosButton = findViewById(R.id.take_photo);
        submitPhotosButton = findViewById(R.id.submitPhoto);
        RecyclerView recyclerView = findViewById(R.id.imageList);
        imageAdapter = new ImageAdapter(this, imageList);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA },
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
        takePhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
//        Intent intent = getIntent();
//        jsonString = intent.getStringExtra("result_string");
//        parseJsonString(jsonString);
      //  takeMultiplePhotos();
        submitPhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                ImageSenderApiCall();
                Intent showList = new Intent(AttendanceFaculty.this, AttendanceList.class);
                showList.putExtra("result_string", String.valueOf(attendanceListJson));
                startActivity(showList);
            }
        });
    }
    private void ImageSenderApiCall()
    {
        List<String> base64List = new ArrayList<>();
        for (Bitmap bitmap : imageList) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            base64List.add(encoded);
        }
        JSONObject json = new JSONObject();
        try {
            json.put("images", new JSONArray(base64List));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "API_ENDPOINT";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle the response here
                        attendanceListJson=response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                        Toast.makeText(AttendanceFaculty.this, "API Fail", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageList.add(imageBitmap);
            imageAdapter.notifyDataSetChanged();
        }
    }


    private void parseJsonString(String jsonString)
    {
        courseNameTextView.setText(courseName);
        facultyNameTextView.setText(facultyName);
        roomNoTextView.setText(roomNo);
        takePhotosButton.setText("Take Photos");
    }
    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private Context context;
        private List<Bitmap> imageList;

        ImageAdapter(Context context, List<Bitmap> imageList) {
            this.context = context;
            this.imageList = imageList;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.imageView.setImageBitmap(imageList.get(position));
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
            }
        }
    }
}