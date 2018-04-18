package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Neato Banditos on 4/3/2018.
 */

@Table(name = "Character")
public class Character extends Model {
    @Column(name = "CharacterID", index = true)
    public int id;

    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "Level")
    public int level;

    @Column(name = "Class")
    public Class charClass;

    @Column(name = "Race")
    public Race race;

    @Column(name = "Background")
    public Background bg;
    
    @Column(name = "HP")
    public int hp;

    @Column(name = "HitDice")
    public String hitDice;

    @Column(name = "SpellSlots")
    public int slots;

    @Column(name = "STR")
    public int str;

    @Column(name = "DEX")
    public int dex;

    @Column(name = "CON")
    public int con;

    @Column(name = "INT")
    public int intel;

    @Column(name = "WIS")
    public int wis;

    @Column(name = "CHA")
    public int cha;

    @Column(name = "Equipment")
    public String equip;

    public Character() {
        super();
    }

    public Character(int id, String name, int level, Class charClass, Race race, Background bg, int hp, String hitDice, int slots, int str,
                     int dex, int con, int intel, int wis, int cha, String equip) {
        super();
        this.id = id;
        this.name = name;
        this.level = level;
        this.charClass = charClass;
        this.race = race;
        this.bg = bg;
        this.hp = hp;
        this.hitDice = hitDice;
        this.slots = slots;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.equip = equip;
    }
}
