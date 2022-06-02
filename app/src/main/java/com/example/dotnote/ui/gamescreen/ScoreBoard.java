package com.example.dotnote.ui.gamescreen;
import java.util.*;

public class ScoreBoard {
    private int score;
    private int highScore;

    private final List<Integer> playerScores;

    public ScoreBoard(List<Integer> playerScores) {
        this.playerScores = playerScores;
        score = 0;
        highScore = 0;
    }

    private void sortList() {
        Collections.sort(playerScores);
    }

    // Update the score after each game has ended.
    public void addScore(int score) {
        this.score += score;
        if (this.score > highScore) {
            highScore = this.score;
        }
        playerScores.add(score);
        sortList();
        updateList();
    }

    // Update the list after each game has ended.
    public void updateList() {
        if (playerScores.size() > 10) {
            for (int i = 10; i < playerScores.size(); i++) {
                playerScores.remove(i);
            }
        }
    }
}

