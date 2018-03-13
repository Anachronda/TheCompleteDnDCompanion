package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);
    }

    public void saveCharacter(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Character saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancelCharacter(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
