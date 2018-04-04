package com.anachronda.www.thecompletedndcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

public class CharacterManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_manager);

        int charID = getIntent().getIntExtra("CharacterID", 0);

        TextView charName = findViewById(R.id.txtCharName);
        TextView charRace = findViewById(R.id.txtCharRace);
        TextView charClass = findViewById(R.id.txtCharClass);
        TextView charBg = findViewById(R.id.txtCharBg);
        TextView charAlign = findViewById(R.id.txtCharAlign);
        TextView charHitDice = findViewById(R.id.txtCharHit);
        TextView charInit = findViewById(R.id.txtCharInit);
        TextView charHP = findViewById(R.id.txtCharHP);
        TextView charAC = findViewById(R.id.txtCharAC);
        TextView charStr = findViewById(R.id.txtCharStr);
        TextView charDex = findViewById(R.id.txtCharDex);
        TextView charCon = findViewById(R.id.txtCharCon);
        TextView charInt = findViewById(R.id.txtCharInt);
        TextView charWis = findViewById(R.id.txtCharWis);
        TextView charCha = findViewById(R.id.txtCharCha);

        List<Character> characters = new Select()
                .from(Character.class)
                .where("CharacterID = " + charID)
                .limit(1)
                .execute();
        String name = characters.get(0).name;
        int level = characters.get(0).level;
        String className = characters.get(0).charClass.name;
        String race = characters.get(0).race.name;
        String bg = characters.get(0).bg.name;
        int hp = characters.get(0).hp;
        int str = characters.get(0).str;
        int dex = characters.get(0).dex;
        int con = characters.get(0).con;
        int intel = characters.get(0).intel;
        int cha = characters.get(0).cha;
        int wis = characters.get(0).wis;
        int ac = 10 + (int)Math.ceil((dex - 10) / 2);

        charName.setText("Name: " + name);
        charClass.setText("Class: Level " + level + " " + className);
        charRace.setText("Race: " + race);
        charBg.setText("Background: " + bg);
        charHP.setText(hp + " HP");
        charAC.setText(ac + " AC");
        charStr.setText(""+str);
        charDex.setText(""+dex);
        charCon.setText(""+con);
        charInt.setText(""+intel);
        charCha.setText(""+cha);
        charWis.setText(""+wis);
    }

    public void rollDice(View view) {
        Intent intent = new Intent(this, DiceTool.class);
        startActivity(intent);
    }
}
