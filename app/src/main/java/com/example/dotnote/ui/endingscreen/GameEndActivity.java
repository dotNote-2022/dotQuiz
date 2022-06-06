package com.example.dotnote.ui.endingscreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.dotnote.MainActivity;
import com.example.dotnote.R;
import com.example.dotnote.business_logic.music.MusicManager;
import com.example.dotnote.business_logic.music.MusicTrack;
import com.example.dotnote.db.DBManager;

public class GameEndActivity extends AppCompatActivity {

    private final DBManager db = DBManager.getInstance(this);
    private ActionBar toolbar;

    private int score, correct, wrong, total, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        getIntentData();
        setUpActionBar();
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
            navigateToMain();
        });

    }

    private void setUpActionBar() {
        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(false);
            toolbar.setTitle("Game Results");
        }

    }

    @Override
    public void onBackPressed() {
        navigateToMain();
    }

    private void setUpMusic() {
        MusicManager.playTrack(this, MusicTrack.ENDING_MUSIC);
    }

    private void navigateToMain() {
        MusicManager.stop();
        startActivity(new Intent(this, MainActivity.class));
    }


}