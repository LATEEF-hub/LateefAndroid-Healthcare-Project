package com.example.healthcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextBMBFullName);
        edEmail = findViewById(R.id.editTextBMBAddress);
        edPassword = findViewById(R.id.editTextBMBPincode);
        edConfirmPassword = findViewById(R.id.editTextBMBContact);
        btn = findViewById(R.id.buttonBMBBooking);
        tv = findViewById(R.id.textViewExistingUser);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String ConfirmPassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare", null, 2);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || ConfirmPassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.compareTo(ConfirmPassword) == 0) {
                           if (isValid(password)) {
                               db.register(username, email, password);
                               Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                           }
                           else{
                               Toast.makeText(getApplicationContext(), "Password must contain least 8 character having letter, digit and symbols", Toast.LENGTH_SHORT).show();
                           }
                    }else{
                        Toast.makeText(getApplicationContext(), "Both password needs to match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public static boolean isValid(String passwordHere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordHere.length() >= 8) {
            for (int p = 0; p < passwordHere.length(); p++) {
                if (Character.isLetter(passwordHere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordHere.length(); r++) {
                if (Character.isDigit(passwordHere.charAt(r))) {
                    f2 = 1;
                }

            }
            for (int s = 0; s < passwordHere.length(); s++) {
                char c = passwordHere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;

        }
        return false;
    }

}