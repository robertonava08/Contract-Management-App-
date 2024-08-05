package com.example.contractapp16;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    // Declare variables here
    FloatingActionButton back_button;

    FloatingActionButton template_button; // Button to choose a new contract template from device

    FloatingActionButton create_contract;


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


        //  Functionality for the create contract button
        create_contract = findViewById(R.id.crt_contract_btn);

        create_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateContract.class);
                startActivity(intent);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                saveFileToInternalStorage(uri);
            }
        }
    }

    private void saveFileToInternalStorage(Uri uri) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(uri);
            File file = new File(getFilesDir(), "template_" + System.currentTimeMillis() + ".docx");
            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            Toast.makeText(this, "Template saved successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save template", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}