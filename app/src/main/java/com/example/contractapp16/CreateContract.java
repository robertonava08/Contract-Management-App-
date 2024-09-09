package com.example.contractapp16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateContract extends AppCompatActivity {



    FloatingActionButton back_button;

    Spinner templateSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contract);

        //Declare all variables here

        back_button = findViewById(R.id.back_button);

        templateSpinner = findViewById(R.id.template_spinner);

        // the on click listener for the back button (pretty self explanatory)
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });




        // Get the list of template names from internal storage
        String[] templateNames = getTemplateNames();

        // Prepend "Select Template" as the first item in the list
        templateNames = prependSelectTemplate(templateNames);

        // If no templates are found, add a hint
        if (templateNames.length == 0) {
            templateNames = new String[]{"Select Template"};
        }


        // Create an ArrayAdapter using the template names and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, templateNames);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        templateSpinner.setAdapter(adapter);






    }

    // Method to get the names of template files from internal storage
    private String[] getTemplateNames() {
        // Access the root directory of internal storage
        File internalStorageDir = getFilesDir();

        // Get the list of files in the directory
        File[] files = internalStorageDir.listFiles();



        // Get the list of files in the directory
        files = internalStorageDir.listFiles();

        // Create a list to hold the file names
        ArrayList<String> fileNames = new ArrayList<>();

        // Loop through the files and add the names of files ending with ".docx"
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith("template_") && file.getName().endsWith(".docx")) {
                    fileNames.add(file.getName());
                }
            }
        }

        // Convert the list to an array and return it
        return fileNames.toArray(new String[0]);
    }



    // Method to prepend "Select Template" to the list of template names
    private String[] prependSelectTemplate(String[] templateNames) {
        ArrayList<String> modifiedList = new ArrayList<>(Arrays.asList(templateNames));
        modifiedList.add(0, "Select Template");
        return modifiedList.toArray(new String[0]);
    }



}