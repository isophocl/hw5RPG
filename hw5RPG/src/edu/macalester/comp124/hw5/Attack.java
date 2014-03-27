package edu.macalester.comp124.hw5;

/**
 * Created by Ioannis on 3/27/14.
 */
public class Attack {

    public int damage;
    public String name = "";
    public String actionDescription = "attacks";

    public Attack (String name, int damage, String actionDescription){
        this.name = name;
        this.damage = damage;
        this.actionDescription = actionDescription;
    }

    public int getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }

}
