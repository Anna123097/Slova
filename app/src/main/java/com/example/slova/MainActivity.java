package com.example.slova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View .OnClickListener{
    ImageView exit;
    ImageView start;
    ImageView scores;
    ImageView rules;

    ImageView btnUp;
    ImageView btnDown;
    ImageView imgLevel;

    int level = 3;
    SharedPreferences sh;
    final String LEVEL = "level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit = findViewById(R.id.imageViewExit);
        start = findViewById(R.id.imageViewStart);
        scores = findViewById(R.id.imageViewScores);
        rules = findViewById(R.id.imageViewRules);

        btnUp = findViewById(R.id.imageUp);
        btnDown = findViewById(R.id.imageDown);
        imgLevel = findViewById(R.id.imageLevel);


        exit.setOnClickListener(this);
        start.setOnClickListener(this);
        scores.setOnClickListener(this);
        rules.setOnClickListener(this);

        btnDown.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        sh = getPreferences(MODE_PRIVATE);
        level = sh.getInt(LEVEL,3);
        showLevel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewRules:
                Intent intent1 = new Intent(MainActivity.this, ActivityRules.class);
                startActivity(intent1);
                break;
            case R.id.imageViewExit:
                finish();
                break;
            case R.id.imageViewScores:
                Intent intent2 = new Intent(MainActivity.this, ActivityTheBest.class);
                startActivity(intent2);
                break;

            case R.id.imageViewStart:
                Intent intent = new Intent(MainActivity.this, ActivityGame.class);
                intent.putExtra("level", level);
                startActivity(intent);
                break;

            case R.id.imageDown:
                if(level>3) level--;
                showLevel();
                levelUpDate();
                break;
            case R.id.imageUp:
                if(level<10) level++;
                showLevel();
                levelUpDate();
                break;
        }
    }

    private void showLevel(){
        switch (level){
            case 3: imgLevel.setImageResource(R.drawable.thre);  break;
            case 4: imgLevel.setImageResource(R.drawable.forth);  break;
            case 5: imgLevel.setImageResource(R.drawable.five);  break;
            case 6: imgLevel.setImageResource(R.drawable.six);  break;
            case 7: imgLevel.setImageResource(R.drawable.seven);  break;
            case 8: imgLevel.setImageResource(R.drawable.eihgt);  break;
            case 9: imgLevel.setImageResource(R.drawable.nine);  break;
            case 10: imgLevel.setImageResource(R.drawable.ten); break;

        }
    }

    private void levelUpDate(){
        SharedPreferences.Editor editor = sh.edit();
        editor.putInt(LEVEL, level);
        editor.commit();
    }
}