package edu.macalester.comp124.hw5;

/**
 * Created by Ioannis on 3/26/14.
 */
public class Item {
    public String name;
    public int healthPoints, x, y;
    public String type;
    public char character;



    public Item(String type,String name, int healthPoints, char character){
        this.name = name;
        this.healthPoints = healthPoints;
        this.type = type;
        this.character = character;

    }

    public String getName(){
        return name;
    }

    public int getHealthpoints()
    {
        return healthPoints;
    }

    public void takeHealth(Agent Player, Item item) {
        int newHealth = Player.getHealth() + item.getHealthpoints();
        Player.health = newHealth;
    }


}

