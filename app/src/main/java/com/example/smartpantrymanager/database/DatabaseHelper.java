package com.example.smartpantrymanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//class helps to create and manage sqlite database
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db"; // name of database file
    private static final int DATABASE_VERSION = 1; // database version used when changes are made to database.

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);// constructor creates or opens database
    }

    @Override // will be called when the database is created for the 1st time.
    public void onCreate(SQLiteDatabase db) {
// SQL statement used to create pantry table.
        String createPantryTable = "CREATE TABLE pantry (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ingredient_name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT NOT NULL, " +
                "expiry_date TEXT" +
                ")";

        db.execSQL(createPantryTable); // executes sql statement and creates table
    }

    @Override // called when the database version increases
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS pantry");// this deletes the old pantry table
        onCreate(db); // this creates the table again with a new structure
    }
}