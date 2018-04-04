package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Neato Banditos on 4/3/2018.
 */
@Table(name = "Class")
public class Class extends Model {
    @Column(name = "ClassID", index = true)
    public int id;

    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "HitDice")
    public String hitDice;

    @Column(name = "StartingHealth")
    public int health;

    @Column(name = "ArmorProficiencies")
    public String armorProf;

    @Column(name = "WeaponProficiencies")
    public String weaponProf;

    @Column(name = "ToolProficiencies")
    public String toolProf;

    @Column(name = "SavingThrows")
    public String savingThrows;

    @Column(name = "Skills")
    public String skills;

    @Column(name = "ProficiencyBonus")
    public int profBonus;

    public Class() {
        super();
    }

    public Class(int id, String name, String hitDice, int health, String armorProf, String weaponProf,
                 String toolProf, String savingThrows, String skills, int profBonus) {
        super();
        this.id = id;
        this.name = name;
        this.hitDice = hitDice;
        this.health = health;
        this.armorProf = armorProf;
        this.weaponProf = weaponProf;
        this.toolProf = toolProf;
        this.savingThrows = savingThrows;
        this.skills = skills;
        this.profBonus = profBonus;
    }
}
