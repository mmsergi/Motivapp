package com.sergi.motivapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sergi.motivapp.models.Goal;

import java.util.ArrayList;

public class DatabaseGoals extends SQLiteOpenHelper {

    private static final String name = "databasegoals.db";

    private static final int version = 1;

    private static final String tableGoals = "create table goals (goal text, why text, tasks text, created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public DatabaseGoals(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableGoals);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists goals");
        onCreate(db);
    }

    public void insertGoal (Goal item){
        SQLiteDatabase db = getWritableDatabase();

        if (db!=null) {
            ContentValues values = new ContentValues();

            values.put("goal", item.goal);
            values.put("why", item.why);
            values.put("tasks", item.tasks);

            db.insert("goals", null, values);
            db.close();
        }
    }

    public void deleteGoal(String goal){
        SQLiteDatabase db = getWritableDatabase();

        if (db!=null) {

            db.delete("goals", "goal='" + goal + "'", null);
            db.close();

        }
    }

    public ArrayList<Goal> getGoals(){

        ArrayList<Goal> aListGoals = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(" SELECT * FROM goals", null);

        if (c.moveToFirst()) {
            do {

                String goal = c.getString(0);
                String why = c.getString(1);
                String tasks = c.getString(2);

                Goal g = new Goal(goal, why, tasks);

                aListGoals.add(g);

            } while(c.moveToNext());
        }

        return aListGoals;
    }
}