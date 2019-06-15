package com.example.gridtest.playground.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gridtest.playground.LevelselectActivity;

public class LevelDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "levels.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Level";
    private static final String COLUMN_LEVEL = "LevelID";

    public LevelDatabase(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_LEVEL + " INTEGER PRIMARY KEY)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void firstSave(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL, LevelselectActivity.index);
        db.insert(TABLE_NAME, null, values);
    }

    public int loadLevels() {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "SELECT " + COLUMN_LEVEL + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_LEVEL + " DESC";
        Cursor cursor = db.rawQuery(QUERY, null);
        int row = cursor.getColumnIndex(COLUMN_LEVEL);
        cursor.moveToFirst();
        return cursor.getInt(row);
    }

    public void saveLevels(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL, LevelselectActivity.index);
        db.insert(TABLE_NAME, null, values);
    }
}
