package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Item")
public class Item extends Model {
    @Column(name = "ItemID", index = true)
    public int id;

    @Column(name = "ItemName", index = true)
    public String name;

    @Column(name = "Type")
    public String type;

    @Column(name = "Cost")
    public String cost;

    @Column(name = "Weight")
    public String weight;

    @Column(name = "Attributes")
    public String attributes;

    @Column(name = "Notes")
    public String notes;

    @Column(name = "Description")
    public String description;

    @Column(name = "Attunement")
    public boolean attunement;

    @Column(name = "Consumable")
    public boolean consumable;

    @Column(name = "Rarity")
    public String rarity;

    public Item() { super(); }

    public Item(int id, String name, String type, String cost, String weight, String attributes,
                String notes, String description, boolean attunement, boolean consumable, String rarity){
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.weight = weight;
        this.attributes = attributes;
        this.notes = notes;
        this.description = description;
        this.attunement = attunement;
        this.consumable = consumable;
        this.rarity = rarity;
    }
}
