package com.example.dotnote.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dotnote.business_logic.Constants;
import com.example.dotnote.business_logic.Highscore;
import com.example.dotnote.business_logic.Player;
import com.example.dotnote.business_logic.Question;
import com.example.dotnote.business_logic.QuestionType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DBManager extends SQLiteOpenHelper {

    private static DBManager sInstance;

    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "quizDB.db";

    // Questions table
    public static final String QUESTIONS_TABLE = "Questions";
    public static final String COLUMN_ID_QUESTIONS = "_id";
    public static final String COLUMN_QUESTION_TEXT = "questionText";
    public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    public static final String COLUMN_QUESTION_TYPE = "type";
    public static final String COLUMN_ANSWERS = "answers";
    public static final String COLUMN_PICTURE = "picture";

    // Users table
    public static final String PLAYERS_TABLE = "Players";
    public static final String COLUMN_ID_PLAYERS = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PLAYER_POINTS = "points";

    // Highscores table
    private static final String HIGHSCORES_TABLE = "Highscores";
    private static final String HIGHSCORE_POINTS = "points";
    private static final String HIGHSCORE_DATE = "date";

    public static synchronized DBManager getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DBManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Creating DB...");
//        this.initializeQuestions();

        createQuestionsTable(sqLiteDatabase);
        createUsersTable(sqLiteDatabase);
        createHighscoresTable(sqLiteDatabase);
        initializeQuestions(sqLiteDatabase);

        System.out.println("Finished creating local DB");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE);
        onCreate(sqLiteDatabase);
    }
    
    private void createQuestionsTable(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_QUESTIONS_TABLE_QUERY = "CREATE TABLE " + QUESTIONS_TABLE + "("
                + COLUMN_ID_QUESTIONS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION_TEXT + " TEXT NOT NULL,"
                + COLUMN_ANSWERS + " TEXT NOT NULL,"
                + COLUMN_CORRECT_ANSWER + " TEXT NOT NULL,"
                + COLUMN_QUESTION_TYPE + " TEXT NOT NULL,"
                + COLUMN_PICTURE + " TEXT"
                + ")";

        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE_QUERY);
    }

    private void createUsersTable(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_PLAYERS_TABLE_QUERY = "CREATE TABLE " + PLAYERS_TABLE + "("
                + COLUMN_ID_PLAYERS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT NOT NULL,"
                + COLUMN_PLAYER_POINTS + " INTEGER NOT NULL"
                + ")";

        sqLiteDatabase.execSQL(CREATE_PLAYERS_TABLE_QUERY);
    }

    private void createHighscoresTable(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_HIGHSCORES_TABLE = "CREATE TABLE " + HIGHSCORES_TABLE + "("
                + HIGHSCORE_POINTS + " INTEGER NOT NULL,"
                + HIGHSCORE_DATE + " TEXT NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(CREATE_HIGHSCORES_TABLE);
    }

    /**
     * Checks if a user exists in the DB
     * @return <b>TRUE</b> if a user exists, else <b>FALSE</b>
     */
    public boolean checkIfUserExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countPlayers = "SELECT COUNT(*) FROM " + PLAYERS_TABLE;

        Cursor cursor = db.rawQuery(countPlayers, null);
        cursor.moveToFirst();

        if (cursor.getInt(0) == 0) {
            System.out.println("No player registered");
            db.close();
            return false;
        }

        db.close();
        System.out.println("Player registered");
        return true;
    }

    /**
     * Fetch the player from the Database
     * @return the Player object from the DB
     */
    public Player fetchPlayer() {

        SQLiteDatabase db = this.getReadableDatabase();
        String fetchPlayer = "SELECT * FROM " + PLAYERS_TABLE + " LIMIT 1";

        Cursor cursor = db.rawQuery(fetchPlayer, null);
        cursor.moveToFirst();

        db.close();

        return new Player(cursor.getString(1), 0);

    }

    public void createUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String addNewUser = "INSERT INTO "
                + PLAYERS_TABLE + "(" + COLUMN_ID_PLAYERS + "," + COLUMN_USERNAME + "," + COLUMN_PLAYER_POINTS + ")"
                + "VALUES " + "(" + "null" + ",\"" + username + "\"," + 200 + ")";

        db.execSQL(addNewUser);
        db.close();
    }

    public void addHighscore(int score) {

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        SQLiteDatabase db = this.getWritableDatabase();
        String addNewHighscore = "INSERT INTO "
                + HIGHSCORES_TABLE + "(" + HIGHSCORE_POINTS + "," + HIGHSCORE_DATE + ") "
                + "VALUES " + "(" + score + ",\"" + formattedDate + "\")";
        db.execSQL(addNewHighscore);
        db.close();
    }

    public ArrayList<Highscore> fetchHighscores() {
        SQLiteDatabase db = this.getReadableDatabase();
        String fetchHighscoresQuery = "SELECT " + HIGHSCORE_POINTS + "," + HIGHSCORE_DATE +  " FROM " + HIGHSCORES_TABLE;
        Cursor highscoreCursor = db.rawQuery(fetchHighscoresQuery, null);

        ArrayList<Highscore> highscores = new ArrayList<>();

        if (highscoreCursor.moveToFirst()) {
            do {
                highscores.add(new Highscore(highscoreCursor.getInt(0), highscoreCursor.getString(1)));
            } while (highscoreCursor.moveToNext());
        }

        db.close();
        return highscores;
    }

    @SuppressLint("NewApi")
    public void initializeQuestions(SQLiteDatabase DB) {

        ContentValues values = new ContentValues();

        for (Question question: Constants.QUESTIONS) {
            values.put(COLUMN_QUESTION_TEXT, question.getQuestionText());
            values.put(COLUMN_ANSWERS, String.join(",", question.getAnswers().values()));
            values.put(COLUMN_CORRECT_ANSWER, question.getCorrectAnswer());
            values.put(COLUMN_QUESTION_TYPE, question.getQuestionType().toString());
            values.put(COLUMN_PICTURE, !question.getPicture().equals("") ? question.getPicture() : null);
            DB.insert(QUESTIONS_TABLE, null, values);
            values.clear();
        }
    }

    /**
     * Fetches all the questions whose type matches at least one of the provided labels
     * @param labels an ArrayList with the types of the questions to be fetched
     * @return an ArrayList of Questions
     */
    public ArrayList<Question> fetchQuestions(ArrayList<String> labels, int diff) {

        SQLiteDatabase db = this.getReadableDatabase();

        StringBuilder SELECT_QUESTIONS = new StringBuilder("SELECT * FROM " + QUESTIONS_TABLE + " WHERE " + COLUMN_QUESTION_TYPE + " IN (");

        for (String label: labels) {
            SELECT_QUESTIONS.append("\"").append(label).append("\"").append(",");
        }
        SELECT_QUESTIONS.append(")");
        SELECT_QUESTIONS.replace(SELECT_QUESTIONS.length() - 2, SELECT_QUESTIONS.length() - 1, "");
        SELECT_QUESTIONS.append(" LIMIT ").append(10);

        System.out.println(SELECT_QUESTIONS);

        Cursor cursorQuestions = db.rawQuery(SELECT_QUESTIONS.toString(), null);

        ArrayList<Question> questions = new ArrayList<>();

        if (cursorQuestions.moveToFirst()) {

            do {
                String questionText = cursorQuestions.getString(1);
                String correctAnswer = cursorQuestions.getString(3);
                String picture = cursorQuestions.getString(5);
                QuestionType questionType = QuestionType.valueOf(cursorQuestions.getString(4));
                HashMap<String, String> answers = new HashMap<>();
                String[] possibleAnswers = cursorQuestions.getString(2).split(",");
                char c = 'A';
                for (int i = 0; i < possibleAnswers.length; i++)
                    answers.put(String.valueOf(c++), possibleAnswers[i]);
                if (!picture.equals("none")) {
                    questions.add(new Question(questionText, correctAnswer, answers, questionType, true, picture));
                } else {
                    questions.add(new Question(questionText, correctAnswer, answers, questionType, false));
                }
            } while (cursorQuestions.moveToNext());

        }

        cursorQuestions.close();
        db.close();

        return questions;

    }

    public void updatePlayerStats(int score) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor player = db.rawQuery("SELECT * FROM " + PLAYERS_TABLE + " LIMIT 1", null);

        player.moveToFirst();

        int playerId = player.getInt(0);
        int currentPoints = player.getInt(2);

        String UPDATE_STATS = "UPDATE " + PLAYERS_TABLE +
                " SET points=" + (currentPoints + (score / 10))
                + " WHERE _id=" + playerId + ";";

        db.execSQL(UPDATE_STATS);

    }

    public void purchaseBoost(int cost) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor player = db.rawQuery("SELECT * FROM " + PLAYERS_TABLE + " LIMIT 1", null);

        player.moveToFirst();

        int playerId = player.getInt(0);
        int currentPoints = player.getInt(2);

        String UPDATE_POINTS = "UPDATE " + PLAYERS_TABLE +
                " SET points=" + (currentPoints - cost)
                + " WHERE _id=" + playerId + ";";

        db.execSQL(UPDATE_POINTS);
    }

    public int getPlayerPoints() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor pointsCursor = db.rawQuery("SELECT " + COLUMN_PLAYER_POINTS + " FROM " + PLAYERS_TABLE + " LIMIT 1", null);
        pointsCursor.moveToFirst();

        return pointsCursor.getInt(0);
    }
}
