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
        Game theGame = new Game();

        MainForm mapScreen = new MainForm(theGame);
        mapScreen.setVisible(true);

    }
}