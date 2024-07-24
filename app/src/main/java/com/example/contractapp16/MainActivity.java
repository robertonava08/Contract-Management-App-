package com.example.contractapp16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    // Declare variables here
    FloatingActionButton back_button;

    FloatingActionButton template_button; // Button to choose a new contract template from device


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Functionality for the back button
        back_button = findViewById(R.id.back_btn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // The finish() closes the current activity and returns to the previous one
                finish();
            }
        });


         // This section will be to initialize the template button
        template_button = findViewById(R.id.template_upload_btn);
        template_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });


    }
    // Bracket above is for on create method
    private void openFilePicker() {
        // Create a new intent to open a document
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Set the MIME type to filter for DOCX files
        intent.setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        // Add a category to ensure only files that can be opened are shown
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Start the activity for result with a request code of 1
        startActivityForResult(intent, 1);
    }


}