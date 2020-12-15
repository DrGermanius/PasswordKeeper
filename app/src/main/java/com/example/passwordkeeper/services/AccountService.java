package com.example.passwordkeeper.services;

import android.content.Context;

import com.example.passwordkeeper.database.AccountAdapter;
import com.example.passwordkeeper.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    AccountAdapter accountAdapter;

    public AccountService(Context context)
    {
        accountAdapter = new AccountAdapter(context);
    }

    public void CreateAccount(Account account){
        accountAdapter.open();
        accountAdapter.insert(account);
        accountAdapter.close();
    }

    public ArrayList<Account> GetAccounts()
    {
        accountAdapter.open();
        ArrayList<Account> accountList = accountAdapter.getAccounts();
        accountAdapter.close();
        return accountList;
    }

    public Account GetAccount(long id) {
        accountAdapter.open();
        Account account = accountAdapter.getAccount(id);
        accountAdapter.close();
        if (account != null) {
            return account;
        }
        return null;
    }

    public void UpdateAccount(Account account)
    {
        accountAdapter.open();
        accountAdapter.update(account);
        accountAdapter.close();
    }

    public void DeleteAccount(long id)
    {
        accountAdapter.open();
        accountAdapter.delete(id);
        accountAdapter.close();
    }
}
