package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    GameHelper gameHelper = new GameHelper();
    boolean forEach = true; // A boolean I need for breaking a for-loop if player wants to exit for main menu in game.
    public Game() {
        // Start with main menu when program is running.
        boolean startMenu = true;
        while(startMenu) {
            Scanner input = new Scanner(System.in); // So we can get user input.
            gameHelper.menuClearScreen();// Clear the screen for to much text in terminal.
            System.out.println("""
                                ------------------------
                                |  ANIMAl MASTER 2000  |
                                ------------------------
                                #   |1| - Start game   #
                                #   |2| - Game info    #
                                #                      #
                                #   |3| - Exit         #
                                ------------------------""");
            //Checks so user enters right input and stops from getting exceptions.
            int choice = 0;
            while (choice < 1 || choice > 3) {
                try {
                    System.out.print("Enter an option: ");
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("You must enter a number in the menu.");
                }
            }
            //Switch-cases for the user choice.
            switch (choice){
                case 1 -> startGame(); // Continue the game
                case 2 -> gameHelper.gameInfo(); // Game info in text
                case 3 -> {  //Option to exit the game.
                    boolean exitMenu = true;
                    choice = 0;
                    while (exitMenu) {
                        while (choice < 1 || choice > 2) {  // Looping the exit menu until the user makes a
                            gameHelper.menuClearScreen();     // correct choice. Must press 1 or 2.
                            System.out.println("""
                                                ------------------------
                                                |      EXIT GAME       |
                                                ------------------------
                                                # |1| - Yes.
                                                # |2| - No.""");
                            try {
                                System.out.println("Enter an option: ");
                                choice = input.nextInt();
                            } catch (Exception e) {
                                input.next(); // Must have or it will be looping all over the place.
                            }                 // Don't want any error message just return to the choices.
                        }
                        if(choice == 1){ // If user press 1 , game will close.
                            gameHelper.menuClearScreen();
                            System.out.println("Game is closing...");
                            exitMenu = false;
                            startMenu = false;
                        }
                        if(choice == 2){ // If user press 2, return to start menu.
                            exitMenu = false;
                        }

                    }
                }
            }
        }
    }

    //Method to start the real game.
    public void startGame(){

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>(); // Must have a list to save the players in.
        int numberOfTurns = 0; // User decide how many turns the game will do before ends.
        while(numberOfTurns < 5 || numberOfTurns > 30) { // Must be 5-30 turns or user wont continue.
            gameHelper.menuClearScreen(); // Clear text
            System.out.println("Enter how many rounds you want the game to be, between 5-30 rounds!");
            System.out.print("Number of rounds: ");
            try { // Handle exceptions.
                numberOfTurns = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }
        }
        // Ask the user how many players will join the game.
        int numberOfPlayers = 0;
        while(numberOfPlayers < 1 || numberOfPlayers > 4) { // Can only be 1-4 players.
            gameHelper.menuClearScreen(); // Clear text
            System.out.println("You have chosen " + numberOfTurns + " rounds for the game!\n");
            System.out.println("Now enter how many players between 1-4! ");
            System.out.print("Number of players: ");
            try { // Handle exceptions.
                numberOfPlayers = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }

        }
        gameHelper.menuClearScreen(); // Lets the users enter their player names.
        System.out.println("\nYou have chosen " + numberOfPlayers + " players for the game!");
        for(int i = 1; i < numberOfPlayers + 1; i++){
            System.out.print("Player " + i + " -> choose your name: ");
            String name = myScanner.next();
            players.add(new Player(name));
        }
        // Putting out some game info from what the user has entered before.
        gameHelper.menuClearScreen();
        System.out.println("""
                                ------------------------
                                |      GAME INFO       |
                                ------------------------""");
        System.out.println("# Number of rounds = " + numberOfTurns);
        int playerNumber = 1;
        for(Player player: players){
            System.out.println("# Player " + playerNumber + ": " + player.getName());
            playerNumber++;
        }
        System.out.println("#");
        System.out.println("# " + players.get(0).getName() + " will start the first round!");
        System.out.println("# May the best player win!");
        gameHelper.menuHelper();

        for (int i = 1; i < numberOfTurns + 1; i++) {
            for (Player player : players) {
                playerMenu(player, i);
                if(checkIfFalse()){
                    break;
                }
            }
            if(checkIfFalse()){
                break;
            }
        }
    }
    public void playerMenu(Player player, int turn){
        Store store = new Store();
        Scanner input = new Scanner(System.in);
        boolean realGameMenu = true;

        while(realGameMenu) {

            System.out.println("\n".repeat(20));
            System.out.println("\nIt's now round: " + turn);
            System.out.println(player.getName() + "'s turn!\n");
            System.out.println("""
                                --------------------------------
                                |          THE GAME            |
                                --------------------------------
                                #   |1| - Animal store.        #
                                #   |2| - Food store.          #
                                #   |3| - Breed.               # 
                                #   |4| - Feed animal.         #
                                #   |5| - Sell animals.        #   
                                #   |6| - Done, next player.   #
                                #                              #
                                #   |0| - Exit to main menu.   #""");
            int choice = -1;
            while (choice > 6 || choice < 0) {
                try {
                    System.out.println("Enter an option: ");
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("You must enter a number in the menu.");
                }
            }

            switch (choice) {
                case 1 -> store.buyAnimals(player);
                case 2 -> {}
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                case 6 -> realGameMenu = false;
                case 0 -> {
                    realGameMenu = false;
                    setForEach(false);
                }
            }
        }
    }
    //Method for checking the boolean that breaks the for-loop if user needs to exit for main menu.
    public boolean checkIfFalse(){
        return !this.forEach;
    }

    public void setForEach(boolean forEach) {
        this.forEach = forEach;
    }
}
