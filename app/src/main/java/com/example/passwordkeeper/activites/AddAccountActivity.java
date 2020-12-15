package com.example.passwordkeeper.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordkeeper.R;
import com.example.passwordkeeper.models.Account;
import com.example.passwordkeeper.services.AccountService;
import com.example.passwordkeeper.services.PasswordService;

import net.sqlcipher.database.SQLiteDatabase;

public class AddAccountActivity extends AppCompatActivity {
    AccountService accountService;
    TextView nameTextView;
    TextView loginTextView;
    TextView passTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase.loadLibs(this);

        super.onCreate(savedInstanceState);
        accountService = new AccountService(getApplicationContext());
        setContentView(R.layout.activity_add_account);

        nameTextView = findViewById(R.id.newAccountNameTextView);
        loginTextView = findViewById(R.id.newAccountLoginTextView);
        passTextView = findViewById(R.id.newAccountPasswordEditText);
        descriptionTextView = findViewById(R.id.newAccountDescriptionEditText);
    }

    public void GeneratePasswordClick(View view) {
        String pass = PasswordService.GetGeneratedPassword();
        passTextView.setText(pass);
    }

    public void AddAccount(View view) {
        String name = nameTextView.getText().toString();
        String login = loginTextView.getText().toString();
        String pass = passTextView.getText().toString();
        String description = descriptionTextView.getText().toString();

        Account newAccount = new Account(name, login, pass, description);
        accountService.CreateAccount(newAccount);
        Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
    }
}