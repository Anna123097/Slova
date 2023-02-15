package com.example.slova;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTheBest extends AppCompatActivity {
    final String KEY1 = "n1";
    final String KEY2 = "n2";
    final String KEY3 = "n3";
    final String KEY4 = "n4";
    final String KEY5 = "n5";

    final String TIME1 = "time1";
    final String TIME2 = "time2";
    final String TIME3 = "time3";
    final String TIME4 = "time4";
    final String TIME5 = "time5";

    SharedPreferences sh;

    String s[] = new String[5];
    int t[] = new int[5];
    int res =0;
    String name = "";

    TextView n1, n2, n3, n4, n5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_best);


        n1 = findViewById(R.id.textViewRes1);
        n2 = findViewById(R.id.textViewRes2);
        n3 = findViewById(R.id.textViewRes3);
        n4 = findViewById(R.id.textViewRes4);
        n5 = findViewById(R.id.textViewRes5);

        sh = getPreferences(MODE_PRIVATE);
        s[0] = sh.getString("n1", "Pupkin");
        s[1] = sh.getString("n2", "Pupkin");
        s[2] = sh.getString("n3", "Pupkin");
        s[3] = sh.getString("n4", "Puplin");
        s[4] = sh.getString("n5", "Pupkin");

        t[0] = sh.getInt("time1", 100);
        t[1] = sh.getInt("time2", 100);
        t[2] = sh.getInt("time3", 100);
        t[3] = sh.getInt("time4", 100);
        t[4] = sh.getInt("time5", 100);

        Intent intent = getIntent();
        if(intent != null){
            long result = intent.getLongExtra("time_result", 20000000)/1000;
            res = (int)result;
            if(res <= t[4]){
                GetNameForTable();
            }
            else if(res<20000)ShowResult(res);
        }
        ShowAll();
    }
    private void ShowAll(){
        n1.setText(s[0] + "  " + t[0]);
        n2.setText(s[1] + "  " + t[1]);
        n3.setText(s[2] + "  " + t[2]);
        n4.setText(s[3] + "  " + t[3]);
        n5.setText(s[4] + "  " + t[4]);
    }

    private void ShowResult(int res) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage("Your result is only: " + res);

        alert.setPositiveButton("Ok", (dialog, whichButton) -> {});
        alert.show();
    }

    private void GetNameForTable() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage("Congradulate ! You have good result ! Enter you e name");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Enter", (dialog, whichButton) -> {
            name = input.getText().toString();
            for(int i = 0; i != 5; i++)
                if(res <= t[i]){
                    String sx = s[i];
                    int tx = t[i];
                    s[i] = name;
                    t[i] = res;
                    name = sx;
                    res = tx;

                }
            SharedPreferences.Editor edit = sh.edit();
            edit.putString("n1", s[0]);
            edit.putString("n2", s[1]);
            edit.putString("n3", s[2]);
            edit.putString("n4", s[3]);
            edit.putString("n5", s[4]);

            edit.putInt("time1", t[0]);
            edit.putInt("time2", t[1]);
            edit.putInt("time3", t[2]);
            edit.putInt("time4", t[3]);
            edit.putInt("time5", t[4]);
            edit.commit();
            ShowAll();
        });

        alert.show();
    }

}