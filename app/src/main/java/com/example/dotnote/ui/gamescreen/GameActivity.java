package com.example.dotnote.ui.gamescreen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dotnote.MainActivity;
import com.example.dotnote.R;
import com.example.dotnote.business_logic.BoostType;
import com.example.dotnote.business_logic.Constants;
import com.example.dotnote.business_logic.Question;
import com.example.dotnote.business_logic.QuestionManager;
import com.example.dotnote.db.DBManager;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int currentQuestion = 1;
    private int totalQuestions;
    private int score = 0;
    private int points;
    private DBManager db = DBManager.getInstance(this);
    private QuestionManager questionManager;
    private ArrayList<Button> answerButtons = new ArrayList<>();
    private Question question;
    private ActionBar toolbar;
    private MediaPlayer music;
    private Random random;

    CountDownTimer countDownTimer;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionManager = new QuestionManager(db);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> labels = extras.getStringArrayList("tags");
            System.out.println(labels.toString());
            System.out.println(extras.getInt("diff"));
            this.setUpQuestions(labels, extras.getInt("diff"));
        }

        this.setUpActionBar();
        this.setUpButtons();
        this.setUpNextQuestion();
        this.fetchPoints();

        TextView timerText = findViewById(R.id.textViewTimer);
        timerText.setVisibility(View.GONE);

        TextView scoreText = findViewById(R.id.textView16);
        scoreText.setText(this.score + "");

//        timer();

    }

    private void setUpActionBar() {
        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void fetchPoints() {
        this.points = db.getPlayerPoints();
        TextView pointsTextView = findViewById(R.id.points);
        pointsTextView.setText(MessageFormat.format("{0}", points));

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
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

        ImageButton helpBoostBtn = findViewById(R.id.helpBoostBtn);
        helpBoostBtn.setOnClickListener(view -> {
            displayHelpBoostDialog();
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayHelpBoostDialog() {
        final int[] gay = {-1};
        String[] options = {"50/50 (100 points)", "Caller's help (80 points)", "New Question (60 points)", "Spectator Poll (70 points)"};
        BoostType[] optionsEnum = {BoostType.FIFTY_FIFTY, BoostType.CALLER_HELP, BoostType.NEW_QUESTION, BoostType.SPECTATOR_POLL};
        int[] optionsCost = { 100, 80, 60, 70 };
        final String[] selected = {options[1]};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Help boost")
                .setSingleChoiceItems(options, gay[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (optionsCost[i] > points) {
                            Snackbar.make(findViewById(android.R.id.content), R.string.insufficient_points, Snackbar.LENGTH_SHORT).show();
                        } else {
                            gay[0] = i;
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("Ok", (dialog, id) -> {
                    if (gay[0] >= 0)
                        handleBoost(optionsEnum[gay[0]]);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {});

        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleBoost(BoostType choice) {

        ImageButton boostBtn = findViewById(R.id.helpBoostBtn);
        boostBtn.setEnabled(false);

        System.out.println(choice);

        db.purchaseBoost(BoostType.costs[choice.ordinal()]);
        this.fetchPoints();

        random = new Random();

        switch(choice) {

            case FIFTY_FIFTY:

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

                char[] ans = {'A', 'B', 'C', 'D'};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("A random caller says:")

                        .setMessage(Constants.CALLER_QUOTES[random.nextInt(Constants.CALLER_QUOTES.length)]
                                + (random.nextBoolean()? question.getCorrectAnswer() : question.getAnswers().get(ans[random.nextInt(ans.length)] + "")) )

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                            }
                        })

                        .setIcon(R.drawable.ic_baseline_phone_24);


                builder.show();

                break;
            case NEW_QUESTION:

                this.setUpNextQuestion();

                Snackbar.make(findViewById(android.R.id.content), R.string.question_shuffled, Snackbar.LENGTH_SHORT).show();

                break;
            case SPECTATOR_POLL:


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

        if (questionManager.questionsRemaining() <= 0) {
            finishGame();
        }

        question = questionManager.getNextQuestion();

        TextView answerText = findViewById(R.id.textViewQuestionText);
        answerText.setText(question.getQuestionText());

        int btnIndex = 0;
        for (String k: question.getAnswers().keySet()) {
            answerButtons.get(btnIndex).setText(String.format("%s) %s", k, question.getAnswers().get(k)));
            answerButtons.get(btnIndex).setEnabled(true);
            answerButtons.get(btnIndex++).setClickable(true);
        }

        ImageButton boostBtn = findViewById(R.id.helpBoostBtn);
        boostBtn.setEnabled(true);

        toolbar.setTitle("Question " + this.currentQuestion++ + "" + "/" + this.totalQuestions);

    }

    private void updateScore() {
        TextView scoreText = findViewById(R.id.textView16);
        scoreText.setText(MessageFormat.format("{0}", this.score));
    }

    private void  timer(){
        TextView myTimer = findViewById(R.id.textViewTimer);
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                myTimer.setText(Long.toString(l));
                System.out.println(l);
            }

            @Override
            public void onFinish() {
                setUpNextQuestion();
            }
        }.start();
    }

    private void finishGame() {
        db.updatePlayerStats(score);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exiting the game")
                .setMessage("Are you sure?")
                .setPositiveButton("YES", (dialog, whichButton) -> {
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
            music = MediaPlayer.create(this, random.nextBoolean()? R.raw.clapping1 : R.raw.clapping2);
            music.start();
        } else {
            btn.setBackgroundColor(Color.RED);
            if (this.score - 30 >= 0) {
                this.score -= 30;
            }
            for (Button btn1: answerButtons) {
                if (this.question.isCorrectAnswer(btn1.getText().charAt(0) + "")) {
                    btn1.setBackgroundColor(Color.GREEN);
                }
            }
        }
        updateScore();

        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                () -> {
                   questionManager.removeAnsweredQuestion();
                   for (Button btn3: answerButtons) {
                       btn3.setBackgroundColor(getResources().getColor(R.color.purple_200));
                   }
                   setUpNextQuestion();
                   music.stop();
                },
                3000);


    }
}