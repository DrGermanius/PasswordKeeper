package com.example.passwordkeeper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase;

import com.example.passwordkeeper.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {

    private AccountDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context _context;

    public AccountAdapter(Context context) {
        dbHelper = new AccountDatabaseHelper(context.getApplicationContext());
        _context = context;
    }

    public AccountAdapter open() {
        SQLiteDatabase.loadLibs(_context);
        database = dbHelper.getWritableDatabase("main.db");
        return this;
    }

    public void close() {
        SQLiteDatabase.loadLibs(_context);
        dbHelper.close();
    }

    public long insert(Account account) {
        SQLiteDatabase.loadLibs(_context);
        ContentValues cv = new ContentValues();
        cv.put(AccountDatabaseHelper.COLUMN_NAME, account.getName());
        cv.put(AccountDatabaseHelper.COLUMN_LOGIN, account.getLogin());
        cv.put(AccountDatabaseHelper.COLUMN_USER_PASSWORD, account.getPassword());
        cv.put(AccountDatabaseHelper.COLUMN_DESCRIPTION, account.getDescription());

        return database.insert(AccountDatabaseHelper.TABLE_ACCOUNTS, null, cv);
    }

    public long delete(long userId) {
        SQLiteDatabase.loadLibs(_context);
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(AccountDatabaseHelper.TABLE_ACCOUNTS, whereClause, whereArgs);
    }

    public long update(Account account) {
        SQLiteDatabase.loadLibs(_context);
        String whereClause = AccountDatabaseHelper.COLUMN_ID + "=" + String.valueOf(account.getId());
        ContentValues cv = new ContentValues();
        cv.put(AccountDatabaseHelper.COLUMN_NAME, account.getName());
        cv.put(AccountDatabaseHelper.COLUMN_LOGIN, account.getLogin());
        cv.put(AccountDatabaseHelper.COLUMN_USER_PASSWORD, account.getPassword());
        cv.put(AccountDatabaseHelper.COLUMN_DESCRIPTION, account.getDescription());
        return database.update(AccountDatabaseHelper.TABLE_ACCOUNTS, cv, whereClause, null);
    }

    private Cursor getAllEntries() {
        SQLiteDatabase.loadLibs(_context);
        String[] columns = new String[]{AccountDatabaseHelper.COLUMN_ID, AccountDatabaseHelper.COLUMN_NAME, AccountDatabaseHelper.COLUMN_LOGIN,
                AccountDatabaseHelper.COLUMN_USER_PASSWORD, AccountDatabaseHelper.COLUMN_DESCRIPTION};
        return database.query(AccountDatabaseHelper.TABLE_ACCOUNTS, columns, null, null, null, null, null);
    }

    public Account getAccount(long id) {
        SQLiteDatabase.loadLibs(_context);
        Cursor cursor = database.rawQuery("select * from accounts where _id =" + id, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_NAME));
            String login = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_LOGIN));
            String password = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_USER_PASSWORD));
            String description = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_DESCRIPTION));
            Account account = new Account(name, login, password, description);
            return account;
        }
        return null;
    }

    public ArrayList<Account> getAccounts() {
        SQLiteDatabase.loadLibs(_context);
        ArrayList<Account> accounts = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_NAME));
                String login = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_LOGIN));
                String password = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_USER_PASSWORD));
                String description = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_DESCRIPTION));
                accounts.add(new Account(id, name, login, password, description));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return accounts;
    }
}
