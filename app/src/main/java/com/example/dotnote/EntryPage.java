package com.example.dotnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntryPage extends AppCompatActivity {
    public Button button;
    public EditText myTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        button = findViewById(R.id.button2);

        checkIfUserExists();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EntryPage.this, myTextField.getText().toString() + "!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EntryPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean checkIfUserExists(){
        button = findViewById(R.id.button2);
        button.setEnabled(false);
        myTextField = findViewById(R.id.editTextTextPersonName2);
        myTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    button.setEnabled(false);
                }
                else
                    button.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        checkIfUserExists();
    }

    /*public void clickFunction(View view){ //function gia to button to settarw sto onClick

        EditText myTextField = findViewById(R.id.editTextTextPersonName2); // R -> Resources

        //Toast.makeText(EntryPage.this, myTextField.getText().toString() + "!", Toast.LENGTH_SHORT).show(); // mynhma pou skaei apo katw

        Log.i("Username", myTextField.getText().toString()); // LOG INFO
        //System.out.println(myTextField.getText().toString());
    }*/
}