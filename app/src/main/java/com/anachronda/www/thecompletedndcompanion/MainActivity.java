package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(getAssets().open("character.txt")));
            String str;

            List<String> list = new ArrayList<>();
            while((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] charArr = list.toArray(new String[0]);

            ListView clv = findViewById(R.id.characterList);
            ArrayAdapter<String> characterAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    charArr);

            clv.setAdapter(characterAdapter);
            clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            in = new BufferedReader(new InputStreamReader(getAssets().open("spellbook.txt")));
            String str;

            List<String> list = new ArrayList<>();
            while((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] spellArr = list.toArray(new String[0]);

            ListView slv = findViewById(R.id.spellBook);
            ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    spellArr);

            slv.setAdapter(spellAdapter);
            slv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void characterManager(View view) {
        Intent intent = new Intent(this, CharacterManager.class);
        startActivity(intent);
    }

    public void deleteCharacter(View view){
        Context context = getApplicationContext();
        CharSequence text = "Character deleted!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }

    public void spellbookManager(View view) {
        Intent intent = new Intent(this, SpellSkillManager.class);
        startActivity(intent);
    }

    public void deleteSpellbook(View view){
        Context context = getApplicationContext();
        CharSequence text = "Spellbook/Skillset deleted!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }

    public void diceTool(View view){
        Intent intent = new Intent(this, DiceTool.class);
        startActivity(intent);
    }

    public void lootMerchant(View view) {
        Intent intent = new Intent(this, LootMerchant.class);
        startActivity(intent);
    }
}
