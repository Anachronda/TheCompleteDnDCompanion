package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name="LootTable")
public class LootTable extends Model {
    @Column(name="TableID", index=true)
    public int id;
    @Column(name="TableName", index=true)
    public String name;
    @Column(name="Loot")
    public String loot;
    @Column(name="TopList")
    public String topList;
    @Column(name="BottomList")
    public String bottomList;

    public LootTable() { super(); }

    public LootTable(int id, String name, String loot, String top, String bottom) {
        super();
        this.id = id;
        this.name = name;
        this.loot = loot;
        this.topList = top;
        this.bottomList = bottom;
    }
}
