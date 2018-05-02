package com.anachronda.www.thecompletedndcompanion;

import com.anachronda.www.thecompletedndcompanion.StringListSerializer;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.serializer.TypeSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LootMerchant extends AppCompatActivity {

    private static String listName;
    private static Integer listID;
    private static Integer listIndex = -1;
    private static Integer lootIndex = -1;
    private static Integer tableIndex = -1;
    private static List<String> itemList;
    private static List<String> lootList;
    private static List<Integer> idList;
    private static List<Integer> lootIDList;
    private static List<Item> lootTable;
    private static List<LootTable> tableList;
    private static List<String> tableNameList;
    private static List<Integer> tableIDList;
    private static ArrayAdapter<String> itemAdapter;
    private static ArrayAdapter<String> lootAdapter;

    public static final int ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loot_merchant);

        List<Item> items = new Select()
                .from(Item.class)
                .orderBy("ItemID")
                .execute();
        itemList = new ArrayList<>();
        lootList = new ArrayList<>();
        lootTable = new ArrayList<>();
        idList = new ArrayList<>();
        lootIDList = new ArrayList<>();

        if(items.size() == 0){
            String placeholder = "No items found!";
            itemList.add(placeholder);
        } else {
            for (int i=0;i<items.size();i++) {
                String item = items.get(i).name + " - " + items.get(i).type + " (" + items.get(i).notes +")";
                Integer itemID = items.get(i).id;
                itemList.add(item);
                idList.add(itemID);
            }
        }

        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };

        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });
    }

    public void selectAll(View view) {
        Context context = getApplicationContext();

        //Add items from top to bottom
        lootList.addAll(itemList);

        //Add all items to loot table
        lootTable = new Select().from(Item.class).orderBy("ItemID").execute();

        //Remove all items from top list
        itemList.clear();

        //Update loot table
        ListView table = findViewById(R.id.lvTable);

        lootAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, lootList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        table.setAdapter(lootAdapter);

        table.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lootIndex = position;
            }
        });

        //Update item list
        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });
        lootIndex = -1;
        listIndex = -1;
    }

    public void removeAll(View view) {
        Context context = getApplicationContext();

        //Add items from top to bottom
        itemList.addAll(lootList);

        //Remove all items from loot table
        lootTable = new ArrayList<>();

        //Remove all items from bottom list
        lootList.clear();

        //Update loot table
        ListView table = findViewById(R.id.lvTable);

        lootAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, lootList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        table.setAdapter(lootAdapter);

        table.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lootIndex = position;
            }
        });

        //Update item list
        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });
        lootIndex = -1;
        listIndex = -1;
    }

    public void addItem(View view) {
        if (listIndex == -1){
            Context context = getApplicationContext();
            CharSequence text = "Select an item to move";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return;
        }
        Context context = getApplicationContext();
        String thisItem = itemAdapter.getItem(listIndex);

        //Add item to loot list
        lootList.add(thisItem);

        //Remove item from top list & adapter
        itemList.remove(thisItem);
        itemAdapter.remove(thisItem);

        int itemID = idList.get(listIndex);
        idList.remove(listIndex.intValue());
        lootIDList.add(itemID);
        List<Item> items = new Select()
                .from(Item.class)
                .where("ItemID = ?", itemID)
                .execute();
        Item item = items.get(0);
        lootTable.add(item);

        //Update loot table
        ListView table = findViewById(R.id.lvTable);

        lootAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, lootList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        table.setAdapter(lootAdapter);

        table.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lootIndex = position;
            }
        });

        //Update item list
        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });
        lootIndex = -1;
        listIndex = -1;
    }

    public void removeItem(View view) {
        if (lootIndex == -1){
            Context context = getApplicationContext();
            CharSequence text = "Select an item to move";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return;
        }
        Context context = getApplicationContext();
        String thisItem = lootAdapter.getItem(lootIndex);

        //Remove item from loot list
        lootList.remove(thisItem);
        lootAdapter.remove(thisItem);

        //Add item to top list
        itemList.add(thisItem);

        int itemID = lootIDList.get(lootIndex);
        lootIDList.remove(lootIndex.intValue());
        idList.add(itemID);
        List<Item> items = new Select()
                .from(Item.class)
                .where("ItemID = ?", itemID)
                .execute();
        Item item = items.get(0);
        lootTable.remove(item);

        //Update top list
        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });

        //Update bottom list
        ListView table = findViewById(R.id.lvTable);

        lootAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, lootList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        table.setAdapter(lootAdapter);

        table.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lootIndex = position;
            }
        });
        lootIndex = -1;
        listIndex = -1;
    }

    public void newItem(View view) {
        Intent intent = new Intent(this, NewItem.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    public void deleteItem(View view){
        try {
            Context context = getApplicationContext();
            String delete = itemAdapter.getItem(listIndex);

            //Remove character from list
            itemAdapter.remove(delete);


            //Remove character from database
            int deletedID = idList.get(listIndex);
            new Delete().from(Item.class).where("ItemID = ?", deletedID).execute();

            //Update character/id list
            List<Item> items = new Select()
                    .from(Item.class)
                    .orderBy("ItemID")
                    .execute();
            itemList = new ArrayList<>();
            idList = new ArrayList<>();
            if(items.size() == 0){
                String placeholder = "No items found!";
                itemList.add(placeholder);
            } else {
                for (int i=0;i<items.size();i++) {
                    String item = items.get(i).name + " - " + items.get(i).type + " (" + items.get(i).notes +")";
                    Integer itemID = items.get(i).id;
                    itemList.add(item);
                    idList.add(itemID);
                }
            }
            ListView iv = findViewById(R.id.lvItemList);
            itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);

                    textView.setTextColor(Color.BLACK);

                    return textView;
                }
            };
            iv.setAdapter(itemAdapter);
            iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listIndex = position;
                }
            });

            //Finalize Delete
            CharSequence text = "Item deleted!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            lootIndex = -1;
            listIndex = -1;
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "You can't delete nothing...";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            lootIndex = -1;
            listIndex = -1;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK){
                //Update character/id list
                List<Item> items = new Select()
                        .from(Item.class)
                        .orderBy("ItemID")
                        .execute();
                itemList = new ArrayList<>();
                idList = new ArrayList<>();
                if(items.size() == 0){
                    String placeholder = "No items found!";
                    itemList.add(placeholder);
                } else {
                    for (int i=0;i<items.size();i++) {
                        String item = items.get(i).name + " - " + items.get(i).type + " (" + items.get(i).notes +")";
                        Integer itemID = items.get(i).id;
                        itemList.add(item);
                        idList.add(itemID);
                    }
                }
                ListView iv = findViewById(R.id.lvItemList);
                itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, itemList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextView textView = (TextView) super.getView(position, convertView, parent);

                        textView.setTextColor(Color.BLACK);

                        return textView;
                    }
                };
                iv.setAdapter(itemAdapter);
                iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listIndex = position;
                    }
                });
            } else if (resultCode == RESULT_CANCELED) {
                Context context = getApplicationContext();
                CharSequence text = "Creation Canceled";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        }
    }

    public void saveList(View view){
        if (listID == null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Save List");
            alert.setMessage("Enter a list name");

            final EditText input = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alert.setView(input);

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LootTable thisTable = new LootTable();

                    List<LootTable> tables = new Select()
                            .from(LootTable.class)
                            .orderBy("TableID DESC")
                            .execute();
                    int maxID;
                    if (tables.size() > 0){
                        maxID = tables.get(0).id;
                    } else {
                        maxID = 0;
                    }

                    String loot = serializeLoot(lootTable);
                    String top = serialize(itemList);
                    String bottom = serialize(lootList);

                    thisTable.id = maxID + 1;
                    thisTable.name = input.getText().toString();
                    thisTable.loot = loot;
                    thisTable.topList = top;
                    thisTable.bottomList = bottom;
                    thisTable.save();

                    listID = thisTable.id;
                    listName = thisTable.name;

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
        } else {
            LootTable thisTable = new LootTable();

            List<LootTable> tables = new Select()
                    .from(LootTable.class)
                    .where("TableID =?" + listID)
                    .execute();

            new Delete().from(LootTable.class).where("TableID =?" + listID);

            String loot = serializeLoot(lootTable);
            String top = serialize(itemList);
            String bottom = serialize(lootList);

            thisTable.id = listID;
            thisTable.name = listName;
            thisTable.loot = loot;
            thisTable.topList = top;
            thisTable.bottomList = bottom;
            thisTable.save();

            Context context = getApplicationContext();
            CharSequence text = "Table Saved";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    public void loadList(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Load List");
        alert.setMessage("Select a list to load");

        final ListView input = new ListView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        List<LootTable> tables = new Select()
                .from(LootTable.class)
                .orderBy("TableID DESC")
                .execute();
        tableList = new ArrayList<>(tables);
        tableIDList = new ArrayList<>();
        tableNameList = new ArrayList<>();

        if(tables.size() == 0){
            String placeholder = "No tables found!";
            itemList.add(placeholder);
        } else {
            for (int i=0;i<tables.size();i++) {
                String table = tables.get(i).name;
                Integer tableID = tables.get(i).id;
                tableNameList.add(table);
                tableIDList.add(tableID);
            }
        }

        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, tableNameList);
        input.setAdapter(list);
        input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tableIndex = position;
            }
        });
        input.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        alert.setView(input);

        alert.setPositiveButton("Load", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (tableIndex == -1){
                    Context context = getApplicationContext();
                    CharSequence text = "Select a table to load";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                } else if ((new Select().from(LootTable.class).execute()).size() > 0 ){
                    lootList = deserialize(tableList.get(tableIndex).bottomList);
                    itemList = deserialize(tableList.get(tableIndex).topList);
                    lootTable = deserializeLoot(tableList.get(tableIndex).loot);

                    //Update top list
                    ListView iv = findViewById(R.id.lvItemList);
                    itemAdapter = new ArrayAdapter<String>(LootMerchant.this, android.R.layout.simple_list_item_activated_1, itemList) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            TextView textView = (TextView) super.getView(position, convertView, parent);

                            textView.setTextColor(Color.BLACK);

                            return textView;
                        }
                    };
                    iv.setAdapter(itemAdapter);
                    iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                    iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            listIndex = position;
                        }
                    });

                    //Update bottom list
                    ListView tv = findViewById(R.id.lvTable);

                    lootAdapter = new ArrayAdapter<String>(LootMerchant.this, android.R.layout.simple_list_item_activated_1, lootList) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            TextView textView = (TextView) super.getView(position, convertView, parent);

                            textView.setTextColor(Color.BLACK);

                            return textView;
                        }
                    };
                    tv.setAdapter(lootAdapter);

                    tv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                    tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            lootIndex = position;
                        }
                    });

                    listName = tableList.get(tableIndex).name;
                    listID = tableList.get(tableIndex).id;

                    Context context = getApplicationContext();
                    CharSequence text = "Load Complete";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                    dialog.dismiss();
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = getApplicationContext();
                CharSequence text = "Load Canceled";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                dialog.dismiss();
                itemAdapter.notifyDataSetChanged();
            }
        });
        alert.show();
    }

    public void deleteList(View view){
        new Delete().from(LootTable.class).where("TableID =?" + listID).execute();

        Context context = getApplicationContext();
        CharSequence text = "Load Canceled";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        //Update top list
        ListView iv = findViewById(R.id.lvItemList);
        itemAdapter = new ArrayAdapter<String>(LootMerchant.this, android.R.layout.simple_list_item_activated_1, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        iv.setAdapter(itemAdapter);
        iv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });

        //Update bottom list
        ListView tv = findViewById(R.id.lvTable);

        lootAdapter = new ArrayAdapter<String>(LootMerchant.this, android.R.layout.simple_list_item_activated_1, lootList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };
        tv.setAdapter(lootAdapter);

        tv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lootIndex = position;
            }
        });

        lootAdapter.notifyDataSetChanged();
        itemAdapter.notifyDataSetChanged();

        listID = null;
        listName = null;
    }

    public void generateList(View view){
        if (lootTable.size() != 0){
            Intent intent = new Intent(this, GeneratedLootMerchant.class);
            String loot = serializeLoot(lootTable);
            intent.putExtra("List", loot);
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Add some items first!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    public String serialize(Object o){
        if (o == null){
            return null;
        }

        String str = "";
        for (String s : (List<String>) o) {
            str = str + s + "#";
        }

        return str;
    }

    public List<String> deserialize(Object o){
        if (o==null){
            return null;
        }
        String[] str = o.toString().split("#");
        List<String> strings = new ArrayList<>();
        strings = Arrays.asList(str);

        return strings;
    }

    public String serializeLoot(List<Item> o){
        if (o == null){
            return null;
        }

        String str="";
        for (Item i : o){
            int id = i.id;
            String name = i.name;
            String type = i.type;
            String cost = i.cost;
            String weight = i.weight;
            String attributes = i.attributes;
            String notes = i.notes;
            String description = i.description;
            String rarity = i.rarity;
            boolean attunement = i.attunement;
            boolean consumable = i.consumable;
            String attune;
            String consume;

            if (attunement) {
                attune = "true";
            } else {
                attune = "false";
            }

            if (consumable) {
                consume = "true";
            } else {
                consume = "false";
            }


            String item = id + "%" + name + "%" + type + "%" + cost + "%" + weight  + "%" + attributes  + "%" + notes  + "%" + description  + "%" + attune  + "%" + consume   + "%" + rarity;

            str = str + item + "#";
        }
        return str;
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

    public void wipMessage(View view){
    Context context = getApplicationContext();
    CharSequence text = "WIP!!!";
    int duration = Toast.LENGTH_SHORT;

    Toast.makeText(context, text, duration).show();
}
}
