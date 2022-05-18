package com.example.dotnote.ui.gamescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.dotnote.R;
import com.example.dotnote.business_logic.Question;
import com.example.dotnote.business_logic.QuestionManager;
import com.example.dotnote.db.DBManager;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private DBManager db = DBManager.getInstance(this);
    private QuestionManager questionManager;
    private ArrayList<Button> answerButtons = new ArrayList<>();


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
            this.setUpQuestions(labels);
        }

        this.setUpActionBar();
        this.setUpButtons();
        this.setUpNextQuestion();
    }

    private void setUpActionBar() {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("Question 1");
//            toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFBB86FC")));
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpQuestions(ArrayList<String> labels) {
        this.questionManager.createQuestions(labels);
    }

    private void setUpButtons() {

        Button answer1 = findViewById(R.id.buttonAns1);
        Button answer2 = findViewById(R.id.buttonAns2);
        Button answer3 = findViewById(R.id.buttonAns3);
        Button answer4 = findViewById(R.id.buttonAns4);

        answerButtons.add(answer1);
        answerButtons.add(answer2);
        answerButtons.add(answer3);
        answerButtons.add(answer4);

    }

    private void setUpNextQuestion() {
        Question question = questionManager.getNextQuestion();

        TextView answerText = findViewById(R.id.textViewQuestionText);
        answerText.setText(question.getQuestionText());

        int btnIndex = 0;
        for (String k: question.getAnswerKeySet()) {
            answerButtons.get(btnIndex++).setText(String.format("%s) %s", k, question.getAnswers().get(k)));
        }
    }
}