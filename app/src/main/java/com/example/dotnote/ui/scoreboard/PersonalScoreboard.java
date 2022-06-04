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

import com.example.dotnote.databinding.FragmentScoreboardBinding;
import com.example.dotnote.ui.gamescreen.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

public class PersonalScoreboard extends Fragment {
    private FragmentScoreboardBinding binding;
    ScoreBoard scoreBoard = new ScoreBoard(new ArrayList<>());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScoreboardViewModel scoreboardViewModel =
                new ViewModelProvider(this).get(ScoreboardViewModel.class);

        binding = FragmentScoreboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.scoreboardPersonalScoreTextView;
        scoreboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        introView();
        displayRestScores(scoreBoard.playerScores);
        return root;
    }

    @SuppressLint("SetTextI18n")
    private void introView(){
        final TextView introView = binding.scoreBoardIntro;
        introView.setText("Your Top 5 Scores!!!");
    }

    @SuppressLint("SetTextI18n")
    public void displayRestScores(List<Integer> playerScores) {
        scoreBoard.sortList();

        final TextView highScoreView = binding.highScoreInt;
        final TextView secondScore = binding.secondScore;
        final TextView thirdScore = binding.thirdScore;
        final TextView fourthScore = binding.fourthScore;
        final TextView fifthScore = binding.fifthScore;

        for (int i = 0; i < playerScores.size(); i++) {
            if (i == 0)
                highScoreView.setText("HighScore: " + playerScores.get(i));
            else if (i == 1)
                secondScore.setText("2nd: " + playerScores.get(i));
            else if (i == 2)
                thirdScore.setText("3rd: " + playerScores.get(i));
            else if (i == 3)
                fourthScore.setText("4th: " + playerScores.get(i));
            else if (i == 4)
                fifthScore.setText("5th: " + playerScores.get(i));
        }
        if(playerScores.size() < 5){
            for(int i = playerScores.size(); i < 5; i++){
                if(i == 0)
                    highScoreView.setText("No HighScore Yet!");
                else if(i == 1)
                    secondScore.setText("2nd: No score available");
                else if(i == 2)
                    thirdScore.setText("3rd: No score available");
                else if(i == 3)
                    fourthScore.setText("4th: No score available");
                else if(i == 4)
                    fifthScore.setText("5th: No score available");
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
