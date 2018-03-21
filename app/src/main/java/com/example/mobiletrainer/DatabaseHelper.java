package com.example.mobiletrainer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by curtd on 3/13/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "workoutplan.db";
    private static final int DB_VERSION = 3;

    private static final String WORKOUT_TABLE = "workout";
    private static final String COL_WORKOUT_NAME = "workout_name";
    private static final String COL_WORKOUT_COMPLETE = "complete";

    private static final String EXERCISE_TABLE = "exercise";
    private static final String COL_EXERCISE_NAME = "exercise_name";
    private static final String COL_EXERCISE_DESCRIPTION =  "description";
    private static final String COL_CATEGORY = "category";
    private static final String COL_WORKOUT_ID = "workout_id";
    private static final String COL_EXERCISE_COMPLETE = "complete";

    private static final String CREATE_WORKOUT_TABLE =
            "CREATE TABLE " + WORKOUT_TABLE + " (" +
                    COL_WORKOUT_NAME + " TEXT NOT NULL, " +
                    COL_WORKOUT_COMPLETE + " INTEGER);";

    private static final String CREATE_EXERCISE_TABLE =
            "CREATE TABLE " + EXERCISE_TABLE + " (" +
                    COL_EXERCISE_NAME + " TEXT NOT NULL, " +
                    COL_EXERCISE_DESCRIPTION + " TEXT NOT NULL, " +
                    COL_CATEGORY + " TEXT NOT NULL, " +
                    COL_WORKOUT_ID + " INTEGER, " +
                    COL_EXERCISE_COMPLETE + " INTEGER);";

    private static final String DROP_WORKOUT_TABLE = "DROP TABLE IF EXISTS " + EXERCISE_TABLE;

    private static final String DROP_EXERCISE_TABLE = "DROP TABLE IF EXISTS " + WORKOUT_TABLE;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORKOUT_TABLE);
        db.execSQL(CREATE_EXERCISE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EXERCISE_TABLE);
        db.execSQL(DROP_WORKOUT_TABLE);
        onCreate(db);
    }
}
