package com.example.gridtest.playground.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gridtest.playground.model.Player;


// De database class waar de spelers met de behaalde scores worden opgeslagen van elke level.
public class MyDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "playerDB.db";
    private static final String TABLE_NAME = "Player";
    private static final String COLUMN_ID = "PlayerID";
    private static final String COLUMN_NAME = "PlayerName";
    private static final String COLUMN_SCORE = "PlayerScore";
    private static final String COLUMN_LEVEL = "Level";

    // Instantieert een eigen database, met naam en versie
    public MyDatabase(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    // Creërt de daadwerkelijke database, met de waardes en de namen van de kolommen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SCORE + " INTEGER, "
                + COLUMN_LEVEL + " INTEGER )";
        db.execSQL(CREATE_TABLE);
    }

    // Geeft de mogelijkheid om de databaseversie de vernieuwen
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Voegt een speler toe aan de database;
    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, player.getPlayerID());
        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_SCORE, player.getScore());
        values.put(COLUMN_LEVEL, player.getLevel());
        db.insert(TABLE_NAME, null, values);
    }

    /* Haalt het hoogste spelerID op, dit is nodig om vanuit Player.class het spelerID telkens op te hogen.
     *  Wanneer er nog geen spelers in de database staan is het spelerID 0.
     */
    public int getLastPlayerID(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC " + " LIMIT " + 1 ;
        Cursor cursor = db.rawQuery(query, null);
        int row = cursor.getColumnIndex(COLUMN_ID);
        cursor.moveToLast();
        int highestID;
        if(cursor.getCount() == 0){
            highestID = 0;
        }
        else{
            highestID = cursor.getInt(row);
        }
        return highestID;
    }

    // Haalt een Array met strings terug van top 5 scores van de spelers van het level wat wordt meegegeven.
    public String[] getScores(int level){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + COLUMN_SCORE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_LEVEL + " = " + level + " ORDER BY " + COLUMN_SCORE + " DESC " + " LIMIT " + 5;
        Cursor cursor = db.rawQuery(query, null);
        int row = cursor.getColumnIndex(COLUMN_SCORE);
        String scores[] = new String[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String score = cursor.getString(row);
            scores[i] = score;
            i++;
        }
        return scores;
    }

    // Haalt een Array met string terug van top 5 namen van de spelers van het level wat wordt meegegeven.
    public String[] getNames(int level){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME + " WHERE " + COLUMN_LEVEL + " = " + level + " ORDER BY " + COLUMN_SCORE + " DESC " + " LIMIT " + 5;
        Cursor cursor = db.rawQuery(query, null);
        int row = cursor.getColumnIndex(COLUMN_NAME);
        String names[] = new String[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String name = cursor.getString(row);
            names[i] = name;
            i++;
        }
        return names;
    }
}
