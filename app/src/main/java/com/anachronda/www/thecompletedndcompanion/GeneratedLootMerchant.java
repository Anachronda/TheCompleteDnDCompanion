package com.anachronda.www.thecompletedndcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneratedLootMerchant extends AppCompatActivity {
    private Integer listIndex = -1;
    private List<Item> lootTable;
    private List<Item> generatedList;
    private List<String> inStock;
    private Random random;
    private ListView lv;
    private ArrayAdapter<String> itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_loot_merchant);
        String lootList = getIntent().getStringExtra("List");
        generatedList = new ArrayList<>();
        lootTable = deserializeLoot(lootList);
        inStock = new ArrayList<>();
        random = new Random();

        final TextView itemName = findViewById(R.id.txtItemName);
        itemName.setText("");
        final TextView itemAttributes = findViewById(R.id.txtAttributes);
        itemAttributes.setText("");
        final TextView itemNotes = findViewById(R.id.txtNotes);
        itemNotes.setText("");
        final TextView itemDescription = findViewById(R.id.txtDescription);
        itemDescription.setText("");
        final TextView itemType = findViewById(R.id.txtType);
        itemType.setText("");
        final TextView itemAttunement = findViewById(R.id.txtAttunement);
        itemAttunement.setText("");
        final TextView itemConsumable = findViewById(R.id.txtConsumable);
        itemConsumable.setText("");

        for (Item i : lootTable){
            if (i.rarity.equals("Common")) {
                int roll = random.nextInt(99) + 75;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            } else if (i.rarity.equals("Uncommon")) {
                int roll = random.nextInt(99) + 61;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            } else if (i.rarity.equals("Rare")) {
                int roll = random.nextInt(99) + 45;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            } else if (i.rarity.equals("Very Rare")) {
                int roll = random.nextInt(99) + 31;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            } else if (i.rarity.equals("Legendary")) {
                int roll = random.nextInt(99) + 15;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            } else if (i.rarity.equals("Artifact")) {
                int roll = random.nextInt(99) + 1;
                if (roll >= 100){
                    String item = i.name + " - " + i.cost;
                    inStock.add(item);
                    generatedList.add(i);
                }
            }
        }
        if (inStock.size() == 0){
            inStock.add("No items found");
        }
        ListView lv = findViewById(R.id.lvItems);
        itemAdapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_activated_1, inStock);
        lv.setAdapter(itemAdapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;

                Item thisItem = generatedList.get(listIndex);

                String name = thisItem.name;
                String cost = thisItem.cost;
                String type = thisItem.type;
                String attributes = thisItem.attributes;
                String description = thisItem.description;
                String notes = thisItem.notes;
                String attunement;
                String consumable;
                boolean attune = thisItem.attunement;
                boolean consume = thisItem.consumable;

                if (attune){
                    attunement = "This item requires attunement.";
                } else {
                    attunement = "";
                }

                if (consume) {
                    consumable = "This item is consumable.";
                } else {
                    consumable = "";
                }

                itemName.setText(name);
                itemType.setText(type);
                itemAttributes.setText(attributes);
                itemDescription.setText(description);
                itemNotes.setText(notes);
                itemAttunement.setText(attunement);
                itemConsumable.setText(consumable);
            }
        });



    }

    public List<Item> deserializeLoot(Object o){
        if (o==null){
            return null;
        }

        List<Item> items = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> pieces = new ArrayList<>();

        String str = (String) o;

        Collections.addAll(strings, str.split("#"));

        for (String s : strings){
            Collections.addAll(pieces, s.split("%"));

            Item thisItem = new Item();

            thisItem.id = Integer.parseInt(pieces.get(0));
            thisItem.name = pieces.get(1);
            thisItem.type = pieces.get(2);
            thisItem.cost = pieces.get(3);
            thisItem.weight = pieces.get(4);
            thisItem.attributes = pieces.get(5);
            thisItem.notes = pieces.get(6);
            thisItem.description = pieces.get(7);
            if (pieces.get(8).equals("true")){
                thisItem.attunement = true;
            } else {
                thisItem.attunement = false;
            }
            if (pieces.get(9).equals("true")){
                thisItem.consumable = true;
            } else {
                thisItem.consumable = false;
            }
            thisItem.rarity = pieces.get(10);

            items.add(thisItem);
            pieces.clear();
        }

        return items;
    }

    public void goBack(View view){
        Intent intent = new Intent(this, LootMerchant.class);
        startActivity(intent);
    }
}
