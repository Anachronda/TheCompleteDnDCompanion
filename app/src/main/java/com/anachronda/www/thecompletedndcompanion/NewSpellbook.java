package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class NewSpellbook extends AppCompatActivity {
    ListView sl;
    ListView bl;
    Spinner ddl;
    List<SpellSkill> spellBook;
    List<String> bookList;
    List<String> spellList;
    List<String> charList;
    List<Integer> idList;
    List<Integer> bookIDList;
    List<Integer> charIDList;
    Integer listIndex;
    Integer bookIndex;
    Integer charIndex;
    ArrayAdapter<String> spellAdapter;
    ArrayAdapter<String> bookAdapter;
    ArrayAdapter<String> charAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spellbook);

        sl = findViewById(R.id.lvSpellList);
        bl = findViewById(R.id.lvGrimoire);
        ddl = findViewById(R.id.ddlCharacters);

        List<SpellSkill> spells = new Select()
                .from(SpellSkill.class)
                .orderBy("SkillID")
                .execute();
        spellBook = new ArrayList<>();
        spellList = new ArrayList<>();
        bookList = new ArrayList<>();
        idList = new ArrayList<>();
        bookIDList = new ArrayList<>();
        charList = new ArrayList<>();
        charIDList = new ArrayList<>();

        if(spells.size() == 0){
            String placeholder = "No spells/skills found!";
            spellList.add(placeholder);
        } else {
            for (int i=0;i<spells.size();i++) {
                String item = spells.get(i).name + " - " + spells.get(i).level + " Level " + spells.get(i).type;
                Integer itemID = spells.get(i).id;
                spellList.add(item);
                idList.add(itemID);
            }
        }

        spellAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, spellList);

        sl.setAdapter(spellAdapter);
        sl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        sl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });

        List<Character> chars = new Select()
                .from(Character.class)
                .orderBy("CharacterID")
                .execute();

        if(chars.size() == 0){
            String placeholder = "No characters found";
            charList.add(placeholder);
        } else {
            for (int i=0;i<chars.size();i++) {
                String character = chars.get(i).name + ", Level " + chars.get(i).level+ " " + chars.get(i).charClass.name;
                Integer charID = chars.get(i).id;
                charList.add(character);
                charIDList.add(charID);
            }
        }

        charAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, charList);

        ddl.setAdapter(charAdapter);
        ddl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                charIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                charIndex = -1;
            }
        });
    }

    public void addSpell(View view) {
        if (listIndex == -1){
            Context context = getApplicationContext();
            CharSequence text = "Select a spell to add";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return;
        }
        Context context = getApplicationContext();
        String thisItem = spellAdapter.getItem(listIndex);

        //Add item to book list
        bookList.add(thisItem);

        //Remove item from top list & adapter
        spellList.remove(thisItem);
        spellAdapter.remove(thisItem);

        int itemID = idList.get(listIndex);
        idList.remove(listIndex.intValue());
        bookIDList.add(itemID);
        List<SpellSkill> spells = new Select()
                .from(SpellSkill.class)
                .where("SkillID = ?", itemID)
                .execute();
        SpellSkill spell = spells.get(0);
        spellBook.add(spell);

        //Update book table

        bookAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, bookList);
        bl.setAdapter(bookAdapter);

        bl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        bl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookIndex = position;
            }
        });

        //Update spell list
        spellAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, spellList);
        sl.setAdapter(spellAdapter);
        sl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        sl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });
        bookIndex = -1;
        listIndex = -1;
    }

    public void removeSpell(View view) {
        if (bookIndex == -1){
            Context context = getApplicationContext();
            CharSequence text = "Select an spell to remove";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return;
        }
        Context context = getApplicationContext();
        String thisItem = bookAdapter.getItem(bookIndex);

        //Remove item from loot list
        bookList.remove(thisItem);
        bookAdapter.remove(thisItem);

        //Add item to top list
        spellList.add(thisItem);

        int spellID = bookIDList.get(bookIndex);
        bookIDList.remove(bookIndex.intValue());
        idList.add(spellID);
        List<SpellSkill> spells = new Select()
                .from(SpellSkill.class)
                .where("SkillID = ?", spellID)
                .execute();
        SpellSkill spell = spells.get(0);
        spellBook.remove(spell);

        //Update top list
        sl = findViewById(R.id.lvSpellList);
        spellAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, spellList);
        sl.setAdapter(spellAdapter);
        sl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        sl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });

        //Update bottom list


        bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, bookList);
        bl.setAdapter(bookAdapter);

        bl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        bl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookIndex = position;
            }
        });
        bookIndex = -1;
        listIndex = -1;
    }

    public void saveBook(View view){
        if (spellBook == null) {
            Context context = getApplicationContext();
            CharSequence text = "You must add spells before saving!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Save List");
            alert.setMessage("Enter a book name");

            final EditText input = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alert.setView(input);

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Spellbook thisBook = new Spellbook();

                    List<Spellbook> books = new Select()
                            .from(Spellbook.class)
                            .orderBy("SpellbookID DESC")
                            .execute();
                    int maxID;
                    if (books.size() > 0){
                        maxID = books.get(0).id;
                    } else {
                        maxID = 0;
                    }

                    String book = serializeBook(spellBook);

                    Integer charID = charIDList.get(charIndex);

                    thisBook.id = maxID + 1;
                    thisBook.charid = charID;
                    thisBook.name = input.getText().toString();
                    thisBook.list = book;
                    thisBook.slots = "";
                    thisBook.save();

                    Context context = getApplicationContext();
                    CharSequence text = "Table Saved";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                    dialog.dismiss();
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Context context = getApplicationContext();
                    CharSequence text = "Save Canceled";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                    dialog.dismiss();
                }
            });

            alert.show();
        }
    }

    public String serializeBook(List<SpellSkill> o){
        if (o == null){
            return null;
        }

        String str="";
        for (SpellSkill i : o){
            int id = i.id;
            String name = i.name;
            String level = i.level;
            String cast = i.cast;
            String duration = i.duration;
            String range = i.range;
            String area = i.area;
            String type = i.type;
            String effect = i.effect;
            String description = i.description;

            String item = id + "%" + name + "%" + level + "%" + cast + "%" + duration  + "%" + range  + "%" + area  + "%" + type  + "%" + effect  + "%" + description;

            str = str + item + "#";
        }
        return str;
    }
}
