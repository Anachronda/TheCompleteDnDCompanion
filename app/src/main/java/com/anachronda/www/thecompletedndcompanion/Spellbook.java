package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Spellbook")
public class Spellbook extends Model {
    @Column(name = "SpellbookID", index = true)
    public int id;

    @Column(name = "CharacterID", index = true)
    public int charid;

    @Column(name = "Name")
    public String name;

    @Column(name = "Spells")
    public String list;

    @Column(name = "SpellSlots")
    public String slots;


}
