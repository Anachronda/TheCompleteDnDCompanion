package com.anachronda.www.thecompletedndcompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.List;

public class NewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }

    protected void saveItem(View view){
        try {
            List<Item> items = new Select()
                   .from(Item.class)
                   .orderBy("ItemID DESC")
                   .execute();
            int maxID = items.get(0).id;

            TextView itemName = findViewById(R.id.txtItemName);
            TextView itemCost = findViewById(R.id.txtCost);
            TextView itemWeight = findViewById(R.id.txtWeight);
            TextView itemAttributes = findViewById(R.id.txtAttributes);
            TextView itemNotes = findViewById(R.id.txtNotes);
            TextView itemDescription = findViewById(R.id.txtDescription);

            Spinner itemType = findViewById(R.id.ddlType);
            Spinner itemRarity = findViewById(R.id.ddlRarity);

            CheckBox itemAttunement = findViewById(R.id.cbAttunement);
            CheckBox itemConsumable = findViewById(R.id.cbConsumable);

            int itemID = maxID + 1;

            String name = itemName.getText().toString();
            String cost = itemCost.getText().toString();
            String weight = itemWeight.getText().toString();
            String attributes = itemAttributes.getText().toString();
            String notes = itemNotes.getText().toString();
            String description = itemDescription.getText().toString();
            String type = itemType.getSelectedItem().toString();
            String rarity = itemRarity.getSelectedItem().toString();

            Boolean attunement;
            Boolean consumable;

            if (itemAttunement.isChecked()){
                attunement = true;
            } else{
                attunement = false;
            }
            if (itemConsumable.isChecked()){
                consumable = true;
            } else{
                consumable = false;
            }

            Item thisItem = new Item();

            thisItem.id = itemID;
            thisItem.name = name;
            thisItem.cost = cost;
            thisItem.weight = weight;
            thisItem.attributes = attributes;
            thisItem.notes = notes;
            thisItem.description = description;
            thisItem.type = type;
            thisItem.rarity = rarity;
            thisItem.attunement = attunement;
            thisItem.consumable = consumable;
            thisItem.save();
            Context context = getApplicationContext();
            CharSequence text = "Item saved!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();

            Intent intent = new Intent();
            intent.putExtra("ID", itemID);
            setResult(Activity.RESULT_OK, intent);

            this.finish();
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "Something went wrong!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    protected void cancelItem(View view) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        this.finish();
    }
}
