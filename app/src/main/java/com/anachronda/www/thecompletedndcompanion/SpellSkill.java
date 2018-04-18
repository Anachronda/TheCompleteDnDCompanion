package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name = "SpellSkill")
public class SpellSkill extends Model {
    @Column(name = "SkillID", index = true)
    public int id;

    @Column(name = "SkillName", index = true)
    public String name;

    @Column(name = "SpellLevel")
    public String level;

    @Column(name = "CastingTime")
    public String cast;

    @Column(name = "Duration")
    public String duration;

    @Column(name = "Range")
    public String range;

    @Column(name = "Area")
    public String area;

    @Column(name = "Type")
    public String type;

    @Column(name = "Effect")
    public String effect;

    @Column(name = "Description")
    public String description;

    public SpellSkill() { super(); }

    public SpellSkill(int id, String name, String level, String cast, String duration,
                      String range, String area, String type, String effect, String description) {

        super();
        this.id = id;
        this.name = name;
        this.level = level;
        this.cast = cast;
        this.duration = duration;
        this.range = range;
        this.area = area;
        this.type = type;
        this.effect = effect;
        this.description = description;

    }
}
