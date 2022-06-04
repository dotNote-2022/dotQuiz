package com.example.dotnote.ui.endingscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dotnote.MainActivity;
import com.example.dotnote.R;
import com.example.dotnote.db.DBManager;

public class GameEndActivity extends AppCompatActivity {

    private DBManager db = DBManager.getInstance(this);
    private MediaPlayer music;

    private int score, correct, wrong, total, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        getIntentData();
        updateUserData();
        setFields();
        setUpMusic();
    }

    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getInt("score");
            correct = extras.getInt("correct");
            wrong = extras.getInt("wrong");
            total = extras.getInt("total");
        }
    }

    private void updateUserData() {
        db.updatePlayerStats(score);
        db.addHighscore(score);
    }

    private void setFields() {

        TextView correctTextView = findViewById(R.id.textViewCorrect);
        correctTextView.setText(correct / (double) total * 100 + "%");

        TextView wrongTextView = findViewById(R.id.textViewWrong);
        wrongTextView.setText(wrong/ (double) total*100 + "%");

        TextView scoreTextView = findViewById(R.id.textViewScore);
        scoreTextView.setText(score + "");

        TextView pointsTextView = findViewById(R.id.textViewPoints);
        pointsTextView.setText((score / 10) + "");

        Button exitBtn = findViewById(R.id.buttonGoToMainScreen);
        exitBtn.setOnClickListener(view -> {
            if (music != null) {
                music.stop();
            }
            startActivity(new Intent(this, MainActivity.class));
        });

    }

    private void setUpMusic() {
        music = MediaPlayer.create(this, R.raw.main_menu_theme);
        music.start();
    }

}