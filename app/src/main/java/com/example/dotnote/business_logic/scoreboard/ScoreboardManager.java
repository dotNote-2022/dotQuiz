package com.example.dotnote.business_logic.scoreboard;
import android.content.Context;

import com.example.dotnote.business_logic.Highscore;
import com.example.dotnote.db.DBManager;

import java.util.*;

public class ScoreboardManager {
    private int score;
    private int highScore;

    public ArrayList<Highscore> playerScores;
    private final DBManager dbManager;

    public ScoreboardManager(Context context) {
        dbManager = DBManager.getInstance(context);
        
        this.updateList();
    }

    // sort the list in descending order
    private void sortList() {
        Collections.sort(playerScores, (highscore, t1) -> t1.getScore() - highscore.getScore());
    }

//    // Update the score after each game has ended.
//    public void addScore(int score) {
//        this.score += score;
//        if (this.score > highScore)
//            highScore = this.score;
//
//        sortList();
//        updateList();
//    }

    // Update the list after each game has ended.
    public void updateList() {
        this.playerScores = this.dbManager.fetchHighscores();
        this.sortList();
    }
}

