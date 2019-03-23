package com.example.rv193.sqlproject3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

import timber.log.Timber;

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contact_db";
    public static final int DATABASE_VERSION = 1;
    private static String TABLE_NAME = ContactContract.ContactEntry.TABLE_NAME;
    private static String CONTACT_ID = ContactContract.ContactEntry.CONTACT_ID;
    private static String CONTACT_NAME = ContactContract.ContactEntry.CONTACT_NAME;
    private static String CONTACT_EMAIL = ContactContract.ContactEntry.CONTACT_EMAIL;

    public static final String DROP_TABLE =
            "drop table if exists " + ContactContract.ContactEntry.TABLE_NAME;

    public static final String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" +
            CONTACT_ID + " number," +
            CONTACT_NAME + " text," +
            CONTACT_EMAIL + " text" +
            ");";

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Timber.d("SQLITE TABLE CREATED...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addContact(int id, String name, String email, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_ID, id);
        contentValues.put(CONTACT_NAME, name);
        contentValues.put(CONTACT_EMAIL, email);
        database.insert(TABLE_NAME, null, contentValues);
        Timber.d("TABLE RAW INSERT...");
    }

    //Method to help us to read for the SQLite database
    public Cursor readContacts(SQLiteDatabase database){
        String[] projections = {CONTACT_ID, CONTACT_NAME, CONTACT_EMAIL};
        Cursor cursor = database.query(TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }
}
