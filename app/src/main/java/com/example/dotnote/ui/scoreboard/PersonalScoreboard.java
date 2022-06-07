package com.example.dotnote.ui.scoreboard;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dotnote.business_logic.Highscore;
import com.example.dotnote.databinding.FragmentScoreboardBinding;
import com.example.dotnote.business_logic.scoreboard.ScoreboardManager;

import java.util.ArrayList;

public class PersonalScoreboard extends Fragment {
    private FragmentScoreboardBinding binding;
    ScoreboardManager scoreboardManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScoreboardViewModel scoreboardViewModel =
                new ViewModelProvider(this).get(ScoreboardViewModel.class);

        binding = FragmentScoreboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.scoreboardPersonalScoreTextView;
        textView.setText("All-time: ");
        scoreboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        introView();
        scoreboardManager = new ScoreboardManager(this.getContext());
        
        displayRestScores(scoreboardManager.playerScores);
        return root;
    }



    @SuppressLint("SetTextI18n")
    private void introView(){
        final TextView introView = binding.scoreBoardIntro;
        introView.setText("Your Top 5 Scores:");
    }

    @SuppressLint("SetTextI18n")
    public void displayRestScores(ArrayList<Highscore> playerScores) {

        final TextView highScoreView = binding.highScoreInt;
        final TextView secondScore = binding.secondScore;
        final TextView thirdScore = binding.thirdScore;
        final TextView fourthScore = binding.fourthScore;
        final TextView fifthScore = binding.fifthScore;

        for (int i = 0; i < playerScores.size(); i++) {
            Highscore highscore = playerScores.get(i);
            String highscoreString = highscore.getScore() + " (" + highscore.getDate() + ")";
            switch (i) {
                case 0:
                    highScoreView.setText("1st: " + highscoreString);
                    break;
                case 1:
                    secondScore.setText("2nd: " + highscoreString);
                    break;
                case 2:
                    thirdScore.setText("3rd: " + highscoreString);
                    break;
                case 3:
                    fourthScore.setText("4th: " + highscoreString);
                    break;
                case 4:
                    fifthScore.setText("5th: " + highscoreString);
                    break;
            }
        }

        if (playerScores.size() < 5){
            for(int i = playerScores.size(); i < 5; i++){
                switch (i) {
                    case 0:
                        highScoreView.setText("No HighScore Yet!");
                        break;
                    case 1:
                        secondScore.setText("2nd: No score available");
                        break;
                    case 2:
                        thirdScore.setText("3rd: No score available");
                        break;
                    case 3:
                        fourthScore.setText("4th: No score available");
                        break;
                    case 4:
                        fifthScore.setText("5th: No score available");
                        break;
                }
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
