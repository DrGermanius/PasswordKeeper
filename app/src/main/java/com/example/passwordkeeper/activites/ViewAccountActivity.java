package com.example.passwordkeeper.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordkeeper.R;
import com.example.passwordkeeper.models.Account;
import com.example.passwordkeeper.services.AccountService;
import com.example.passwordkeeper.services.PasswordService;

import net.sqlcipher.database.SQLiteDatabase;

public class ViewAccountActivity extends AppCompatActivity {

    AccountService accountService;

    long id;
    TextView nameTextView;
    TextView loginTextView;
    TextView passTextView;
    TextView descriptionTextView;
    TextView hintTextView;

    Button saveButton;
    Button editButton;
    Button generatePassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase.loadLibs(this);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        accountService = new AccountService(getApplicationContext());

        nameTextView = findViewById(R.id.currentAccountNameTextView);
        loginTextView = findViewById(R.id.currentAccountLoginTextView);
        passTextView = findViewById(R.id.currentAccountPasswordTextView);
        descriptionTextView = findViewById(R.id.currentAccountDescriptionTextView);

        hintTextView = findViewById(R.id.HintText);

        saveButton = findViewById(R.id.Save);
        editButton = findViewById(R.id.Edit);
        generatePassButton = findViewById(R.id.generatePassButton_showActivity);

        EnableCoping();

        id = getIntent().getExtras().getLong("id");
        ShowAccount(id);
    }

    private void ShowAccount(long id) {
        Account account = accountService.GetAccount(id);
        nameTextView.setText(account.getName());
        loginTextView.setText(account.getLogin());
        passTextView.setText(account.getPassword());
        descriptionTextView.setText(account.getDescription());
    }

    public void EditAccountButtonClick(View view) {
        DisableCoping();
        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.VISIBLE);
        generatePassButton.setVisibility(View.VISIBLE);

    }

    public void SaveAccountButtonClick(View view) {
        String name = nameTextView.getText().toString();
        String login = loginTextView.getText().toString();
        String pass = passTextView.getText().toString();
        String description = descriptionTextView.getText().toString();

        Account account = new Account(id, name, login, pass, description);
        accountService.UpdateAccount(account);

        EnableCoping();
        editButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.GONE);
        generatePassButton.setVisibility(View.GONE);

        Toast.makeText(this, "Account Updated!", Toast.LENGTH_SHORT).show();
    }

    public void DeleteAccountButtonClick(View view) {
        accountService.DeleteAccount(id);
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Account Deleted!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void GeneratePasswordClick(View view) {
        String pass = PasswordService.GetGeneratedPassword();
        passTextView.setText(pass);
    }

    private void EnableCoping() {
        hintTextView.setText("You can copy value from field by click on it. For back to Account's list click 'Back' on your device.");

        nameTextView.setFocusableInTouchMode(false);
        loginTextView.setFocusableInTouchMode(false);
        passTextView.setFocusableInTouchMode(false);
        descriptionTextView.setFocusableInTouchMode(false);


        nameTextView.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null, nameTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Name Copied!", Toast.LENGTH_SHORT).show();
        });

        loginTextView.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null, loginTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Login Copied!", Toast.LENGTH_SHORT).show();
        });

        passTextView.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null, passTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Password Copied!", Toast.LENGTH_SHORT).show();
        });

        descriptionTextView.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null, descriptionTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Description Copied!", Toast.LENGTH_SHORT).show();
        });

    }

    private void DisableCoping() {
        hintTextView.setText("Account edit mode. For exit without changes click 'Back' on your device");

        nameTextView.setFocusableInTouchMode(true);
        loginTextView.setFocusableInTouchMode(true);
        passTextView.setFocusableInTouchMode(true);
        descriptionTextView.setFocusableInTouchMode(true);

        nameTextView.setOnClickListener(null);
        loginTextView.setOnClickListener(null);
        passTextView.setOnClickListener(null);
        descriptionTextView.setOnClickListener(null);
    }

}