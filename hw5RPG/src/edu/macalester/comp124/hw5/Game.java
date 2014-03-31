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
    public Agent player, robber, gangster, pimp;     // change this to whatever subclass player is
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
        agents.add(new Agent("pimp", "Pimp Paul", 30, 'p'));//Robbers
        agents.add(new Agent("gangster", "Gangster Emily", 25, 'n'));
        agents.add(new Agent("gangster", "Mobster Eddy", 20, 'n'));
        agents.add(new Agent("gangster", "Mobster Mike", 20, 'n'));
    }

    public void fillItems(){
        items.add(new Item("d","Donut", 3, 'd'));
        items.add(new Item("c","Coffee", 2, 'c'));
        items.add(new Item("c","Mocha Coffee", 3, 'c'));
        items.add(new Item("d","Chocolate Donut", 6, 'd'));
        items.add(new Item("c", "Coffee", 2, 'c'));
        items.add(new Item("c", "Coffee", 2, 'c'));

        for (int i=0;i< items.size(); i++){
            items.get(i).x=0;
            items.get(i).y=0;
        }
    }

    public void placeItems(){

        for (int i=0;i< items.size(); i++){
            if (items.get(i).x == 0){

                int randomX = randomNumberGenerator.nextInt(map.getWidth());
                int randomY = randomNumberGenerator.nextInt(map.getHeight());


                if ((map.terrain[randomX][randomY].equals("."))){

                    items.get(i).x = randomX;
                    items.get(i).y = randomY;

                    map.items[randomX][randomY] = (items.get(i).type);

                }

                else{
                    if (items.get(i).x == 0){
                        placeItems();
                    }

                }



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

        placeItems();


        for (int i = 0; i < agents.size(); i++) {
            agents.get(i).x = 0;
            agents.get(i).y = 0;
            for (int x = 0; x < map.getWidth(); x++){
                for (int y=0; y < map.getHeight(); y++){

                    //that specifies that the
                    int randomX = randomNumberGenerator.nextInt(map.getWidth() );
                    int randomY = randomNumberGenerator.nextInt(map.getHeight() );

                    Agent criminal = agents.get(i);

                    if (map.terrain[randomX][randomY].equals(".")){

                        criminal.x = randomX;
                        criminal.y = randomY;
                        if (criminal.character == 'r') {
                            //robber = new Robber();
                            robber.x = randomX;
                            robber.y = randomY;
                        }

                        if (criminal.character == 'n') {
                            //gangster = new Gangster();
                            gangster.x = randomX;
                            gangster.y = randomY;
                        }

                        if (criminal.character == 'p') {
                            //pimp = new Pimp();
                            pimp.x = randomX;
                            pimp.y = randomY;

                        }
                    }
                    else{
                        for (int n =0; n < agents.size(); n++){
                            if (agents.get(n).x == 0){
                                if (agents.get(n).y == 0){
                                    randomX = randomNumberGenerator.nextInt(map.getWidth() );
                                    randomY = randomNumberGenerator.nextInt(map.getHeight() );
                                    if (map.terrain[randomX][randomY].equals(".")){

                                        criminal.x = randomX;
                                        criminal.y = randomY;
                                        if (criminal.character == 'r') {
                                            //robber = new Robber();
                                            robber.x = randomX;
                                            robber.y = randomY;
                                        }

                                        if (criminal.character == 'n') {
                                            //gangster = new Gangster();
                                            gangster.x = randomX;
                                            gangster.y = randomY;
                                        }

                                        if (criminal.character == 'p') {
                                            //pimp = new Pimp();
                                            pimp.x = randomX;
                                            pimp.y = randomY;

                                        }

                                    }
                                }
                            }
                        }

                    }
                }
            }
        }


        //--- Create a player, stick him in the top left corner
        System.out.println("Enter the character's name:");
        Scanner userInput = new Scanner(System.in);
        String choice = userInput.nextLine();

        System.out.println("************************************************************************************\n");
        System.out.println("  Welcome to NYPD Donut Hunt, "+choice);
        System.out.println("  It's your first day on the job as a policeman/woman...");
        System.out.println("  You begin the game as a starving cop in search for their daily dose");
        System.out.println("  of Dunkin donuts (marked by the Dunkin donuts icon on the screen).\n");
        System.out.println("  Using the directional arrows you are able to move your player around the map");
        System.out.println("  and colliding with an opponent will set off a combat mode. Each battle lasts");
        System.out.println("  until either you or your opponent has fainted (is out of health/dies)");
        System.out.println("  If your opponent has decreased your hp, you have the opportunity to build it");
        System.out.println("  back up by consuming donuts or cups of coffee along the way.\n");
        System.out.println("  Once you have defeated each opponent, and managed to make it to the Dunkin Donuts");
        System.out.println("  icon, you have completed the game and can finally get to munch on an infinite");
        System.out.println("  amount of donuts!\n");
        System.out.println("  The goal of the game is to defeat each enemy you encounter along the way.");
        System.out.println("  They can either be a gangster, robber or pimp (each varying in difficulty");
        System.out.println("  to defeat) as you progress to the Dunkin donuts icon.\n");
        System.out.println("  Have fun!");
        System.out.println("************************************************************************************\n");

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


        if ((map.terrain[x][y].equals(".")) || (map.terrain[x][y].equals("D")) || (map.terrain[x][y].equals("r"))) {
            //--- Move the player to the new spot
            player.x = x;
            player.y = y;

        }

        //--- Assuming this is the last thing that happens in the round,
        //---	start a new round. This lets the other agents make their moves.

        getCriminal();
        getItem();
        DonutTime(player);
    }

    public void getItem(){
        for (int i = 0; i < items.size(); i++) {
            if ((player.x == items.get(i).x) && (player.y == items.get(i).y)) {

                items.get(i).takeHealth(player, items.get(i));
                items.get(i).x = 0;
                items.get(i).y = 0;
                map.items[player.x][player.y] = "."; //TODO: I wasn't replacing it correctly.
                // I was ".equals" and that was wrong.

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
        copAttacks.add(new Attack("Baton", 3, "uses a baton to beat"));
        copAttacks.add(new Attack("Pepper Spray", 1, "uses pepper spray and sprayed"));
        copAttacks.add(new Attack("Gun", 10, "uses a gun to shoot"));
    }

    public void initializeGangsterAttack() {
        gangsterAttacks.add(new Attack("Gang bang", 5, "beat"));
        gangsterAttacks.add(new Attack("Gun", 10, "uses a gun to shoot"));
    }

    public void initializePimpAttacks() {
        pimpAttacks.add(new Attack("Golden tooth smile", 1, "uses his golden tooth smile and blinds "));
        pimpAttacks.add(new Attack("Pimp stick", 6, "uses his pimp stick and beats "));
        pimpAttacks.add(new Attack("Pimp slap", 5, "slaps "));
    }

    public void initializeRobberAttacks() {
        robberAttacks.add(new Attack("Steal", 1, "uses steal to steal the cop hat of "));
        robberAttacks.add(new Attack("Super punch", 8, "punched"));
    }

    public Attack getAction(Agent character) {

        if (!(character.type.equals("robber") || (character.type.equals("gangster") || (character.type.equals("pimp"))))){

            return getHumanAction();
        }
        else{
            return getAIAction(character);
        }
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

        Attack attack = new Attack("", 0, "");

        //--- Really bad AI - just pick an attack at random
        if (criminal.type == "robber") {

            int numberOfAvailableAttacks = robberAttacks.size();
            int attackNumber = randomNumberGenerator.nextInt(numberOfAvailableAttacks);
            attack = robberAttacks.get(attackNumber);
        }

        if (criminal.type == "gangster") {

            int numberOfAvailableAttacks = gangsterAttacks.size();
            int attackNumber = randomNumberGenerator.nextInt(numberOfAvailableAttacks);
            attack = gangsterAttacks.get(attackNumber);
        }

        if (criminal.type == "pimp") {

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
                    if (agents.get(i).getClassType().equals("Agent")){

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

    }




    public void executeAttack(Agent attacker, Agent target) {
        Attack attack = getAction(attacker);

        System.out.println(attacker.getName() + "'s turn");
        int damage = attack.damage;
        target.takeDamage(damage);

        System.out.println("\n" +
                "  " + attacker.getName() + " " +
                attack.actionDescription    +
                " " + target.getName() + " for " + damage +
                " points of damage. " +
                target.health + "hp remaining.\n");

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
                    System.out.println("");

                    System.out.println("Do you want to play again? (y/n)");
                    Scanner userInput = new Scanner(System.in);
                    String choice = userInput.nextLine();
                    if (choice.equals("y")){
                        System.out.println("Starting a new game...");
                        Game game = new Game();
                        MainForm mapScreen = new MainForm(game);
                        mapScreen.setVisible(true);
                    }
                    else{
                        System.out.println("Bye!");
                        System.exit(0);
                    }
                }
            }
        }
    }

    public void DonutTime(Agent player){

        for (int x =0; x < map.getWidth(); x++){
            for (int y=0; y < map.getHeight(); y++){
                if (map.terrain[x][y].equals("D")){

                    int donutX = x;
                    int donutY = y;


                    if ((donutX == player.x)){
                        if (donutY == player.y){
                            boolean defeatedAll = false;
                            int defeatedCounter = 0;
                            for (int i=0; i < agents.size();i++){
                                if (agents.get(i).health == 0){
                                    System.out.println("Ordering donuts...");
                                    System.out.println("... ... ...");
                                    defeatedCounter += 1;
                                }
                            }

                            if (defeatedCounter < (agents.size()-1)){

                                System.out.println("Sorry! We don't take orders from the likes of you, "+player.getName() );
                                System.out.println("Maybe come back later when the shop is under new management.");
                            }

                            if (defeatedCounter == (agents.size()-1)){

                                System.out.println("DONUT TIME ----!!! "+player.getName()+", YOU WIN A LIFE TIME SUPPLY OF DONUTS!");
                                System.exit(0);
                            }
                        }



                    }
                }
            }
        }
    }
}