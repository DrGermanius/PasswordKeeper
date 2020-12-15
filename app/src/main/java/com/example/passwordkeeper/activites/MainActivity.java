package com.example.passwordkeeper.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.passwordkeeper.R;
import com.example.passwordkeeper.adapters.AccountAdapter;
import com.example.passwordkeeper.models.Account;
import com.example.passwordkeeper.services.AccountService;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView accountListView;
    AccountService accountService;
    AccountAdapter adapter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase.loadLibs(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        super.onCreate(savedInstanceState);
        accountService = new AccountService(getApplicationContext());

        setContentView(R.layout.activity_main);

        accountListView = findViewById(R.id.AccountListView);

        intent = new Intent(this, ViewAccountActivity.class);

        ShowAcccountList();
    }

    @Override
    protected void onResume() {
        ShowAcccountList();
        super.onResume();
    }

    @Override
    protected void onStart() {
        ShowAcccountList();
        super.onStart();
    }

    public void addButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
        startActivity(intent);
    }

    private void ShowAcccountList() {
        ArrayList<Account> accountList = accountService.GetAccounts();
        adapter = new AccountAdapter(accountList, getApplicationContext());
        accountListView.setAdapter(adapter);

        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long accId = accountList.get(position).getId();
                intent.putExtra("id", accId);
                startActivity(intent);
            }
        });
    }
}