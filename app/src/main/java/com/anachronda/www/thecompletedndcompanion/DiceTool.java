package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceTool extends AppCompatActivity {

    private static Integer d4;
    private static Integer d6;
    private static Integer d8;
    private static Integer d10;
    private static Integer d12;
    private static Integer d20;
    private static Integer d100;
    private static String coin;
    private static Integer str;
    private static Integer dex;
    private static Integer con;
    private static Integer intel;
    private static Integer wis;
    private static Integer cha;
    private static Integer runningTotal = 0;
    private static String diceTracker;
    private TextView txtDice;
    private static Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_tool);

        txtDice = findViewById(R.id.txtDice);
        txtDice.setTextColor(Color.BLACK);
    }

    public void rolld4(View view) {
        d4 = random.nextInt(3) + 1;
        runningTotal = runningTotal + d4;
        if(txtDice.getText() == "") {
            diceTracker = "d4(" + d4 + ")";
        } else {
            diceTracker = diceTracker + " + d4(" + d4 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }
    
    public void rolld6(View view) {
        d6 = random.nextInt(5) + 1;
        runningTotal = runningTotal + d6;
        if(txtDice.getText() == "") {
            diceTracker = "d6(" + d6 + ")";
        } else {
            diceTracker = diceTracker + " + d6(" + d6 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }
    
    public void rolld8(View view) {
        d8 = random.nextInt(7) + 1;
        runningTotal = runningTotal + d8;
        if(txtDice.getText() == "") {
            diceTracker = "d8(" + d8 + ")";
        } else {
            diceTracker = diceTracker + " + d8(" + d8 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }
    
    public void rolld10(View view) {
        d10 = random.nextInt(9) + 1;
        runningTotal = runningTotal + d10;
        if(txtDice.getText() == "") {
            diceTracker = "d10(" + d10 + ")";
        } else {
            diceTracker = diceTracker + " + d10(" + d10 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }
    
    public void rolld12(View view) {
        d12 = random.nextInt(11) + 1;
        runningTotal = runningTotal + d12;
        if(txtDice.getText() == "") {
            diceTracker = "d12(" + d12 + ")";
        } else {
            diceTracker = diceTracker + " + d12(" + d12 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }

    public void rolld20(View view) {
        d20 = random.nextInt(19) + 1;
        runningTotal = runningTotal + d20;
        if(txtDice.getText() == "") {
            diceTracker = "d20(" + d20 + ")";
        } else {
            diceTracker = diceTracker + " + d20(" + d20 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }

    public void rolld100(View view) {
        d100 = random.nextInt(99) + 1;
        runningTotal = runningTotal + d100;
        if(txtDice.getText() == "") {
            diceTracker = "d100(" + d100 + ")";
        } else {
            diceTracker = diceTracker + " + d100(" + d100 +")";
        }
        String diceText = diceTracker + " = " + runningTotal;
        txtDice.setText(diceText);
    }

    public void flipCoin(View view) {
        int coinFlip = random.nextInt(3) + 1;
        if (coinFlip > 2) {
            coin = "Heads";
        } else {
            coin = "Tails";
        }
        txtDice.setText(coin);
    }

    public void clearDice(View view) {
        txtDice.setText("");
        diceTracker = "";
        runningTotal = 0;
    }

    public void diceRoll(View view) {
        Context context = getApplicationContext();
        CharSequence text = "WIP";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }
}
