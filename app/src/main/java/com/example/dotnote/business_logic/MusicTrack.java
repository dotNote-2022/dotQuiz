package com.example.dotnote.business_logic;

import com.example.dotnote.R;

public enum MusicTrack {

    CLAPPING_1(R.raw.clapping1),
    CLAPPING_2(R.raw.clapping2),
    TRUMPET_SAD(R.raw.trumpet_sad),
    CROWD_BOO(R.raw.crowd_boo),
    ENDING_MUSIC(R.raw.main_menu_theme),
    COINS(R.raw.coins);

    private final int trackId;

    MusicTrack(int trackId) {
        this.trackId = trackId;
    }

    public int getTrackId() {
        return trackId;
    }
}
