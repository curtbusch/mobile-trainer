package com.example.mobiletrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLInput;

/**
 * Created by curtd on 3/13/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "workoutplan.db";
    private static final int DB_VERSION = 11;

    private static final String WORKOUT_TABLE = "workout";
    private static final String COL_WORKOUT_ID = "id";
    private static final String COL_WORKOUT_NAME = "workout_name";
    private static final String COL_WORKOUT_COMPLETE = "complete";

    private static final String EXERCISE_TABLE = "exercise";
    private static final String COL_EXERCISE_ID = "id";
    private static final String COL_EXERCISE_NAME = "exercise_name";
    private static final String COL_EXERCISE_DESCRIPTION =  "description";
    private static final String COL_EXERCISE_CATEGORY = "category";
    private static final String COL_SETS = "sets";
    private static final String COL_REPS = "reps";
    private static final String COL_WORKOUT_ID_FK = "workout_id";
    private static final String COL_EXERCISE_COMPLETE = "complete";

    private static final String CREATE_WORKOUT_TABLE =
            "CREATE TABLE " + WORKOUT_TABLE + " (" +
                    COL_WORKOUT_ID + " INTEGER NOT NULL, " +
                    COL_WORKOUT_NAME + " TEXT NOT NULL, " +
                    COL_WORKOUT_COMPLETE + " INTEGER," +
                    "PRIMARY KEY (id));";

    private static final String CREATE_EXERCISE_TABLE =
            "CREATE TABLE " + EXERCISE_TABLE + " (" +
                    COL_EXERCISE_ID + " INTEGER NOT NULL, " +
                    COL_EXERCISE_NAME + " TEXT NOT NULL, " +
                    COL_EXERCISE_DESCRIPTION + " TEXT NOT NULL, " +
                    COL_EXERCISE_CATEGORY + " TEXT NOT NULL, " +
                    COL_SETS + " INTEGER NOT NULL, " +
                    COL_REPS + " INTEGER NOT NULL, " +
                    COL_WORKOUT_ID_FK + " INTEGER, " +
                    COL_EXERCISE_COMPLETE + " INTEGER," +
                    "PRIMARY KEY (id));";

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

    public boolean insertWorkout(String name, int isComplete) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_WORKOUT_NAME, name);
        contentValues.put(COL_WORKOUT_COMPLETE, isComplete);

        long result = db.insert(WORKOUT_TABLE, null, contentValues);

        // Result is equal to minus 1 if not inserted
        return result != -1;
    }

    public boolean insertExercise(String exerciseName, String exerciseDescription,
                                  String exerciseCategory, int sets, int reps, int workoutId, int exerciseComplete) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_EXERCISE_NAME, exerciseName);
        contentValues.put(COL_EXERCISE_DESCRIPTION, exerciseDescription);
        contentValues.put(COL_EXERCISE_CATEGORY, exerciseCategory);
        contentValues.put(COL_SETS, sets);
        contentValues.put(COL_REPS, reps);
        contentValues.put(COL_WORKOUT_ID_FK, workoutId);
        contentValues.put(COL_EXERCISE_COMPLETE, exerciseComplete);

        long result = db.insert(EXERCISE_TABLE, null, contentValues);

        return result != -1;
    }

    public Cursor getExercises(int workoutId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM " + EXERCISE_TABLE + " WHERE workout_id = " + workoutId, null);

        return result;
    }

    public Cursor getWorkouts() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM " + WORKOUT_TABLE, null);

        return result;
    }

}
