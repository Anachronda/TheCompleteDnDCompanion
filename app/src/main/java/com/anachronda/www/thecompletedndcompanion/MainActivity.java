package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static Integer listIndex;
    private static List<Integer> charidlist;
    private static List<String> charlist;
    private static List<Integer> spellidlist;
    private static List<String> spelllist;
    private static ArrayAdapter<String> characterAdapter;
    public static final int CHARACTER_REQUEST = 1;
    public static final int SPELLBOOK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);
        BufferedReader in = null;

        //Check if Database Exists, otherwise populate database
        try {
            List<Class> classes = new Select()
                    .from(Class.class)
                    .execute();
            List<String> classList = new ArrayList<>();
            for (int i=0; i<classes.size(); i++) {
                String thisClass = classes.get(i).name;
                classList.add(thisClass);
            }

            if (classList.size() == 0) {
                Class barbarian = new Class();
                barbarian.id = 1;
                barbarian.name = "Barbarian";
                barbarian.hitDice = "1d12";
                barbarian.health = 12;
                barbarian.armorProf = "Light, Medium, Shields";
                barbarian.weaponProf = "Simple, Martial";
                barbarian.toolProf = "None";
                barbarian.savingThrows = "STR, CON";
                barbarian.skills = "2, Animal Handling, Athletics, Intimidation, Nature, Perception, Survival";
                barbarian.profBonus = 2;
                barbarian.save();

                Class bard = new Class();
                bard.id = 2;
                bard.name = "Bard";
                bard.hitDice = "1d8";
                bard.health = 8;
                bard.armorProf = "Light";
                bard.weaponProf = "Simple, Hand Crossbow, Longswords, Rapiers, Shortswords";
                bard.toolProf = "WIP";
                bard.savingThrows = "DEX, CHA";
                bard.skills = "3, Any";
                bard.profBonus = 2;
                bard.save();

                Class cleric = new Class();
                cleric.id = 3;
                cleric.name = "Cleric";
                cleric.hitDice = "1d8";
                cleric.health = 8;
                cleric.armorProf = "Light, Medium, Shields";
                cleric.weaponProf = "Simple";
                cleric.toolProf = "None";
                cleric.savingThrows = "WIS, CHA";
                cleric.skills = "2, History, Insight, Medicine, Persuasion, Religion";
                cleric.profBonus = 2;
                cleric.save();
            }

            List<Race> races = new Select()
                    .from(Race.class)
                    .execute();
            List<String> raceList = new ArrayList<>();
            for (int i=0; i<races.size(); i++) {
                String race = races.get(i).name;
                raceList.add(race);
            }

            if (raceList.size() == 0) {
                Race aarakocra = new Race();
                aarakocra.id = 1;
                aarakocra.name = "Aarakocra";
                aarakocra.size = "Medium";
                aarakocra.traits = "WIP";
                aarakocra.languages = "Common, Aarakocra, Auran";
                aarakocra.speed = 25;
                aarakocra.strBonus = 0;
                aarakocra.dexBonus = 2;
                aarakocra.conBonus = 0;
                aarakocra.intBonus = 0;
                aarakocra.wisBonus = 1;
                aarakocra.chaBonus = 0;
                aarakocra.save();

                Race dwarf = new Race();
                dwarf.id = 2;
                dwarf.name = "Dwarf";
                dwarf.size = "Medium";
                dwarf.traits = "WIP";
                dwarf.languages = "Common, Dwarvish";
                dwarf.speed = 25;
                dwarf.strBonus = 0;
                dwarf.dexBonus = 0;
                dwarf.conBonus = 2;
                dwarf.intBonus = 0;
                dwarf.wisBonus = 0;
                dwarf.chaBonus = 0;
                dwarf.save();

                Race elf = new Race();
                elf.id = 3;
                elf.name = "Elf";
                elf.size = "Medium";
                elf.traits = "WIP";
                elf.languages = "Common, Elvish";
                elf.speed = 25;
                elf.strBonus = 0;
                elf.dexBonus = 2;
                elf.conBonus = 0;
                elf.intBonus = 0;
                elf.wisBonus = 0;
                elf.chaBonus = 0;
                elf.save();
            }

            List<Background> backgrounds = new Select()
                    .from(Background.class)
                    .execute();
            List<String> backgroundList = new ArrayList<>();
            for (int i=0; i<backgrounds.size(); i++) {
                String background = backgrounds.get(i).name;
                backgroundList.add(background);
            }

            if (backgroundList.size() == 0) {
                Background acolyte = new Background();
                acolyte.id = 1;
                acolyte.name = "Acolyte";
                acolyte.featureName = "Shelter of the Faithful";
                acolyte.featureDesc = "As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n" +
                        "\n" +
                        "You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.";
                acolyte.proficiencies = "Religion, Insight";
                acolyte.languages = 2;
                acolyte.equipment = "A holy symbol (a gift to you when you entered the priesthood), a prayer book or prayer wheel, 5 sticks of incense, vestments, a set of common clothes, and a pouch containing 15 gp";
                acolyte.save();

                Background criminal = new Background();
                criminal.id = 2;
                criminal.name = "Criminal/Spy";
                criminal.featureName = "Criminal Contact";
                criminal.featureDesc = "You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.";
                criminal.proficiencies = "Stealth, Deception";
                criminal.languages = 0;
                criminal.equipment = "A crowbar, a set of dark common clothes including a hood, and a pouch containing 15 gp";
                criminal.save();

                Background folkhero = new Background();
                folkhero.id = 3;
                folkhero.name = "Folk Hero";
                folkhero.featureName = "Rustic Hospitality";
                folkhero.featureDesc = "Since you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.";
                folkhero.proficiencies = "Animal Handling, Survival";
                folkhero.languages = 0;
                folkhero.equipment = " A set of artisan’s tools (one of your choice), a shovel, an iron pot, a set of common clothes, and a pouch containing 10 gp";
                folkhero.save();
            }
        } catch (Exception e) {

        }

        //Grab Character List
        List<Character> characters = new Select()
                .from(Character.class)
                .orderBy("CharacterID DESC")
                .limit(3)
                .execute();
        charlist = new ArrayList<>();
        charidlist = new ArrayList<>();
        if (characters.size() == 0) {
            String placeholder = "No characters found!";
            charlist.add(placeholder);
        } else {
            for (int i=0; i<characters.size(); i++) {
                String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                Integer charID = characters.get(i).id;
                charlist.add(character);
                charidlist.add(charID);
            }
        }

        ListView clv = findViewById(R.id.characterList);
        characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
        clv.setAdapter(characterAdapter);
        clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });



        try {
//            //Grab Spellbook List
//            List<Spellbook> spellbooks = new Select()
//                    .from(Spellbook.class)
//                    .orderBy("SpellbookID DESC")
//                    .limit(3)
//                    .execute();
            spelllist = new ArrayList<>();
            spellidlist = new ArrayList<>();
//            if (spellbooks.size() == 0) {
                String placeholder = "No spellbooks found!";
                spelllist.add(placeholder);
//            }
//            else {
//                for (int i=0; i<spellbooks.size(); i++) {
//                    String character = spellbooks.get(i).name + ", Level " + spellbooks.get(i).level+ " " + spellbooks.get(i).charClass.name;
//                    Integer charID = spellbooks.get(i).id;
//                    charlist.add(character);
//                    charidlist.add(charID);
//                }
//            }
            ListView slv = findViewById(R.id.spellBook);
            ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    spelllist);

            slv.setAdapter(spellAdapter);
            slv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void characterManager(View view) {
        int selectedCharId;
        if (charlist.get(listIndex) != "No characters found!"){
            try {
                selectedCharId = charidlist.get(listIndex);
                Intent intent = new Intent(this, CharacterManager.class);
                intent.putExtra("CharacterID", selectedCharId);
                startActivity(intent);
            } catch (Exception e) {
                Context context = getApplicationContext();
                CharSequence text = "Select a character to load";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You must create a character";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }

    }

    public void newCharacter(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivityForResult(intent, CHARACTER_REQUEST);
    }

    public void deleteCharacter(View view){
        try {
            Context context = getApplicationContext();
            String delete = characterAdapter.getItem(listIndex);

            //Remove character from list
            characterAdapter.remove(delete);


            //Remove character from database
            int deletedID = charidlist.get(listIndex);
            new Delete().from(Character.class).where("CharacterID = ?", deletedID).execute();

            //Update character/id list
            List<Character> characters = new Select()
                    .from(Character.class)
                    .orderBy("CharacterID DESC")
                    .limit(3)
                    .execute();
            charlist = new ArrayList<>();
            charidlist = new ArrayList<>();
            if (characters.size() == 0) {
                String placeholder = "No characters found!";
                charlist.add(placeholder);
            } else {
                for (int i=0; i<characters.size(); i++) {
                    String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                    Integer charID = characters.get(i).id;
                    charlist.add(character);
                    charidlist.add(charID);
                }
            }
            ListView clv = findViewById(R.id.characterList);
            characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
            clv.setAdapter(characterAdapter);
            clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listIndex = position;
                }
            });

            //Finalize Delete
            CharSequence text = "Character deleted!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "You can't delete nothing...";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHARACTER_REQUEST) {
            if (resultCode == RESULT_OK){
                //Update character/id list
                List<Character> characters = new Select()
                        .from(Character.class)
                        .orderBy("CharacterID DESC")
                        .limit(3)
                        .execute();
                charlist = new ArrayList<>();
                charidlist = new ArrayList<>();
                if (characters.size() == 0) {
                    String placeholder = "No characters found!";
                    charlist.add(placeholder);
                } else {
                    for (int i=0; i<characters.size(); i++) {
                        String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                        Integer charID = characters.get(i).id;
                        charlist.add(character);
                        charidlist.add(charID);
                    }
                }
                ListView clv = findViewById(R.id.characterList);
                characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
                clv.setAdapter(characterAdapter);
                clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listIndex = position;
                    }
                });
            } else if (resultCode == RESULT_CANCELED) {

            }
        } else if (requestCode == SPELLBOOK_REQUEST) {

        }
    }

}
