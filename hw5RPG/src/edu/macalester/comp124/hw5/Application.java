package edu.macalester.comp124.hw5;

import java.util.Scanner;

/**
 *
 * @author baylor
 */
public class Application
{
	public static void main(String[] args)
	{
		//--- The game is where all the interesting stuff happens
		//--- Formally, it's called the Model
		//--- The thing that draws the picture is called the View
		//--- The thing that lets players select actions is the Controller
		Game theGame = new Game();


		// TODO: Load character screen, create/edit character


		//--- The map screen is a View of our game
		//--- It's also our Controller when navigating the map
		MainForm mapScreen = new MainForm(theGame);
		mapScreen.setVisible(true);


        System.out.println("Enter the character's name and health separated by a comma e.g Bob,100?");
        Scanner userInput = new Scanner(System.in);
        String[] choice = userInput.nextLine().split(",");


        Cop cop = new Cop(choice[0], Integer.parseInt(choice[1]));

	}
}
