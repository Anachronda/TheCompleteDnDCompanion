package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SpellSkillManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_skill_manager);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(getAssets().open("skillset.txt")));
            String str;

            List<String> list = new ArrayList<>();
            while((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] charArr = list.toArray(new String[0]);

            ListView slv = findViewById(R.id.lvSkillset);
            ArrayAdapter<String> skillAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    charArr);

            slv.setAdapter(skillAdapter);
            slv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClick(View view) {
        Context context = getApplicationContext();
        CharSequence text = "WIP!!!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }
}
