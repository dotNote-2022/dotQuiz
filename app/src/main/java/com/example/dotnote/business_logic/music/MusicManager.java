package com.example.dotnote.business_logic.music;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Random;

/**
 * A class that manages music playback
 */
public class MusicManager {

    private static final Random random = new Random();
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    /**
     * Plays one of the correct answer SFX if user hasn't muted them. If a track is already being
     * played, it's stopped.
     * @param context the application context
     */
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

    /**
     * Plays one of the wrong answer SFX if user hasn't muted them. If a track is already being
     * played, it's stopped.
     * @param context the application context
     */
    public static void playWrongAnswer(Context context) {
        if (mediaPlayer == null || !context.getSharedPreferences("root_preferences", 0).getBoolean("sfx", true)) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer
                .create(context, random.nextBoolean()? MusicTrack.TRUMPET_SAD.getTrackId() : MusicTrack.CROWD_BOO.getTrackId());
        mediaPlayer.start();
    }

    /**
     * Plays the given track if the user hasn't muted the music. If a track is already being played,
     * it is stopped
     * @param context the application context
     * @param track one of the available music tracks of type MusicTrack
     */
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

    /**
     * Stops the media player if it's currently playing
     */
    public static void stop() {
        if (mediaPlayer == null) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

}
