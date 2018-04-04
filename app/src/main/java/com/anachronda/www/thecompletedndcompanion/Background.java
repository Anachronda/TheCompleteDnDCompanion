package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Neato Banditos on 4/3/2018.
 */
@Table(name = "Background")
public class Background extends Model {
    @Column(name = "BackgroundID", index = true)
    public int id;

    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "FeatureName")
    public String featureName;

    @Column(name = "FeatureDescription")
    public String featureDesc;

    @Column(name = "Proficiencies")
    public String proficiencies;

    @Column(name = "Languages")
    public int languages;

    @Column(name = "Tools")
    public String tools;

    @Column(name = "Equipment")
    public String equipment;
}
