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
            new Question("Ascorbic acid is more commonly referred to as what?/Gluten/Lactose/Vitamin C/Aspirin/Vitamin C/Science/false/none"),
            new Question("At which venue was the first World Championship Grand Prix held?/Silverstone/Monza/Spa/Pescara/Silverstone/Sports/false/none"),
            new Question("During which century was the telescope created?/16th Century/19th Century/17th Century/18th Century/17th Century/Science/false/none"),
            new Question("Galileo discovered the first four moons around which planet?/Mercury/Mars/Jupiter/Venus/Jupiter/Science/false/none"),
            new Question("How many calories per gram are stored in protein?/4/3/2/5/4/Food_and_Culture/false/none"),
            new Question("How many different blood types can be found in the human body?/4/3/1/2/4/Science/false/none"),
            new Question("How many different teams have won the Premier League since the start of the inaugural season in 1992-93?/7/6/5/8/7/Sports/false/none"),
            new Question("How many films have Al Pacino and Robert De Niro starred in together?/10/6/2/4/4/Movies_and_Series/false/none"),
            new Question("How many flowers do honeybees need to visit in order to make one pound of honey?/2 million/1 million/1.5 million/900.000/2 million/Food_and_Culture/false/none"),
            new Question("How many goals did England score (excluding penalty shoot-outs) at the Mens' 2018 FIFA World Cup?/12/15/10/11/12/Sports/false/none"),
            new Question("How many molecules of oxygen does ozone have?/3/4/5/1/3/Science/false/none"),
            new Question("How many moons does Mars have in all?/0/5/8/2/2/Science/false/none"),
            new Question("How many taste buds does the human tongue have?/10.000/15.000/20.000/8.000/10.000/Science/false/none"),
            new Question("How old is the Queen of England?/89/98/102/94/94/History/false/none"),
            new Question("In what year did The Beatles split up?/1970/1972/1968/1974/1970/Music/false/none"),
            new Question("In which country did the brand LG originate?/USA/China/Germany/South Korea/South Korea/Technology/false/none"),
            new Question("In which genre of metal do the songs of the german metal band 'Rammstein' belong to?/Industrial Metal/Heavy Metal/Power Metal/Death Metal/Industrial Metal/Music/false/none"),
            new Question("In which sports is the participant called pugilist?/Sprinter/Boxing/Wrestling/Javelin throw/Boxing/Sports/false/none"),
            new Question("In which year did Tim Berners-Lee invent the World Wide Web?/1960/1985/1993/1989/1989/Technology/false/none"),
            new Question("Johannes Gutenberg is known for which invention?/Cotton Gin/Sewing Machine/Revolver/Printing Press/Printing Press/Technology/false/none")
    };

    public static final String[] CALLER_QUOTES = {
            "This is quite the difficult question... I'm gonna have to go with ",
            "Oh, oh, I know this one! It's ",
            "What? You're calling me for this? I have no idea... could be "
    };

    public static final int[] ROUNDS = {7, 10, 15};

    public static final int[] SCORE_LOSS = {15, 30, 50};

    public static final int[] BOOST_COST = {100, 80, 60, 70};

    public static final String BACKGROUND_IMAGES_PATH = "src/resources/Background Images/";

    public static final String FONT_FILES_PATH = "src/resources/Fonts/";

    public static final int NUMBER_OF_QUESTIONS_IN_A_ROUND = 5;

    public static final int MAXIMUM_NUMBER_OF_PLAYERS_IN_GUI = 2;

    public static final int NUMBER_OF_AVAILABLE_ANSWERS_IN_GUI = 4;


}
