package com.example.slova;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Readerforwords {


    public static LinkedList<String> readTextFromResource(Context context, int level){
        int id = R.raw.words3;
        switch (level){
            case 3: id = R.raw.words3; break;
            case 4: id = R.raw.words4; break;
            case 5: id = R.raw.words5; break;
            case 6: id = R.raw.words6; break;
            case 7: id = R.raw.words7; break;
            case 8: id = R.raw.words8; break;
            case 9: id = R.raw.words9; break;
            case 10: id = R.raw.words10; break;
        }
        LinkedList<String> words = new LinkedList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(id);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while((line = bufferedReader.readLine()) != null)
                words.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }


}