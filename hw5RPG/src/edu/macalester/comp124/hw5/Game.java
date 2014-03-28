package edu.macalester.comp124.hw5;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author baylor
 */
public class Game {
    public Map map;
    public Agent player, robber, gangster, pimp;    // change this to whatever subclass player is
    private Scanner userInput;


    private Random randomNumberGenerator = new Random();

    //--- A list of all the agents in the game (player, NPCs, monsters, etc.)
    //--- We need to know this so we know who to draw and so that we can ask
    //---	each turn what they want to do
    public List<Agent> agents = new LinkedList<>();
    public List<Attack> copAttacks = new LinkedList<>();
    public List<Attack> gangsterAttacks = new LinkedList<>();
    public List<Attack> pimpAttacks = new LinkedList<>();
    public List<Attack> robberAttacks = new LinkedList<>();
    public List<Item> items = new LinkedList<>();

    String mapsDirectoryName = "hw5RPG/maps/";

    public void fillCriminals() {
        agents.add(new Agent("robber", "Robber Rue", 20, 'r'));//Gangsters
        agents.add(new Agent("gangster", "Gangster Greg", 20, 'n'));//Pimps
        agents.add(new Agent("pimp", "Pimp Prince", 20, 'p'));//Robbers
        agents.add(new Agent("pimp", "Pimp Awesomeness", 30, 'p'));//Robbers
    }

    public void fillItems(){
        items.add(new Item("d","Donut", 5 , 'd'));
        items.add(new Item("d","Donut", 5 , 'd'));
        items.add(new Item("d","Donut", 5 , 'd'));
        items.add(new Item("c","coffee", 3 , 'c'));
        items.add(new Item("c","coffee", 3 , 'c'));
        items.add(new Item("c","coffee", 3 , 'c'));



        for (int i=0;i< items.size(); i++){
            int randomX = randomNumberGenerator.nextInt(map.getWidth() - 1);
            int randomY = randomNumberGenerator.nextInt(map.getHeight() - 1);



            if (map.terrain[randomX][randomY].equals(".")) {
                items.get(i).x = randomX;
                items.get(i).y = randomY;

                map.items[randomX][randomY] = (items.get(i).type);

            }

        }
    }


    public Game() {
        //--- Load a map
        map = new Map("main");
        //--- Load monsters
        initialize();

        // add your agents within the game()
        //


        for (int i = 0; i < agents.size(); i++) {

            int randomX = randomNumberGenerator.nextInt(map.getWidth() - 1);
            int randomY = randomNumberGenerator.nextInt(map.getHeight() - 1);

            if (map.terrain[randomX][randomY].equals(".")){
                //  if (map.items[randomX][randomY] == null){

                agents.get(i).x = randomX;
                agents.get(i).y = randomY;
                if (agents.get(i).character == 'r') {
                    //robber = new Robber();
                    robber.x = randomX;
                    robber.y = randomY;
                }

                if (agents.get(i).character == 'n') {
                    //gangster = new Gangster();
                    gangster.x = randomX;
                    gangster.y = randomY;
                }

                if (agents.get(i).character == 'p') {
                    //pimp = new Pimp();
                    pimp.x = randomX;
                    pimp.y = randomY;

                }
            }

        }


        //--- Create a player, stick him in the top left corner
        System.out.println("** Enter what you want the character's name to be **");
        Scanner userInput = new Scanner(System.in);
        String choice = userInput.nextLine();

        player = new Cop(choice);
        player.x = 2;
        player.y = 2;

        //--- Add the player to the agents list. This list controls
        agents.add(player);



    }





    public void initialize() {

//---Set up a scanner to read data from the terminal
        userInput = new Scanner(System.in);

//---Build a list of criminals to fight
        fillCriminals();
        fillItems();
        initializePimpAttacks();
        initializeGangsterAttack();
        initializeRobberAttacks();
        initializeCopAttacks();

    }

    public void movePlayer(int x, int y) {
        //--- Don't do anything if the move is illegal
        if (map.terrain[x][y].equals("^")) {

        }


        if ((map.terrain[x][y].equals(".")) || (map.terrain[x][y].equals("r")) || (map.terrain[x][y].equals("D"))){
            //--- Move the player to the new spot
            player.x = x;
            player.y = y;
        }
        //--- Assuming this is the last thing that happens in the round,
        //---	start a new round. This lets the other agents make their moves.

        getCriminal();
        getItem();
        DonutTime();

    }


    public void getItem(){
        for (int i = 0; i < items.size(); i++) {
                if ((player.x == items.get(i).x) && (player.y == items.get(i).y)) {

                    items.get(i).takeHealth(player, items.get(i));
                    items.get(i).x = 0;
                    items.get(i).y = 0;

                    map.items[player.x][player.y] = ".";

                    System.out.println("*****************************");
                    System.out.println("*****************************");
                    System.out.println("   You found a " + items.get(i).getName());
                    System.out.println("   Your new HP: " + player.health + " HP");
                    System.out.println("*****************************");
                    System.out.println("*****************************");


                }
            }

    }

    public void movePlayer(char direction) {
        switch (direction) {
            case 'n':
                movePlayer(player.x, player.y - 1);
                break;
            case 's':
                movePlayer(player.x, player.y + 1);
                break;
            case 'e':
                movePlayer(player.x + 1, player.y);
                break;
            case 'w':
                movePlayer(player.x - 1, player.y);
                break;
        }
    }


    public void initializeCopAttacks() {
        copAttacks.add(new Attack("Baton", 3, "lay the beat down on"));
        copAttacks.add(new Attack("Pepper Spray", 0, "sprayed"));
        copAttacks.add(new Attack("Gun", 10, "pulled out a 9mm and put three bullets in"));
    }

    public void initializeGangsterAttack() {
        gangsterAttacks.add(new Attack("Gang bang", 5, "called the homeys and jumped"));
        gangsterAttacks.add(new Attack("Gun", 10, "pulled out a shotgun and popped a cap in"));
    }

    public void initializePimpAttacks() {
        pimpAttacks.add(new Attack("Golden tooth smile", 1, "shows off that blinding smile, paralyzing"));
        pimpAttacks.add(new Attack("Pimp stick", 6, "pulls the pimp stick out and beats"));
        pimpAttacks.add(new Attack("Pimp slap", 5, "slaps"));
    }

    public void initializeRobberAttacks() {
        robberAttacks.add(new Attack("Steal", 1, "stole the cop hat of"));
        robberAttacks.add(new Attack("Super punch", 8, "punched"));
    }

    public Attack getAction(Agent character) {
        if (!(character.type.equals("robber") || (character.type.equals("gangster") || (character.type.equals("pimp")))))
            return getHumanAction();
        else
            return getAIAction(character);
    }


    protected Attack getHumanAction() {

        System.out.println("Which attack do you want to use?" + "(0-" + (copAttacks.size() - 1) + ")");
        for (int i = 0; i < copAttacks.size(); i++) {
            Attack attack = copAttacks.get(i);
            System.out.print("(" + i + ")" + attack.name);
        }

        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();
        Attack selectedAttack = copAttacks.get(choice);

        return selectedAttack;


    }

    protected Attack getAIAction(Agent criminal) {
        //--- Make sure we have an attack to use

        String criminalName = criminal.name;

        Attack attack = new Attack("", 0, "");

        //--- Really bad AI - just pick an attack at random
        if (criminal.type.equals("robber")) {

            int numberOfAvailableAttacks = robberAttacks.size();
            int attackNumber = randomNumberGenerator.nextInt(numberOfAvailableAttacks);
            attack = robberAttacks.get(attackNumber);
        }

        if (criminal.type.equals("gangster")) {

            int numberOfAvailableAttacks = gangsterAttacks.size();
            int attackNumber = randomNumberGenerator.nextInt(numberOfAvailableAttacks);
            attack = gangsterAttacks.get(attackNumber);
        }

        if (criminal.type.equals("pimp")) {

            int numberOfAvailableAttacks = pimpAttacks.size();
            int attackNumber = randomNumberGenerator.nextInt(numberOfAvailableAttacks);
            attack = pimpAttacks.get(attackNumber);

        }

        return attack;
    }


    public void getCriminal() {
        //int playerXCoordinate = player.x;
        //int playerYCoordinate = player.y;
        //if (encounter != null) {
        for (int i = 0; i < agents.size(); i++) {
            if (!(agents.get(i).equals(player))){
                if ((player.x == agents.get(i).x) && (player.y == agents.get(i).y)) {

                    if (agents.get(i).type == "robber") {
                        System.out.println("*****************************");
                        System.out.println("*****************************");
                        System.out.println("   You ran into " + agents.get(i).getName());
                        System.out.println("*****************************");
                        System.out.println("*****************************");


                    }

                    if (agents.get(i).type == "gangster") {
                        System.out.println("*****************************");
                        System.out.println("*****************************");
                        System.out.println("   You ran into " + agents.get(i).getName());
                        System.out.println("*****************************");
                        System.out.println("*****************************");
                    }


                    if (agents.get(i).type == "pimp") {
                        System.out.println("*****************************");
                        System.out.println("*****************************");
                        System.out.println("   You ran into " + agents.get(i).getName());
                        System.out.println("*****************************");
                        System.out.println("*****************************");

                    }

                    playMatch(player, agents.get(i));

                }
            }
        }
    }
    //}


    public void executeAttack(Agent attacker, Agent target) {
        Attack attack = getAction(attacker);

        System.out.println(attacker.getName() + "'s turn");
        int damage = attack.damage;
        target.takeDamage(damage);

        System.out.println("\n" +
                "\n"+
                "*****************************\n"+
                "  " + attacker.getName() + " " +
                attack.actionDescription    +
                " " + target.getName() + " for " + damage +
                " points of damage. " +
                target.health + "hp remaining.\n"+
                "*****************************\n"+"\n");

    }


    private void playMatch(Agent player, Agent criminal) { //****CHANGE: Taking in arguments of the player/cop.

        boolean matchOver = false;

        while (!matchOver) {
            executeAttack(player, criminal);
            if (criminal.hasFainted()) {
                System.out.println(criminal.getName() + " has fainted!");
                System.out.println("You win!");
                matchOver = true;
                criminal.x = 0;
                criminal.y = 0;
                criminal.type = "*";
                //TODO: Add points to the player's health, stamina, speed, etc.
            } else {
                executeAttack(criminal, player);
                if (player.hasFainted()) {
                    System.out.println(player.getName() + " has fainted!");
                    System.out.println("You lose");
                    matchOver = true;
                }
            }

//--This part ROTATES the turns. So, basically, if the criminal hasn't fainted...It's the criminal's turn now. The criminal is now the attacker.

        }
    }


    public void DonutTime () {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                if (map.terrain[x][y].equals("D"))
            {
                int donutX = x;
                int donutY = y;

                if ((donutX == player.x) && (donutY == player.y))
                {
                    System.out.println("Donut Time ---!!! YOU WINNN!");
                }
            }
            }

        }
    }


}