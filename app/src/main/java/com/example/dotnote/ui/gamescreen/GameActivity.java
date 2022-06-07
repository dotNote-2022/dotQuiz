package com.example.dotnote.ui.gamescreen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dotnote.R;
import com.example.dotnote.business_logic.BoostType;
import com.example.dotnote.business_logic.Constants;
import com.example.dotnote.business_logic.music.MusicManager;
import com.example.dotnote.business_logic.music.MusicTrack;
import com.example.dotnote.business_logic.questions.Question;
import com.example.dotnote.business_logic.questions.QuestionManager;
import com.example.dotnote.db.DBManager;
import com.example.dotnote.ui.endingscreen.GameEndActivity;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int currentQuestion = 1;
    private int totalQuestions;
    private int correctQuestions = 0, wrongQuestions = 0;
    private int score = 0;
    private int points;
    private final DBManager db = DBManager.getInstance(this);
    private final QuestionManager questionManager = new QuestionManager(db);
    private final ArrayList<Button> answerButtons = new ArrayList<>();
    private Question question;
    private ActionBar toolbar;
    private final Random random = new Random();
    private int diff;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        

        // fetch the data about difficulty and labels from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> labels = extras.getStringArrayList("tags");
            diff = extras.getInt("diff");
            this.setUpQuestions(labels, extras.getInt("diff"));
        }

        this.setUpActionBar();
        this.setUpButtons();
        this.setUpNextQuestion();
        this.fetchPoints();

        updateScore();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void setUpActionBar() {
        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true); // display the back button
        }

    }

    private void fetchPoints() {
        // fetch points from db and update the score on the toolbar
        this.points = db.getPlayerPoints();
        TextView pointsTextView = findViewById(R.id.points);
        pointsTextView.setText(MessageFormat.format("{0}", points));

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // user pressed the toolbar's back button
        if (item.getItemId() == android.R.id.home) {
            new AlertDialog.Builder(this)
                    .setTitle("Exiting the game")
                    .setMessage("Are you sure?")
                    .setPositiveButton("YES", (dialog, whichButton) -> {
                        MusicManager.stop();
                        finish();
                        dialog.dismiss();
                    }).setNegativeButton("NO", (dialog, whichButton) -> dialog.dismiss()).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpQuestions(ArrayList<String> labels, int diff) {
        this.questionManager.resetQuestions();
        this.questionManager.createQuestions(labels, diff);
        this.totalQuestions = this.questionManager.questionsRemaining();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpButtons() {

        // set up the main 4 answer buttons

        Button answer1 = findViewById(R.id.buttonAns1);
        answer1.setOnClickListener(this);

        Button answer2 = findViewById(R.id.buttonAns2);
        answer2.setOnClickListener(this);

        Button answer3 = findViewById(R.id.buttonAns3);
        answer3.setOnClickListener(this);

        Button answer4 = findViewById(R.id.buttonAns4);
        answer4.setOnClickListener(this);

        answerButtons.add(answer1);
        answerButtons.add(answer2);
        answerButtons.add(answer3);
        answerButtons.add(answer4);

        // set up the help boost button
        ImageButton helpBoostBtn = findViewById(R.id.helpBoostBtn);
        helpBoostBtn.setOnClickListener(view -> {
            displayHelpBoostDialog();
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayHelpBoostDialog() {

        // set up the contents of the help boost
        final int[] selection = {-1};
        String[] options = {"50/50 \uD83C\uDFB2 (100 points) ", "Caller's help ☎️ (80 points)", "New Question \uD83D\uDD04 (60 points)", "Spectator Poll \uD83D\uDDF3️ (70 points)"};
        BoostType[] optionsEnum = {BoostType.FIFTY_FIFTY, BoostType.CALLER_HELP, BoostType.NEW_QUESTION, BoostType.SPECTATOR_POLL};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("\uD83D\uDCB2 Help boost \uD83D\uDCB2")
                // Set help boost options
                .setSingleChoiceItems(options, selection[0], (dialogInterface, i) -> {
                    if (Constants.BOOST_COST[i] > points) {
                        Snackbar.make(findViewById(android.R.id.content), R.string.insufficient_points, Snackbar.LENGTH_SHORT).show();
                    } else {
                        selection[0] = i;
                    }
                })
                // Set the action buttons
                .setPositiveButton("Ok", (dialog, id) -> {
                    if (selection[0] >= 0)
                        handleBoost(optionsEnum[selection[0]]);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {});

        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleBoost(BoostType choice) {

        MusicManager.playTrack(this, MusicTrack.COINS);

        // disable help boost button so the player can't use a boost two times in one round
        ImageButton boostBtn = findViewById(R.id.helpBoostBtn);
        boostBtn.setEnabled(false);

        // purchase the boost and update user's points
        db.purchaseBoost(BoostType.costs[choice.ordinal()]);
        this.fetchPoints();

        switch(choice) {

            case FIFTY_FIFTY:

                // make only the correct answer and one other random answer available
                int correctIndex = 0;
                int index = 0;
                for (Button btn: answerButtons) {
                    if (this.question.isCorrectAnswer(btn.getText().charAt(0) + "")) {
                        correctIndex = index;
                    } else {
                        btn.setEnabled(false);
                    }
                    index++;
                }

                int otherEnable = random.nextInt(answerButtons.size());
                while (otherEnable == correctIndex) {
                    otherEnable = random.nextInt(answerButtons.size());
                }
                answerButtons.get(otherEnable).setEnabled(true);


                break;
            case CALLER_HELP:

                // fetch a random dialog answer and attach the correct answer with a chance of 50%

                char[] ans = {'A', 'B', 'C', 'D'};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("A random caller says:")

                        .setMessage(Constants.CALLER_QUOTES[random.nextInt(Constants.CALLER_QUOTES.length)]
                                + (random.nextBoolean()? question.getCorrectAnswer() : question.getAnswers().get(ans[random.nextInt(ans.length)] + "")) )

                        .setPositiveButton("Ok", (dialog, id) -> {})

                        .setIcon(R.drawable.ic_baseline_phone_24);


                builder.show();

                break;
            case NEW_QUESTION:

                // skips question

                this.setUpNextQuestion();

                Snackbar.make(findViewById(android.R.id.content), R.string.question_shuffled, Snackbar.LENGTH_SHORT).show();

                break;
            case SPECTATOR_POLL:

                // fetch random statistics ([0, 100]) and add 50 to the correct answer

                StringBuilder pollString = new StringBuilder();
                for (String k: question.getAnswerKeySet()) {
                    pollString.append(k).append(": ").append(this.question.isCorrectAnswer(k)? random.nextInt(100) + 50 : random.nextInt(100)).append("\n");
                }

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

                builder2.setTitle("The audience voted:")
                        .setMessage(pollString)
                        .setPositiveButton("Ok", (dialog, id) -> {})
                        .setIcon(R.drawable.ic_baseline_poll_24);


                builder2.show();

                break;
        }
    }

    private void setUpNextQuestion() {

        // no more questions remaining, exit the game
        if (questionManager.questionsRemaining() <= 0) {
            finishGame();
            return;
        }

        question = questionManager.getNextQuestion();

        TextView answerText = findViewById(R.id.textViewQuestionText);
        answerText.setText(question.getQuestionText());

        updateScore();

        int btnIndex = 0;

        // set up buttons with their respective answers
        for (String k: question.getAnswers().keySet()) {
            answerButtons.get(btnIndex).setText(String.format("%s) %s", k, question.getAnswers().get(k)));
            answerButtons.get(btnIndex).setEnabled(true);
            answerButtons.get(btnIndex++).setClickable(true);
        }

        ImageButton boostBtn = findViewById(R.id.helpBoostBtn);
        boostBtn.setEnabled(true);

        // update game's round progress
        toolbar.setTitle("Question " + this.currentQuestion++ + "" + "/" + this.totalQuestions);

    }

    private void updateScore() {
        TextView scoreText = findViewById(R.id.textView16);
        scoreText.setText(MessageFormat.format("{0}", this.score));
    }

    private void finishGame() {
        // add relevant statistics and navigate to GameEndActivity
        Intent i = new Intent(this, GameEndActivity.class);
        i.putExtra("correct", correctQuestions);
        i.putExtra("wrong", wrongQuestions);
        i.putExtra("total", totalQuestions);
        i.putExtra("score", score);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exiting the game")
                .setMessage("Are you sure?")
                .setPositiveButton("YES", (dialog, whichButton) -> {
                    MusicManager.stop();
                    finish();
                    dialog.dismiss();
                }).setNegativeButton("NO", (dialog, whichButton) -> dialog.dismiss()).show();
    }


    @Override
    public void onClick(View view) {

        for (Button btnAns: answerButtons) {
            btnAns.setClickable(false);
        }

        Button btn = findViewById(view.getId());

        if (this.question.isCorrectAnswer(btn.getText().charAt(0) + "")) {
            btn.setBackgroundColor(Color.GREEN);
            score += 100;
            correctQuestions++;
            MusicManager.playCorrectAnswer(this);
        } else {
            // highlight the wrong answer
            btn.setBackgroundColor(Color.RED);
            MusicManager.playWrongAnswer(this);
            wrongQuestions++;
            // lower score if the difference wont result in negative score
            if (this.score - Constants.SCORE_LOSS[diff] >= 0) {
                this.score -= Constants.SCORE_LOSS[diff];
            }
            // highlight the correct question
            for (Button btn1: answerButtons) {
                if (this.question.isCorrectAnswer(btn1.getText().charAt(0) + "")) {
                    btn1.setBackgroundColor(Color.GREEN);
                }
            }
        }

        // set a timer of 3.5 seconds, then proceed to the next round
        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                () -> {
                   questionManager.removeAnsweredQuestion();
                   // reset buttons to origin color
                   for (Button btn3: answerButtons) {
                       btn3.setBackgroundColor(getResources().getColor(R.color.purple_200));
                   }
                   setUpNextQuestion();
                   MusicManager.stop();
                },
                3500);


    }
}