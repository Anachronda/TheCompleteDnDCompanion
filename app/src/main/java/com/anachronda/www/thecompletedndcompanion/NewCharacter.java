package com.anachronda.www.thecompletedndcompanion;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class NewCharacter extends AppCompatActivity {
    private String skillText;
    private List<Integer> classIdList;
    private List<Integer> raceIdList;
    private List<Integer> bgIdList;
    private static Integer classIndex;
    private static Integer raceIndex;
    private static Integer bgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        List<Class> classes = new Select()
                .from(Class.class)
                .orderBy("Name")
                .execute();
        List<String> classlist = new ArrayList<>();
        classIdList = new ArrayList<>();
        for(int i=0;i<classes.size();i++){
            String charClass = classes.get(i).name;
            Integer classId = classes.get(i).id;
            classlist.add(charClass);
            classIdList.add(classId);
        }
        Spinner ddlClass = findViewById(R.id.ddlClass);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classlist);
        ddlClass.setAdapter(classAdapter);
        ddlClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<Race> races = new Select()
                .from(Race.class)
                .orderBy("Name")
                .execute();
        List<String> racelist = new ArrayList<>();
        raceIdList = new ArrayList<>();
        for(int i=0;i<races.size();i++){
            String charRace = races.get(i).name;
            Integer raceId = races.get(i).id;
            racelist.add(charRace);
            raceIdList.add(raceId);
        }
        Spinner ddlRace = findViewById(R.id.ddlRace);
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, racelist);
        ddlRace.setAdapter(raceAdapter);
        ddlRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                raceIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<Background> backgrounds = new Select()
                .from(Background.class)
                .orderBy("Name")
                .execute();
        List<String> backgroundlist = new ArrayList<>();
        bgIdList = new ArrayList<>();
        for(int i=0;i<backgrounds.size();i++){
            String charBackground = backgrounds.get(i).name;
            Integer backgroundId = backgrounds.get(i).id;
            backgroundlist.add(charBackground);
            bgIdList.add(backgroundId);
        }
        Spinner ddlBackground = findViewById(R.id.ddlBackground);
        ArrayAdapter<String> backgroundAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, backgroundlist);
        ddlBackground.setAdapter(backgroundAdapter);
        ddlBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bgIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void editStr(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView str = findViewById(R.id.txtCharStr);
                    str.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void editDex(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView dex = findViewById(R.id.txtCharDex);
                    dex.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void editCon(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView con = findViewById(R.id.txtCharCon);
                    con.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void editInt(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView intel = findViewById(R.id.txtCharInt);
                    intel.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void editWis(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView wis = findViewById(R.id.txtCharWis);
                    wis.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void editCha(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Value");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skillText = input.getText().toString();
                try {
                    int skill = Integer.parseInt(skillText);
                    TextView cha = findViewById(R.id.txtCharCha);
                    cha.setText(""+skill);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Input a whole number";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void saveCharacter(View view) {
        try {
            List<Character> characters = new Select()
                    .from(Character.class)
                    .orderBy("CharacterID DESC")
                    .execute();
            TextView charName = findViewById(R.id.txtCharName);
            TextView charStr = findViewById(R.id.txtCharStr);
            TextView charDex = findViewById(R.id.txtCharDex);
            TextView charCon = findViewById(R.id.txtCharCon);
            TextView charInt = findViewById(R.id.txtCharInt);
            TextView charWis = findViewById(R.id.txtCharWis);
            TextView charCha = findViewById(R.id.txtCharCha);

            Integer charID = characters.size() + 1;

            int str = Integer.parseInt((String)charStr.getText());
            int dex = Integer.parseInt((String)charDex.getText());
            int con = Integer.parseInt((String)charCon.getText());
            int intel = Integer.parseInt((String)charInt.getText());
            int wis = Integer.parseInt((String)charWis.getText());
            int cha = Integer.parseInt((String)charCha.getText());

            String name = charName.getText().toString();

            Integer classId = classIdList.get(classIndex);
            Integer raceId = raceIdList.get(raceIndex);
            Integer bgId = bgIdList.get(bgIndex);

            List<Class> thisClass = new Select()
                    .from(Class.class)
                    .where("ClassID = " + classId)
                    .execute();

            List<Race> thisRace = new Select()
                    .from(Race.class)
                    .where("RaceID = " + raceId)
                    .execute();

            List<Background> thisBg = new Select()
                    .from(Background.class)
                    .where("BackgroundID = " + bgId)
                    .execute();

            Character thisCharacter = new Character();
            thisCharacter.id = charID;
            thisCharacter.name = name;
            thisCharacter.level = 1;
            thisCharacter.charClass = thisClass.get(0);
            thisCharacter.race = thisRace.get(0);
            thisCharacter.bg = thisBg.get(0);
            thisCharacter.hp = (thisClass.get(0).health + ((int)Math.ceil((con - 10) / 2)));
            thisCharacter.hitDice = (thisClass.get(0).hitDice);
            thisCharacter.slots = 0;
            thisCharacter.str = str;
            thisCharacter.dex = dex;
            thisCharacter.con = con;
            thisCharacter.intel = intel;
            thisCharacter.wis = wis;
            thisCharacter.cha = cha;
            thisCharacter.equip = "WIP";
            thisCharacter.save();

            Context context = getApplicationContext();
            CharSequence text = "Character saved!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();

            Intent intent = new Intent();
            intent.putExtra("ID", charID);
            setResult(Activity.RESULT_OK, intent);

            this.finish();
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "Something went wrong!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    public void cancelCharacter(View view){
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
    }
}
