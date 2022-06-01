package com.example.dotnote.business_logic;

public class Constants {

    public static final String[] SOUND_PATHS = {
            "src/resources/SoundClips/correct_answer.wav",
            "src/resources/SoundClips/wrong_answer.wav",
            "src/resources/SoundClips/main_menu_theme.wav",
            "src/resources/SoundClips/during_game_theme.wav",
            "src/resources/SoundClips/clapping1.wav",
            "src/resources/SoundClips/clapping2.wav",
            "src/resources/SoundClips/clock_ticking.wav",
            "src/resources/SoundClips/coins.wav",
            "src/resources/SoundClips/answer.wav",
            "src/resources/SoundClips/crowd_boo.wav",
            "src/resources/SoundClips/not_correct_option3.wav",
            "src/resources/SoundClips/transition.wav",
            "src/resources/SoundClips/trumpet_sad.wav",
            "src/resources/SoundClips/xylophone_sad.wav",
            "src/resources/SoundClips/button_select.wav"
    };

    public static final Question[] QUESTIONS = {
            new Question("\"Fe\" is the chemical symbol for which common element?/Copper/Iron/Oxygen/Hydrogen/Iron/Science/false/none"),
            new Question("Approximately how many Earths could fit inside the sun?/356.000/1.3 Million/864.000/2 Million/1.3 Million/Science/false/none"),
            new Question("Ascorbic acid is more commonly referred to as what?/Gluten/Lactose/Vitamin C/Aspirin/Vitamin C/Science/false/none")
    };

    public static final String BACKGROUND_IMAGES_PATH = "src/resources/Background Images/";

    public static final String FONT_FILES_PATH = "src/resources/Fonts/";

    public static final int NUMBER_OF_QUESTIONS_IN_A_ROUND = 5;

    public static final int MAXIMUM_NUMBER_OF_PLAYERS_IN_GUI = 2;

    public static final int NUMBER_OF_AVAILABLE_ANSWERS_IN_GUI = 4;


}
