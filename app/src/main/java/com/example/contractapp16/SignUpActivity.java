package com.example.contractapp16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;



public class SignUpActivity extends AppCompatActivity {

    //This section will declare the objects for the editTexts and buttons
    EditText editTextEmail, editTextPassword, editTextVPassword;
    Button button;
    FirebaseAuth mAuth;

    FloatingActionButton back_button;




@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the objects that we previously created
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextVPassword = findViewById(R.id.verify_password);
        button = findViewById(R.id.btn_signup);

        // Set the on-click listener for the button whenever it is pressed
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When the "Sign Up" button is clicked
                   we have to read the texts from the
                   edit texts email, password and
                   verify password
                 */
                String email, password, verify_password;

                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                verify_password = String.valueOf(editTextVPassword.getText());


                // These if statements will check if email, password, and v_password are empty or not
                if(TextUtils.isEmpty(email)){
                    // Will display a message if the fields are empty
                   Toast.makeText(SignUpActivity.this, "Enter email",Toast.LENGTH_SHORT).show();
                   return;
                }

                if(TextUtils.isEmpty(password)){
                    // Will display error message if field empty for password
                    Toast.makeText(SignUpActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(verify_password)){
                    // Display error message if verify password is empty
                    Toast.makeText(SignUpActivity.this,"Verify password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if password and verify_password match
                if (!password.equals(verify_password)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        back_button = findViewById(R.id.back_btn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}