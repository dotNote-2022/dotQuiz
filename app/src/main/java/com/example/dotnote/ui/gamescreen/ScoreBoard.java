package com.example.dotnote.ui.gamescreen;
import java.util.*;

public class ScoreBoard {
    private int score;
    private int highScore;

    public final List<Integer> playerScores;

    public ScoreBoard(List<Integer> playerScores) {
        this.playerScores = playerScores;
        playerScores.add(1400); // This is a Test Score (To be removed)
        playerScores.add(1000); // This is a Test Score (To be removed)
        //playerScores.add(1200);
        //playerScores.add(1920);
        playerScores.add(800);  // This is a Test Score (To be removed)
        //playerScores.add(600);

        score = 0;
        highScore = 0;
    }

    // sort the list in descending order
    public void sortList() {
        Collections.sort(playerScores, Collections.reverseOrder());
    }

    // Update the score after each game has ended.
    public void addScore(int score) {
        this.score += score;
        if (this.score > highScore)
            highScore = this.score;

        if (!playerScores.contains(this.score)) // Check is score already exists.
            playerScores.add(this.score);
        sortList();
        updateList();
    }

    // Update the list after each game has ended.
    public void updateList() {
        if (playerScores.size() > 5) {
            for (int i = 5; i < playerScores.size(); i++) {
                playerScores.remove(i);
            }
        }
    }
}

