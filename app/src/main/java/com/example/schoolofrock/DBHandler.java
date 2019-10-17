package com.example.schoolofrock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for DB version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "schoolofrock.db";

    // initialize constants for shoppinglist table
    public static final String TABLE_VOTER = "song_vote";
    public static final String COLUMN_VOTER_ID = "_id";
    public static final String COLUMN_VOTER_SONG = "song";
    public static final String COLUMN_VOTER_NAME = "voter";
    public static final String COLUMN_VOTER_VOTE = "_vote";


    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        // call superclass constructor
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates database tables
     * @param sqLiteDatabase reference to shopper database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // define create statement for shopping list table
        String query = "CREATE TABLE " + TABLE_VOTER + "(" +
                COLUMN_VOTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VOTER_SONG + " TEXT, " +
                COLUMN_VOTER_NAME + " TEXT, " +
                COLUMN_VOTER_VOTE + " INTEGER" +
                ");";

        // execute create statement
        sqLiteDatabase.execSQL(query);


    }

    /**
     * This method gets executed when a new version of the database is initialized.
     * @param sqLiteDatabase reference to shopper database
     * @param oldVersion old version of database
     * @param newVersion new version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // execute drop statement that drops shopping list table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTER);

        // execute drop statement that drops shopping list item table


        // call method that creates tables
        onCreate(sqLiteDatabase);
    }

    public void addStudent (String song, String voter, int vote) {

        // get reference to shopper database.  This method calls onUpgrade
        // and onCreate.
        SQLiteDatabase db = getWritableDatabase();

        // initialize an empty ContentValues
        ContentValues values = new ContentValues();

        // put key-value pairs in ContentValues.  The key must be the name
        // of a column and the value is the value to be inserted in the column.
        values.put(COLUMN_VOTER_SONG, song);
        values.put(COLUMN_VOTER_NAME, voter);
        values.put(COLUMN_VOTER_VOTE, vote);

        // insert values into shopping list table
        db.insert(TABLE_VOTER, null, values);

        // close reference to shopper database
        db.close();
    }




}
