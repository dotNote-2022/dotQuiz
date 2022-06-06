package com.example.dotnote.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dotnote.R;
import com.example.dotnote.business_logic.questions.QuestionType;
import com.example.dotnote.databinding.FragmentHomeBinding;
import com.example.dotnote.ui.gamescreen.GameActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView guideText = view.findViewById(R.id.text_guide_1);
        guideText.setText("1) Select the categories of questions for your quiz");

        Button btnStart = view.findViewById(R.id.buttonStart);
        btnStart.setEnabled(false);
        btnStart.setOnClickListener(viewC -> {
            
            Intent i = new Intent(getActivity(), GameActivity.class);
            ArrayList<String> questionTags = new ArrayList<>();
            ChipGroup chipGroup = view.findViewById(R.id.category_selection);
            SeekBar seekBar = view.findViewById(R.id.seekBarDifficulty);
            for (Integer id: chipGroup.getCheckedChipIds()) {
                questionTags.add((String) ((Chip) view.findViewById(id)).getText());
            }
            i.putExtra("tags", questionTags);
            i.putExtra("diff", seekBar.getProgress());
            startActivity(i);

        });

        this.createChips(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createChips(View v) {
        ChipGroup chipGroup = v.findViewById(R.id.category_selection);

        for (QuestionType questionType: QuestionType.values()) {
            Chip chip = new Chip(v.getContext());
            chip.setText(questionType.toString());
            chip.setCheckable(true);
            chip.setOnClickListener(view -> {
                this.lol(v);
            });
            chipGroup.addView(chip);
        }
    }

    private void lol(View v) {
        ChipGroup chipGroup = v.findViewById(R.id.category_selection);
        Button startBtn = v.findViewById(R.id.buttonStart);

        if (chipGroup.getCheckedChipIds().size() == 0) {
            startBtn.setEnabled(false);
        }

        if (chipGroup.getCheckedChipIds().size() > 0) {
            startBtn.setEnabled(true);
        }
    }
}