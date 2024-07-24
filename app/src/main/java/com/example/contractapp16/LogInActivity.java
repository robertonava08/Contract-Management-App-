package com.example.contractapp16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInActivity extends AppCompatActivity {

    //This section will declare the objects for the editTexts and buttons
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Initialize the objects
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_sign_in);


        // Set the on-click listener for the button whenever it is pressed
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());


                // if statements to check if the email and password are empty or not
                // These if statements will check if email, password, and v_password are empty or not
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    // Will display if both password and email are missing
                    Toast.makeText(LogInActivity.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                            return;
                }

                if(TextUtils.isEmpty(email)){
                    // Will display a message if the fields are empty
                    Toast.makeText(LogInActivity.this, "Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    // Will display error message if field empty for password
                    Toast.makeText(LogInActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LogInActivity.this, "Logged In ",
                                            Toast.LENGTH_SHORT).show();
                                    // Here you will put the intent to open a new activity where all the contracts will be
                                    // The way how to do it is on the video on 27:42 minutes
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    // Add "finish();" if you want to not be able to go back to log in page

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LogInActivity.this, "Authentication failed ",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        // Find the Sign Up TextView
        TextView textViewSignUp = findViewById(R.id.tv_signup);

        // Set OnClickListener to start SignUpActivity
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignUpActivity
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });

    }
}