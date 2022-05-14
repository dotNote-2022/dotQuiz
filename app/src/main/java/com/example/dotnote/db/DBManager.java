package com.example.dotnote.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dotnote.business_logic.Constants;
import com.example.dotnote.business_logic.Question;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizDB.db";
    public static final String QUESTIONS_TABLE = "questions";
    public static final String COLUMN_ID = "_id";
    public static final String  COLUMN_QUESTION_TEXT = "questionText";
    public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    public static final String COLUMN_ANSWERS = "answers";


    public DBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("onCreate");
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QUESTIONS_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION_TEXT + " TEXT," + COLUMN_ANSWERS + " TEXT," + COLUMN_CORRECT_ANSWER + ")";
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
//        this.initializeQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
    }

    public void addQuestion(Question question) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_QUESTION_TEXT, "This is a test?");
//        values.put(COLUMN_ANSWERS, "1,2,3,4");
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(QUESTIONS_TABLE, null, values);
//        db.close();
        this.initializeQuestions();
    }

    @SuppressLint("NewApi")
    private void initializeQuestions() {

        ContentValues values = new ContentValues();
        SQLiteDatabase DB = this.getWritableDatabase();

        for (Question question: Constants.QUESTIONS) {
            values.put(COLUMN_QUESTION_TEXT, question.getQuestionText());
            values.put(COLUMN_ANSWERS, String.join(",", question.getAnswers().values()));
            values.put(COLUMN_CORRECT_ANSWER, question.getCorrectAnswer());
            DB.insert(QUESTIONS_TABLE, null, values);
            values.clear();
        }

    }
}
