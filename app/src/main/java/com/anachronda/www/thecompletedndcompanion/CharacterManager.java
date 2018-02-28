package com.anachronda.www.thecompletedndcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CharacterManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_manager);
    }

    public void rollDice(View view) {
        Intent intent = new Intent(this, DiceTool.class);
        startActivity(intent);
    }
}
