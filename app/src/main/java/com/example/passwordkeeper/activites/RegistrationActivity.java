package com.example.passwordkeeper.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.passwordkeeper.R;
import com.example.passwordkeeper.services.FingerPrintService;
import com.example.passwordkeeper.services.LoginService;

import net.sqlcipher.database.SQLiteDatabase;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase.loadLibs(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        LoginService loginService = new LoginService(getApplicationContext());

        TextView welcomeText = findViewById(R.id.welcomeTextView);
        EditText welcomePass = findViewById(R.id.welcomePassEditField);
        Button welcomeButton = (Button) findViewById(R.id.welcomeButton);

        Intent intentToMain = new Intent(RegistrationActivity.this, MainActivity.class);

        if (loginService.isPasswordExist()) {
            welcomeText.setText("Enter Password");
            welcomeButton.setText("Login");
            welcomeButton.setOnClickListener(v -> {
                if (loginService.checkPass(welcomePass.getText().toString())) {
                    startActivity(intentToMain);
                } else {
                    welcomeText.setText("Incorrect Password");
                    welcomePass.setText("");
                }
            });
        } else {
            welcomeText.setText("Create Password");
            welcomeButton.setText("Register");
            welcomeButton.setOnClickListener(v -> {
                loginService.addPassword(welcomePass.getText().toString());
            });
            startActivity(intentToMain);

        }
    }

    public void fingerPrintAuthStart(View view) {
        FingerPrintService fingerPrintService = new FingerPrintService(getApplicationContext(), RegistrationActivity.this);
        fingerPrintService.auth();
    }
}