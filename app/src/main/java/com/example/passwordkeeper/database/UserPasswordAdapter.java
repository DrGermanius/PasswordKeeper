package com.example.passwordkeeper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase;

public class UserPasswordAdapter {

    private AccountDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context _context;


    public UserPasswordAdapter(Context context) {
        dbHelper = new AccountDatabaseHelper(context.getApplicationContext());
        _context= context;
    }

    public UserPasswordAdapter open() {
        SQLiteDatabase.loadLibs(_context);
        database = dbHelper.getWritableDatabase("main.db");
        return this;
    }

    public void close() {
        SQLiteDatabase.loadLibs(_context);
        dbHelper.close();
    }

    public long insert(String password) {
        SQLiteDatabase.loadLibs(_context);
        ContentValues cv = new ContentValues();
        cv.put(AccountDatabaseHelper.COLUMN_USER_PASSWORD, password);
        return database.insert(AccountDatabaseHelper.TABLE_USER_PASSWORD, null, cv);
    }

    public Cursor get() {
        SQLiteDatabase.loadLibs(_context);
        String[] columns = new String[]{AccountDatabaseHelper.COLUMN_USER_PASSWORD};
        return database.query(AccountDatabaseHelper.TABLE_USER_PASSWORD, columns, null, null, null, null, null);
    }

    public boolean passwordExist() {
        SQLiteDatabase.loadLibs(_context);
        Cursor cursor = database.rawQuery("SELECT * FROM main", null);
        return cursor.moveToFirst();
    }

    public String getPass() {
        SQLiteDatabase.loadLibs(_context);
        String pass;
        Cursor cursor = get();
        cursor.moveToFirst();
        pass = cursor.getString(cursor.getColumnIndex(AccountDatabaseHelper.COLUMN_USER_PASSWORD));
        cursor.close();
        return pass;
    }

    public boolean checkPass(String pass) {
        SQLiteDatabase.loadLibs(_context);
        return pass.equals(getPass());
    }
}
