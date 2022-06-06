package com.example.dotnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dotnote.db.DBManager;

public class EntryPage extends AppCompatActivity {
    public Button button;
    public EditText myTextField;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        dbManager = DBManager.getInstance(this);
        button = findViewById(R.id.button2);

        checkIfUserExists();
        button.setOnClickListener(view -> {

            dbManager.createUser(((TextView) findViewById(R.id.editTextTextPersonName2)).getText().toString());

            Toast.makeText(EntryPage.this, "Welcome " + myTextField.getText().toString() + "!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(EntryPage.this,MainActivity.class);
            startActivity(intent);
        });
    }
    public void checkIfUserExists(){
        button = findViewById(R.id.button2);
        button.setEnabled(false);
        myTextField = findViewById(R.id.editTextTextPersonName2);
        myTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(charSequence.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        checkIfUserExists();
    }
}