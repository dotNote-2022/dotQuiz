package com.example.dotnote.business_logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import java.util.Random;

public class MusicManager {

    private static final Random random = new Random();
    private static MediaPlayer mediaPlayer = new MediaPlayer();



    public static void playCorrectAnswer(Context context) {

        if (mediaPlayer == null || !context.getSharedPreferences("root_preferences", 0).getBoolean("sfx", true)) {
            return;
        }


        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(context, random.nextBoolean()? MusicTrack.CLAPPING_1.getTrackId() : MusicTrack.CLAPPING_2.getTrackId());
        mediaPlayer.start();
    }

    public static void playWrongAnswer(Context context) {
        if (mediaPlayer == null || !context.getSharedPreferences("root_preferences", 0).getBoolean("sfx", true)) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(context, random.nextBoolean()? MusicTrack.TRUMPET_SAD.getTrackId() : MusicTrack.CROWD_BOO.getTrackId());
        mediaPlayer.start();
    }

    public static void playTrack(Context context, MusicTrack track) {

        if (mediaPlayer == null || !context.getSharedPreferences("root_preferences", 0).getBoolean("music", true)) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(context, track.getTrackId());
        mediaPlayer.start();
    }

    public static void stop() {
        if (mediaPlayer == null) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

}
