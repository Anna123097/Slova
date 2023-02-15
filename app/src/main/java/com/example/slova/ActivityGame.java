package com.example.slova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Reader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;

public class ActivityGame extends AppCompatActivity {
    ImageView imageNum;
    TextView textViewLetters;
    EditText editLetters;

    private int level = 3;

    private int id = 0;
    private long current_time = 0;
    LinkedList<String> words;
    Readerforwords reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageNum = findViewById(R.id.imageNumber);
        textViewLetters = findViewById(R.id.textViewLetters);
        editLetters = findViewById(R.id.editText);
        editLetters.addTextChangedListener(lettersEditTextWatcher);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 3);
        words = Readerforwords.readTextFromResource(ActivityGame.this, level);

        current_time = System.currentTimeMillis();
        showWord(id);
    }
    private final TextWatcher lettersEditTextWatcher =
            new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                }


                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String sample = charSequence.toString();
                    if(sample.equals(words.get(id))){
                        if(id == 9){
                            Intent intent = new Intent(ActivityGame.this, ActivityTheBest.class);
                            intent.putExtra("time_result", (System.currentTimeMillis() - current_time) / (level - 2));

                            startActivity(intent);
                        }
                        else id++;
                        editLetters.setText("");
                        showWord(id);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };


    private void showWord(int id) {
        StringBuffer sb = new StringBuffer(words.get(id));
        for(int i = 0;i!=10;i++){
            Random rand = new Random();

            int id1 = rand.nextInt(words.get(id).length());
            int id2 = rand.nextInt(words.get(id).length());

            char ch1 = sb.charAt(id1);
            char ch2 = sb.charAt(id2);
            sb.setCharAt(id1, ch2);
            sb.setCharAt(id2, ch1);
        }
        textViewLetters.setText(sb.toString());

        switch(id){
            case 0: imageNum.setImageResource(R.drawable.ic_num1); break;
            case 1: imageNum.setImageResource(R.drawable.two); break;
            case 2: imageNum.setImageResource(R.drawable.thre); break;
            case 3: imageNum.setImageResource(R.drawable.forth); break;
            case 4: imageNum.setImageResource(R.drawable.five); break;
            case 5: imageNum.setImageResource(R.drawable.six); break;
            case 6: imageNum.setImageResource(R.drawable.seven); break;
            case 7: imageNum.setImageResource(R.drawable.eihgt); break;
            case 8: imageNum.setImageResource(R.drawable.nine); break;
            case 9: imageNum.setImageResource(R.drawable.ten); break;

        }
    }
}