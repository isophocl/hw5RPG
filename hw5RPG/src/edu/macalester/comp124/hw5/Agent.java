package edu.macalester.comp124.hw5;

/**
 * An agent is an entity that can move around. Examples: player, NPCs, monsters.
 * You can add code here if you need something or you can make subclasses
 * of this. What's important here is that there's an ID. This is used by
 * the rendering system to determine which picture to draw for it.
 * @author baylor
 */


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * An agent is an entity that can move around. Examples: player, NPCs, monsters.
 * You can add code here if you need something or you can make subclasses
 * of this. What's important here is that there's an ID. This is used by
 * the rendering system to determine which picture to draw for it.
 * @author baylor
 */
public class Agent
{
    public String type;	// tells us which picture to draw for this agent
    public int x, y;
    public String name;
    public int health;
    public boolean isHumanControlled = false;
    public char character;



    public Agent(String type, String name, int health, char character)
    {
        this.type = type;
        this.name = name;
        this.health = health;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth ()
    {
        return health;
    }

    public String getClassType()
    {
        return "Agent";
    }

    public void takeDamage(int damage){
        health = health - damage;

        if (0 > health){
            health=0;
        }
    }

    public void takeHealth(int healthPoints){
        health = health + healthPoints;
    }

    public boolean hasFainted()
    {
        return (health <= 0);
    }



}
