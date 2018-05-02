package com.anachronda.www.thecompletedndcompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static Integer listIndex;
    private static List<Integer> charidlist;
    private static List<String> charlist;
    private static List<Integer> spellidlist;
    private static List<String> spelllist;
    private static ArrayAdapter<String> characterAdapter;
    public static final int CHARACTER_REQUEST = 1;
    public static final int SPELLBOOK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);
        BufferedReader in = null;

        //Check if Database Exists, otherwise populate database
        try {
            List<Class> classes = new Select()
                    .from(Class.class)
                    .execute();
            List<String> classList = new ArrayList<>();
            for (int i=0; i<classes.size(); i++) {
                String thisClass = classes.get(i).name;
                classList.add(thisClass);
            }

            if (classList.size() == 0) {
                Class barbarian = new Class();
                barbarian.id = 1;
                barbarian.name = "Barbarian";
                barbarian.hitDice = "1d12";
                barbarian.health = 12;
                barbarian.armorProf = "Light, Medium, Shields";
                barbarian.weaponProf = "Simple, Martial";
                barbarian.toolProf = "None";
                barbarian.savingThrows = "STR, CON";
                barbarian.skills = "2, Animal Handling, Athletics, Intimidation, Nature, Perception, Survival";
                barbarian.profBonus = 2;
                barbarian.save();

                Class bard = new Class();
                bard.id = 2;
                bard.name = "Bard";
                bard.hitDice = "1d8";
                bard.health = 8;
                bard.armorProf = "Light";
                bard.weaponProf = "Simple, Hand Crossbow, Longswords, Rapiers, Shortswords";
                bard.toolProf = "WIP";
                bard.savingThrows = "DEX, CHA";
                bard.skills = "3, Any";
                bard.profBonus = 2;
                bard.save();

                Class cleric = new Class();
                cleric.id = 3;
                cleric.name = "Cleric";
                cleric.hitDice = "1d8";
                cleric.health = 8;
                cleric.armorProf = "Light, Medium, Shields";
                cleric.weaponProf = "Simple";
                cleric.toolProf = "None";
                cleric.savingThrows = "WIS, CHA";
                cleric.skills = "2, History, Insight, Medicine, Persuasion, Religion";
                cleric.profBonus = 2;
                cleric.save();
            }

            List<Race> races = new Select()
                    .from(Race.class)
                    .execute();
            List<String> raceList = new ArrayList<>();
            for (int i=0; i<races.size(); i++) {
                String race = races.get(i).name;
                raceList.add(race);
            }

            if (raceList.size() == 0) {
                Race aarakocra = new Race();
                aarakocra.id = 1;
                aarakocra.name = "Aarakocra";
                aarakocra.size = "Medium";
                aarakocra.traits = "WIP";
                aarakocra.languages = "Common, Aarakocra, Auran";
                aarakocra.speed = 25;
                aarakocra.strBonus = 0;
                aarakocra.dexBonus = 2;
                aarakocra.conBonus = 0;
                aarakocra.intBonus = 0;
                aarakocra.wisBonus = 1;
                aarakocra.chaBonus = 0;
                aarakocra.save();

                Race dwarf = new Race();
                dwarf.id = 2;
                dwarf.name = "Dwarf";
                dwarf.size = "Medium";
                dwarf.traits = "WIP";
                dwarf.languages = "Common, Dwarvish";
                dwarf.speed = 25;
                dwarf.strBonus = 0;
                dwarf.dexBonus = 0;
                dwarf.conBonus = 2;
                dwarf.intBonus = 0;
                dwarf.wisBonus = 0;
                dwarf.chaBonus = 0;
                dwarf.save();

                Race elf = new Race();
                elf.id = 3;
                elf.name = "Elf";
                elf.size = "Medium";
                elf.traits = "WIP";
                elf.languages = "Common, Elvish";
                elf.speed = 25;
                elf.strBonus = 0;
                elf.dexBonus = 2;
                elf.conBonus = 0;
                elf.intBonus = 0;
                elf.wisBonus = 0;
                elf.chaBonus = 0;
                elf.save();
            }

            List<Background> backgrounds = new Select()
                    .from(Background.class)
                    .execute();
            List<String> backgroundList = new ArrayList<>();
            for (int i=0; i<backgrounds.size(); i++) {
                String background = backgrounds.get(i).name;
                backgroundList.add(background);
            }

            if (backgroundList.size() == 0) {
                Background acolyte = new Background();
                acolyte.id = 1;
                acolyte.name = "Acolyte";
                acolyte.featureName = "Shelter of the Faithful";
                acolyte.featureDesc = "As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n" +
                        "\n" +
                        "You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.";
                acolyte.proficiencies = "Religion, Insight";
                acolyte.languages = 2;
                acolyte.equipment = "A holy symbol (a gift to you when you entered the priesthood), a prayer book or prayer wheel, 5 sticks of incense, vestments, a set of common clothes, and a pouch containing 15 gp";
                acolyte.save();

                Background criminal = new Background();
                criminal.id = 2;
                criminal.name = "Criminal/Spy";
                criminal.featureName = "Criminal Contact";
                criminal.featureDesc = "You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.";
                criminal.proficiencies = "Stealth, Deception";
                criminal.languages = 0;
                criminal.equipment = "A crowbar, a set of dark common clothes including a hood, and a pouch containing 15 gp";
                criminal.save();

                Background folkhero = new Background();
                folkhero.id = 3;
                folkhero.name = "Folk Hero";
                folkhero.featureName = "Rustic Hospitality";
                folkhero.featureDesc = "Since you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.";
                folkhero.proficiencies = "Animal Handling, Survival";
                folkhero.languages = 0;
                folkhero.equipment = " A set of artisan’s tools (one of your choice), a shovel, an iron pot, a set of common clothes, and a pouch containing 10 gp";
                folkhero.save();
            }

            List<Item> items = new Select()
                    .from(Item.class)
                    .orderBy("ItemID DESC")
                    .execute();
            List<String> itemList = new ArrayList<>();
            for (int i=0; i<items.size(); i++) {
                String item = items.get(i).name;
                itemList.add(item);
            }
            if (itemList.size() == 0) {
                Item abacus = new Item(1,"Abacus","Adventuring Gear","2 gp","2 lbs","","Utility","A standard tool used to make calculations.",false,false,"Common");
                abacus.save();
                Item acid = new Item(2, "Acid (vial)","Adventuring Gear","25 gp","1 lb","","Combat, Damage, Utility","As an action, you can splash the contents of this vial onto a creature within 5 feet of you or throw the vial up to 20 feet, shattering it on impact. In either case, make a ranged attack against a creature or object, treating the acid as an improvised weapon. On a hit, the target takes 2d6 acid damage.",false,true,"Common");
                acid.save();
                Item adamantineArmor = new Item(3,"Adamantine Armor","Armor","Variable","Variable","Armor (medium or heavy, not hide)","Immunity: Critical Hits, Combat, Warding","This suit of armor is reinforced with adamantine, one of the hardest substances in existence. While you're wearing it, any critical hit against you becomes a normal hit.",false,false,"Uncommon");
                adamantineArmor.save();
                Item alchemistsFire = new Item(4,"Alchemist's Fire (flask)","Adventuring Gear","50 gp","1 lb","","Combat, Damage, Utility","This sticky, adhesive fluid ignites when exposed to air. As an action, you can throw this flask up to 20 feet, shattering it on impact. Make a ranged attack against a creature or object, treating the alchemist's fire as an improvised weapon. On a hit, the target takes 1d4 fire damage at the start of each of its turns. A creature can end this damage by using its action to make a DC 10 Dexterity check to extinguish the flames.",false,true,"Common");
                alchemistsFire.save();
                Item alchemistsSupplies = new Item(5,"Alchemist's Supplies","Tool","50 gp","8 lbs","","Utility","These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency.",false,false,"Common");
                alchemistsSupplies.save();
                Item ammunition1 = new Item(6,"Ammunition +1","Weapon","50 gp","0 lbs","Piercing","Bonus: Magic, Damage","You have a +1 bonus to attack and damage rolls made with this piece of magic ammunition. Once it hits a target, the ammunition is no longer magical.",false,false,"Uncommon");
                ammunition1.save();
                Item ammunition2 = new Item(7,"Ammunition +2","Weapon","150 gp","0 lbs","Piercing","Bonus: Magic, Damage","You have a +2 bonus to attack and damage rolls made with this piece of magic ammunition. Once it hits a target, the ammunition is no longer magical.",false,false,"Rare");
                ammunition2.save();
                Item ammunition3 = new Item(8,"Ammunition +3","Weapon","250 gp","0 lbs","Piercing","Bonus: Magic, Damage","You have a +3 bonus to attack and damage rolls made with this piece of magic ammunition. Once it hits a target, the ammunition is no longer magical.",false,false,"Very Rare");
                ammunition3.save();
                Item amulet = new Item(9,"Amulet","Holy Symbol","5 gp","1 lb","","Utility","A holy symbol is a representation of a god or pantheon. A cleric or paladin can use a holy symbol as a spellcasting focus, as described in the Spellcasting section. To use the symbol in this way, the caster must hold it in hand, wear it visibly, or bear it on a shield.",false,false,"Rare");
                amulet.save();
                Item amuletHealth = new Item(10,"Amulet of Health","Wondrous Item","10000 gp","1 lb","CON - Set 19","Buff, Jewelry","Your Constitution score is 19 while you wear this amulet. It has no effect on you if your Constitution is already 19 or higher without it.",true,false,"Rare");
                amuletHealth.save();
            }

            List<SpellSkill> skills = new Select()
                    .from(SpellSkill.class)
                    .execute();
            List<String> spellList = new ArrayList<>();
            for (int i=0; i<skills.size(); i++) {
                String skill = skills.get(i).name;
                spellList.add(skill);
            }
            if (spellList.size() == 0) {
                SpellSkill horridWilting = new SpellSkill(1,"Abi-Dalzim's Horrid Wilting","8th","1 Action","Instantaneous","150 ft","30 ft cube","CON Save","Necrotic","You draw the moisture from every creature in a 30-foot cube centered on a point you choose within range. Each creature in that area must make a Constitution saving throw. Constructs and undead aren’t affected, and plants and water elementals make this saving throw with disadvantage. A creature takes 12d8 necrotic damage on a failed save, or half as much damage on a successful one.\n" +
                        "\n" +
                        "Nonmagical plants in the area that aren’t creatures, such as trees and shrubs, wither and die instantly.");
                horridWilting.save();
                SpellSkill absorbElements = new SpellSkill(2,"Absorb Elements","1st","1 Reaction","1 Round","Self","","","Acid/Cold/Fire/Lightning/Thunder","The spell captures some of the incoming energy, lessening its effect on you and storing it for your next melee attack. You have resistance to the triggering damage type until the start of your next turn. Also, the first time you hit with a melee attack on your next turn, the target takes an extra 1d6 damage of the triggering type, and the spell ends.\n" +
                        "\n" +
                        "At Higher Levels. When you cast this spell using a spell slot of 2nd level or higher, the extra damage increases by 1d6 for each slot level above 1st.");
                absorbElements.save();
                SpellSkill acidArrow = new SpellSkill(3,"Acid Arrow","2nd","1 Action","Instantaneous","90 ft","","Ranged","Acid (4d4)","A shimmering green arrow streaks toward a target within range and bursts in a spray of acid. Make a ranged spell attack against the target. On a hit, the target takes 4d4 acid damage immediately and 2d4 acid damage at the end of its next turn. On a miss, the arrow splashes the target with acid for half as much of the initial damage and no damage at the end of its next turn.\n" +
                        "\n" +
                        "At Higher Levels. When you cast this spell using a spell slot of 3rd level or higher, the damage (both initial and later) increases by 1d4 for each slot level above 2nd.");
                acidArrow.save();
                SpellSkill acidSplash = new SpellSkill(4,"Acid Splash","Cantrip","1 Action","Instantaneous","60 ft","","DEX Save","Acid (1d6)","You hurl a bubble of acid. Choose one creature within range, or choose two creatures within range that are within 5 feet of each other. A target must succeed on a Dexterity saving throw or take 1d6 acid damage.\n" +
                        "\n" +
                        "This spell’s damage increases by 1d6 when you reach 5th level (2d6), 11th level (3d6), and 17th level (4d6).");
                acidSplash.save();
                SpellSkill aganazzarScorcher = new SpellSkill(5,"Aganazzar's Scorcher","2nd","1 Action","Instantaneous","30 ft","30 ft line","DEX Save","Fire (3d8)","A line of roaring flame 30 feet long and 5 feet wide emanates from you in a direction you choose. Each creature in the line must make a Dexterity saving throw. A creature takes 3d8 fire damage on a failed save, or half as much damage on a successful one.\n" +
                        "\n" +
                        "At Higher Levels. When you cast this spell using a spell slot of 3rd level or higher, the damage increases by 1d8 for each slot level above 2nd.");
                aganazzarScorcher.save();
                SpellSkill aid = new SpellSkill(6,"Aid","2nd","1 Action","8 Hours","30 ft","","Buff","3 Creatures Max and Current HP +5","Your spell bolsters your allies with toughness and resolve. Choose up to three creatures within range. Each target's hit point maximum and current hit points increase by 5 for the duration.\n" +
                        "\n" +
                        "At Higher Levels. When you cast this spell using a spell slot of 3rd level or higher, a target's hit points increase by an additional 5 for each slot level above 2nd.");
                aid.save();
                SpellSkill alarm = new SpellSkill(7,"Alarm","1st","1 Minute","8 Hours","30 ft","<=20 ft cube","Detection","Alarm heard 60ft around","You set an alarm against unwanted intrusion. Choose a door, a window, or an area within range that is no larger than a 20-foot cube. Until the spell ends, an alarm alerts you whenever a Tiny or larger creature touches or enters the warded area. When you cast the spell, you can designate creatures that won't set off the alarm. You also choose whether the alarm is mental or audible.\n" +
                        "\n" +
                        "A mental alarm alerts you with a ping in your mind if you are within 1 mile of the warded area. This ping awakens you if you are sleeping.\n" +
                        "\n" +
                        "An audible alarm produces the sound of a hand bell for 10 seconds within 60 feet.");
                alarm.save();
                SpellSkill alterSelf = new SpellSkill(8,"Alter Self","2nd","1 Action","1 Hour","Self","","Transmutation","Shapechanging","You assume a different form. When you cast the spell, choose one of the following options, the effects of which last for the duration of the spell. While the spell lasts, you can end one option as an action to gain the benefits of a different one.\n" +
                        "\n" +
                        "Aquatic Adaptation. You adapt your body to an aquatic environment, sprouting gills and growing webbing between your fingers. You can breathe underwater and gain a swimming speed equal to your walking speed.\n" +
                        "\n" +
                        "Change Appearance. You transform your appearance. You decide what you look like, including your height, weight, facial features, sound of your voice, hair length, coloration, and distinguishing characteristics, if any. You can make yourself appear as a member of another race, though none of your statistics change. You also can't appear as a creature of a different size than you, and your basic shape stays the same; if you're bipedal, you can't use this spell to become quadrupedal, for instance. At any time for the duration of the spell, you can use your action to change your appearance in this way again.\n" +
                        "\n" +
                        "Natural Weapons. You grow claws, fangs, spines, horns, or a different natural weapon of your choice. Your unarmed strikes deal 1d6 bludgeoning, piercing, or slashing damage, as appropriate to the natural weapon you chose, and you are proficient with your unarmed strikes. Finally, the natural weapon is magic and you have a +1 bonus to the attack and damage rolls you make using it.");
                alterSelf.save();
                SpellSkill animalFriendship = new SpellSkill(9,"Animal Friendship","1st","1 Action","24 Hours","30 ft","","WIS Save","Charmed","This spell lets you convince a beast that you mean it no harm. Choose a beast that you can see within range. It must see and hear you. If the beast's Intelligence is 4 or higher, the spell fails. Otherwise, the beast must succeed on a Wisdom saving throw or be charmed by you for the spell's duration. If you or one of your companions harms the target, the spells ends.\n" +
                        "\n" +
                        "At Higher Levels. When you cast this spell using a spell slot of 2nd level or higher, you can affect one additional beast level above 1st.");
                animalFriendship.save();
                SpellSkill animalShapes = new SpellSkill(10,"Animal Messenger","2nd","1 Action","24 Hours","30 ft","25 mile","Enchantment","Communication","By means of this spell, you use an animal to deliver a message. Choose a Tiny beast you can see within range, such as a squirrel, a blue jay, or a bat. You specify a location, which you must have visited, and a recipient who matches a general description, such as \"a man or woman dressed in the uniform of the town guard\" or \"a red-haired dwarf wearing a pointed hat.\" You also speak a message of up to twenty-five words. The target beast travels for the duration of the spell toward the specified location, covering about 50 miles per 24 hours for a flying messenger, or 25 miles for other animals.\n" +
                        "\n" +
                        "When the messenger arrives, it delivers your message to the creature that you described, replicating the sound of your voice. The messenger speaks only to a creature matching the description you gave. If the messenger doesn't reach its destination before the spell ends, the message is lost, and the beast makes its way back to where you cast this spell.\n" +
                        "\n" +
                        "At Higher Levels. If you cast this spell using a spell slot of 3rd level or higher, the duration of the spell increases by 48 hours for each slot level above 2nd.");
                animalShapes.save();
            }
        } catch (Exception e) {

        }

        //Grab Character List
        List<Character> characters = new Select()
                .from(Character.class)
                .orderBy("CharacterID DESC")
                .limit(3)
                .execute();
        charlist = new ArrayList<>();
        charidlist = new ArrayList<>();
        if (characters.size() == 0) {
            String placeholder = "No characters found!";
            charlist.add(placeholder);
        } else {
            for (int i=0; i<characters.size(); i++) {
                String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                Integer charID = characters.get(i).id;
                charlist.add(character);
                charidlist.add(charID);
            }
        }

        ListView clv = findViewById(R.id.characterList);
        characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
        clv.setAdapter(characterAdapter);
        clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIndex = position;
            }
        });



        try {
//            //Grab Spellbook List
            List<Spellbook> spellbooks = new Select()
                    .from(Spellbook.class)
                    .orderBy("SpellbookID DESC")
                    .limit(3)
                    .execute();
            spelllist = new ArrayList<>();
            spellidlist = new ArrayList<>();
            if (spellbooks.size() == 0) {
                String placeholder = "No spellbooks found!";
                spelllist.add(placeholder);
            }
            else {
                for (int i=0; i<spellbooks.size(); i++) {
                    String spellbook = spellbooks.get(i).name;
                    Integer bookID = spellbooks.get(i).id;
                    spelllist.add(spellbook);
                    spellidlist.add(bookID);
                }
            }
            ListView slv = findViewById(R.id.spellBook);
            ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    spelllist);

            slv.setAdapter(spellAdapter);
            slv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void characterManager(View view) {
        try {
            int selectedCharId;
            if (charlist.get(listIndex) != "No characters found!"){
                try {
                    selectedCharId = charidlist.get(listIndex);
                    Intent intent = new Intent(this, CharacterManager.class);
                    intent.putExtra("CharacterID", selectedCharId);
                    startActivity(intent);
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Select a character to load";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            } else {
                Context context = getApplicationContext();
                CharSequence text = "You must create a character";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        } catch (Exception e){
            Context context = getApplicationContext();
            CharSequence text = "No character selected!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    public void newCharacter(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivityForResult(intent, CHARACTER_REQUEST);
    }

    public void deleteCharacter(View view){
        try {
            Context context = getApplicationContext();
            String delete = characterAdapter.getItem(listIndex);

            //Remove character from list
            characterAdapter.remove(delete);


            //Remove character from database
            int deletedID = charidlist.get(listIndex);
            new Delete().from(Character.class).where("CharacterID = ?", deletedID).execute();

            //Update character/id list
            List<Character> characters = new Select()
                    .from(Character.class)
                    .orderBy("CharacterID DESC")
                    .limit(3)
                    .execute();
            charlist = new ArrayList<>();
            charidlist = new ArrayList<>();
            if (characters.size() == 0) {
                String placeholder = "No characters found!";
                charlist.add(placeholder);
            } else {
                for (int i=0; i<characters.size(); i++) {
                    String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                    Integer charID = characters.get(i).id;
                    charlist.add(character);
                    charidlist.add(charID);
                }
            }
            ListView clv = findViewById(R.id.characterList);
            characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
            clv.setAdapter(characterAdapter);
            clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listIndex = position;
                }
            });

            //Finalize Delete
            CharSequence text = "Character deleted!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "You can't delete nothing...";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    public void spellbookManager(View view) {
        Intent intent = new Intent(this, SpellSkillManager.class);
        startActivity(intent);
    }

    public void newSpellbook(View view) {
        Intent intent = new Intent(this,NewSpellbook.class);
        startActivity(intent);
    }

    public void deleteSpellbook(View view){
        Context context = getApplicationContext();
        CharSequence text = "Spellbook/Skillset deleted!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }

    public void diceTool(View view){
        Intent intent = new Intent(this, DiceTool.class);
        startActivity(intent);
    }

    public void lootMerchant(View view) {
        Intent intent = new Intent(this, LootMerchant.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHARACTER_REQUEST) {
            if (resultCode == RESULT_OK){
                //Update character/id list
                List<Character> characters = new Select()
                        .from(Character.class)
                        .orderBy("CharacterID DESC")
                        .limit(3)
                        .execute();
                charlist = new ArrayList<>();
                charidlist = new ArrayList<>();
                if (characters.size() == 0) {
                    String placeholder = "No characters found!";
                    charlist.add(placeholder);
                } else {
                    for (int i=0; i<characters.size(); i++) {
                        String character = characters.get(i).name + ", Level " + characters.get(i).level+ " " + characters.get(i).charClass.name;
                        Integer charID = characters.get(i).id;
                        charlist.add(character);
                        charidlist.add(charID);
                    }
                }
                ListView clv = findViewById(R.id.characterList);
                characterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, charlist);
                clv.setAdapter(characterAdapter);
                clv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        } else if (requestCode == SPELLBOOK_REQUEST) {

        }
    }

}
