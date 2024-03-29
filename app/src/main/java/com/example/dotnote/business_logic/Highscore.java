package com.example.dotnote.business_logic;

/**
 * Class representing a high score entry
 */
public class Highscore {
    private final int score;
    private final String date;

    public Highscore(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Highscore{" +
                "score=" + score +
                ", date='" + date + '\'' +
                '}';
    }
}
