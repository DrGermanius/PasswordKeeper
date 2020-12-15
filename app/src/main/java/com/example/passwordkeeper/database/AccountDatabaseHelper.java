package com.example.passwordkeeper.database;

import android.content.Context;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase;

public class AccountDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "main.db";
    private static final int SCHEMA = 1;
    static final String TABLE_ACCOUNTS = "accounts";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_DESCRIPTION = "description";

    static final String TABLE_USER_PASSWORD = "main";

    public static final String COLUMN_USER_PASSWORD = "password";

    public AccountDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ACCOUNTS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_LOGIN + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_DESCRIPTION + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_USER_PASSWORD + " (" + COLUMN_USER_PASSWORD
                + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PASSWORD);
        onCreate(db);
    }
}
