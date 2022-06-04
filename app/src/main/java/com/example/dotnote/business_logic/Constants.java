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
            new Question("Johannes Gutenberg is known for which invention?/Cotton Gin/Sewing Machine/Revolver/Printing Press/Printing Press/Technology/false/none"),
            new Question("Released in 1900, the Brownie was a famous early camera from what company?/Kodak/Polaroid/Canon/Fuji/Kodak/Technology/false/none"),
            new Question("Tech giant Foxconn is headquartered in which of these countries?/Mexico/Brazil/Denmark/Taiwan/Taiwan/Technology/false/none"),
            new Question("The compact disc was originally developed by Philips and which company?/Sony/IBM/Apple/Zenith/Sony/Technology/false/none"),
            new Question("The computer Deep Blue is known for beating a grandmaster in which game?/Checkers/Chess/Monopoly/Snakes and Ladders/Chess/Technology/false/none"),
            new Question("The famous computer Deep Blue was created by which company?/IBM/Amazon/Gateway/Oracle/IBM/Technology/false/none"),
            new Question("The Imitation Game is a biopic based on which twentieth century figure?/Albert Einstein/Nikola Tesla/Alan Turing/Marie Curie/Alan Turing/Movies_and_Series/false/none\n"),
            new Question("The Manhattan Project resulted in the invention of the first?/Liquid Rocket Fuel/Television/Artificial Heart/Atom Bomb/Atom Bomb/Science/false/none\n"),
            new Question("The number of players in each side in Water Polo is:/6/7/8/9/7/Sports/false/none"),
            new Question("The theory of relativity was introduced in physics by this man./Albert Einstein/Galileo Galilei/Archimedes/Isaac Newton/Albert Einstein/Science/false/none\n"),
            new Question("Toblerone is a chocolate brand from which country?/UK/Belgium/Switzerland/Sweeden/Switzerland/Food_and_Culture/false/none\n"),
            new Question("Vaccine gets its name from the Latin for which farm animal?/Sheep/Pig/Cow/Goat/Cow/Science/false/none"),
            new Question("What company produced the Walkman?/Sony/JVC/Philips/Panasonic/Sony/Technology/false/none"),
            new Question("What company specializes in GPS technology?/Bose/Garmin/Nintendo/Mozilla/Garmin/Technology/false/none"),
            new Question("What condiment was sold in the 1830’s as medicine?/Ketchup/Maple syrup/Ranch dressing/Soy sauce/Ketchup/Food_and_Culture/false/none"),
            new Question("What country won the very first FIFA World Cup in 1930?/Paraguay/Brazil/Germany/Uruguay/Uruguay/Sports/false/none"),
            new Question("What disease is created when cells in the body begin to divide uncontrollably?/Cancer/Influenza/Alzheimer's/Diabetes/Cancer/Science/false/none"),
            new Question("What does \"GPS\" stand for?/Global Positioning System/Graphic Planetary Schematic/Ground Plan Scenario/General Polar Size/Global Positioning System/Technology/false/none\n"),
            new Question("What does a spectrometer help measure?/Light/Voltage/Liquid Density/Fermentation/Light/Science/false/none"),
            new Question("What does HTTP stand for?/HyperText Transfer Protocol/Highly Transposed Text Protocol/Helios Transport Tradition Program/Hi-Tao Tsushimi Products/HyperText Transfer Protocol/Technology/false/none"),
            new Question("What does the word “onion” mean in Latin?/Large pearl/Small oyster/White sphere/White oyster/Large pearl/Food_and_Culture/false/none"),
            new Question("What does Tom Hanks compare life to in Forest Gump?/A book/A box of chocolates/A sunny day/A bouquet of flowers/A box of chocolates/Movies_and_Series/false/none"),
            new Question("What is a tall chef’s hat called?/Toque/Skull cap/Bucket hat/Trilby/Toque/Food_and_Culture/false/none"),
            new Question("What is a type of test to see if a specific network address can be reached?/Trumpet/Dot/Ping/Jam/Ping/Technology/false/none"),
            new Question("What is NOT considered one of the Galilean Moons of Jupiter?/Io/Phobos/Ganymede/Europa/Phobos/Science/false/none"),
            new Question("What is the capital of Lithuania?/Vilnius/Minsk/Krakow/Riga/Vilnius/Geography/false/none"),
            new Question("What is the closest star to the Earth, beside the Sun?/Rigel/Proxima Centauri/Canopus/Arctutus/Proxima Centauri/Science/false/none"),
            new Question("What is the largest organ in the human body?/Heart/Large Intestine/Lungs/Skin/Skin/Science/false/none"),
            new Question("What is the name of the blue bird that is in Twitter's logo?/Freddy/Larry/Manny/Tony/Larry/Technology/false/none"),
            new Question("What is the only edible food that never expires?/Honey/Rye/Barley/White rice/Honey/Food_and_Culture/false/none"),
            new Question("What is the only metal that can be in liquid form at room temperature?/iron/Copper/Magnesium/Mercury/Mercury/Science/false/none"),
            new Question("What is the only U.S. state to grow coffee beans?/Hawaii/California/Louisiana/Florida/Hawaii/Food_and_Culture/false/none"),
            new Question("What is the symbol for sodium?/Na/So/Cl/S/Na/Science/false/none"),
            new Question("What liquid did thermometers have in them, because of its sensitivity to temperature?/Bromine/Barium/Gallium/Mercury/Mercury/Science/false/none"),
            new Question("What part of the cerebrum helps with vision?/Frontal Lobe/Temporal lobe/Parietal Lobe/Occipital Lobe/Occipital Lobe/Science/false/none"),
            new Question("What part of the eye determines what the color of it is ?/Iris/Lens/Cornea/Pupil/Iris/Science/false/none"),
            new Question("What search engine was originally called \"Jerry and David's Guide to the World Wide Web\"?/Amazon/AOL/Google/Yahoo!/Yahoo!/Technology/false/none"),
            new Question("What spice, frequently used in Hungarian, Indian, and Moroccan food, provides vitamin A and inhibit hair loss?/Cinnamon/Paprika/Cardamom/Cumin/Paprika/Food_and_Culture/false/none"),
            new Question("What type of brain disorder causes difficulty in speech and movement?/Arthritis/Parkinson's Disease/Diabetes/Pneumonia/Parkinson's Disease/Science/false/none"),
            new Question("What type of sugar includes a small amount of cornstarch to prevent lumping while mixing?/Caster Sugar/Granulated Sugar/Powdered Sugar/Coarse Sugar/Powdered Sugar/Food_and_Culture/false/none"),
            new Question("What video-on-demand service was originally called \"Watch Now\"?/Hulu/Prime Video/Vudu/Netflix/Netflix/Technology/false/none"),
            new Question("What was the first cold breakfast cereal invented in 1863?/Granola/Corn flakes/Bran flakes/Cheerios/Granola/Food_and_Culture/false/none"),
            new Question("What was the name of the adoptive father of Princess Leia Organa from Star Wars?/Bail/Francis/Bob/Ben/Bail/Movies_and_Series/false/none"),
            new Question("What website was originally known as \"Gadabra\"?/Google/Facebook/Amazon/Instagram/Amazon/Technology/false/none"),
            new Question("What year was the first Toy Story film released in cinemas?/1995/1999/2001/1992/1995/Movies_and_Series/false/none"),
            new Question("What year was the very first model of the iPhone released?/2004/2008/2009/2007/2007/Sports/false/none"),
            new Question("When was the band System of a Down formed?/1988/1987/1990/1992/1988/Music/false/none"),
            new Question("When were System of A Down formed?/1988/1987/1990/1992/1988/Music/false/none"),
            new Question("Which actor broke two toes whilst filming The Lord of the Rings: The Two Towers?/Viggo Mortensen/Elijah Wood/Ian McKellen/Orlando Bloom/Viggo Mortensen/Movies_and_Series/false/none"),
            new Question("Which chemical element is historically known as quicksilver?/Platinum/Mercury/Silver/Gold/Mercury/Science/false/none"),
            new Question("Which computer programming language was known for its \"turtle graphics\"?/Logo/Python/COBOL/C++/Logo/Technology/false/none"),
            new Question("Which country has the most smokers in the world?/Greece/Japan/United States of America/China/China/Food_and_Culture/false/none"),
            new Question("Which country invented tea?/China/England/Japan/Brazil/China/Food_and_Culture/false/none"),
            new Question("Which European city hosted the 1936 Summer Olympics?/Berlin/Madrid/London/Paris/Berlin/Sports/false/none"),
            new Question("Which food is slang for poorly-written computer code? /Roast Beef/Rye Bread/Spaghetti/Lasagna/Spaghetti/Technology/false/none"),
            new Question("Which is the largest river in the world?/The Nile/River Aliakmonas/The Yangtze/The Amazon/The Nile/Geography/false/none"),
            new Question("Which monk invented champagne?/Father Brown/Friar Tuck/Abbot Prosecco/Dom Perignon/Dom Perignon/Food_and_Culture/false/none"),
            new Question("Which nut is used to make dynamite?/Peanuts/Walnuts/Pine nuts/Almonds/Peanuts/Food_and_Culture/false/none"),
            new Question("Which of the following cities is NOT in Europe?/Barcelona/Prague/Moscow/Reykjavik/Moscow/Geography/false/none"),
            new Question("Which of these is a metal on the periodic table?/Neon/Radon/Zinc/Krypton/Zinc/Science/false/none"),
            new Question("Which of these social media platforms was launched in 2010? /Twitter/Myspace/Facebook/Instagram/Instagram/Technology/false/none"),
            new Question("Which one the following Grand Slam titles is also know as \"Roland Garros\" title?/French Open/U.S. Open/Wimbledon/Australian Open/French Open/Sports/false/none"),
            new Question("Which organ could grow back if you donated part of it?/Liver/Stomach/Pancreas/Intestine/Liver/Science/false/none"),
            new Question("Which Oscar-winning actress is the voice of Helen Parr (Elastigirl) in The Incredibles?/Emma Stone/Kate Winslet/Holly Hunter/Julianne Moore/Holly Hunter/Movies_and_Series/false/none"),
            new Question("Which part of the body is affected by a Pott's fracture?/Wrist/Knee/Ankle/Arm/Ankle/Science/false/none"),
            new Question("Which planet has the coldest atmosphere in the solar system?/Jupiter/Mercury/Uranus/Neptune/Uranus/Science/false/none"),
            new Question("Which planet has the Great Red Spot?/Jupiter/Mercury/Mars/Venus/Jupiter/Science/false/none"),
            new Question("Which planet is covered in thick poisonous clouds, trapping all the heat it absorbs from the Sun?/Mercury/Earth/Saturn/Venus/Venus/Science/false/none"),
            new Question("Which racer holds the record for the most Grand Prix wins?/Sebastian Vettel/Michael Schumacher/Max Verstappen/Valtteri Bottas/Michael Schumacher/Sports/false/none"),
            new Question("Who developed the smallpox vaccine?/John Adams/Edward Jenner/Louis Pasteur/Alexander Fleming/Edward Jenner/Science/false/none"),
            new Question("Who directed Titanic, Avatar and The Terminator?/Quentin Tarantino/James Cameron/Joss Whedon/Edgar Wright/James Cameron/Movies_and_Series/false/none"),
            new Question("Who discovered Penicillin?/Alexander Fleming/Florence Nightingale/Alexander Papanikolaou/Joseph Bohr/Alexander Fleming/Science/false/none"),
            new Question("Who invented Coca-Cola?/John Pemberton/Asa Griggs Candler/Charles Elmer Hires/John Matthews/John Pemberton/Food_and_Culture/false/none"),
            new Question("Who is widely considered to be the 'Last Samurai'?/Saigo Takamori/Emperor Hirohito/Shinzou Abe/Emperor Akihito/Saigo Takamori/History/false/none"),
            new Question("Who played Freddy Krueger in the first eight \"Night on Elm Street\" movies?/Kane Hodder/Robert Englund/Jackie Earle Haley/Johnny Depp/Robert Englund/Movies_and_Series/false/none"),
            new Question("Who was the first American President?/George Bush/George Washington/Barrack Obama/Lyndon B. Johnson/George Washington/History/false/none"),
            new Question("With which country does France NOT share a land border with?/Germany/Netherlands/Austria/Luxembourg/Austria/Geography/false/none"),
            new Question("Who was the first human to journey into outer space?/Neil Armstrong/Laika/Yuri Gagarin/Alan Shepard/Yuri Gagarin/Science/false/none"),
            new Question("When was Athens officially made the capital city of Greece?/1930/1934/1933/1935/1934/History/false/none\n"),
            new Question("When did the Second Balkan War start?/1912/1913/1914/1911/1913/History/false/none")
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
