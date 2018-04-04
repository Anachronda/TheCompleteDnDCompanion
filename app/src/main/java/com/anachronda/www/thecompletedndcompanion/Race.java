package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Devin Burch on 4/3/2018.
 */

@Table(name = "Race")
public class Race extends Model {
    @Column(name = "RaceID", index = true)
    public int id;
    
    @Column(name = "Name", index = true)
    public String name;
    
    @Column(name = "Size")
    public String size;

    @Column(name = "Traits")
    public String traits;

    @Column(name = "Languages")
    public String languages;
    
    @Column(name = "Speed")
    public int speed;

    @Column(name = "StrBonus")
    public int strBonus;

    @Column(name = "DexBonus")
    public int dexBonus;

    @Column(name = "ConBonus")
    public int conBonus;

    @Column(name = "IntBonus")
    public int intBonus;

    @Column(name = "WisBonus")
    public int wisBonus;

    @Column(name = "ChaBonus")
    public int chaBonus;

    public Race() {
        super();
    }

    public Race(int id, String name, String size, String traits, String languages,
                int speed, int strBonus, int dexBonus, int conBonus, int intBonus,
                int wisBonus, int chaBonus) {
        super();
        this.id = id;
        this.name = name;
        this.size = size;
        this.traits = traits;
        this.languages = languages;
        this.speed = speed;
        this.strBonus = strBonus;
        this.dexBonus = dexBonus;
        this.conBonus = conBonus;
        this.intBonus = intBonus;
        this.wisBonus = wisBonus;
        this.chaBonus = chaBonus;
    }
}
