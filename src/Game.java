// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private int trackTurns;     // Tracks how many guesses / turns player has done
    private static String debugDecision;        // Global variable assigned based on user input, used to run display() or print() accordingly

    public Game() {     }   // Constructor

    public void playGame(Board b) {     // Handles playing Game, initialized with user input provided in Main
        trackTurns += 1;    // Tracks how many guesses' player has done
        while (!b.checkWin()) {     // checks to see if number of boats sunk is equal to number of boats total
            System.out.println("\nTurn #" + trackTurns);
            switch (debugDecision) {    // Based on yes or no to debug input prompted in Main, prints associated board
                case "y":
                    b.display();    //Calls Board function display() on b ('b' aka 'playerBoard' passed in main)
                    break;
                case "n":
                    b.print();      //Calls Board function print() on b ('b' aka 'playerBoard' passed in main)
                    break;
                default:
                    System.out.println("Error in debugDecision!");  // Should never occur since validity of debugDecision verified in Main while loop
                    System.exit(0);
            }
            Scanner xyInput = new Scanner(System.in);
            System.out.println("Enter an x and y coordinate: ");       // Asks for xy coordinate input from player
            int xChoice = xyInput.nextInt();    // x (xChoice) and y (yChoice) coordinates assigned per input
            int yChoice = xyInput.nextInt();
            int report = b.fire(xChoice, yChoice);   // Calls Board fire function (inputs xy coordinates by player as parameters) on the board, 'b', which returns an integer with correlating to the status of the hit
            switch (report) {       // Int returned from Board fire() function correlates to specific action
                case 0: // penalty
                    System.out.println("Penalty!");     // If xy out of bounds or already guessed, penalty assigned
                    trackTurns += 1;
                    System.out.println("Turn " + trackTurns + " skipped! :/ \n");
                    break;
                case 1: // miss
                    System.out.println("Miss!");
                    break;
                case 2: // hit
                    System.out.println("Hit!");
                    break;
                case 3: // sunk
                    System.out.println("Sunk!");
                    System.out.println("Congrats! You sunk "+b.boatsSunk+" boat(s) in " + trackTurns + " turns!");
                default:
                    break;
            }
            trackTurns += 1;       // Updates number of guesses player has made
        }
        b.display();        // Prints final board once Game is won (outside while loop)
        System.out.println("Game Over! All " + b.boatsSunk + " boat(s) were sunk in "+(trackTurns-1)+" turns!");
        System.exit(0);
    }

    public static void main(String[] args) {        // Asks user for input then creates and initiates playing (playGame()) corresponding Game
        int modeNum = 0;
        while (true) {      // Loops until input is valid
            Scanner debug = new Scanner(System.in);    // Debug choice
            System.out.println("Do you want to run in debug mode? y/n ");
            debugDecision = debug.nextLine();
            Scanner gameChoice = new Scanner(System.in);    // Asks user what difficulty they want to play
            System.out.println("Select your difficulty: Beginner, Intermediate, Expert ");
            String mode = gameChoice.nextLine();
            if ( ! (((debugDecision.equalsIgnoreCase("n")) || (debugDecision.equalsIgnoreCase("y"))) && ((mode.equalsIgnoreCase("Beginner")) || (mode.equalsIgnoreCase("Intermediate")) || (mode.equalsIgnoreCase("Expert"))))) {
                System.out.println("Invalid Input - Try Again!");   // Makes sure both inputs are valid, loops until correct
            }
            else {      // Exits while loop and assigns "mode" variable to value associated with the size of the Board
                switch (mode.toLowerCase()) {     // Ignores capitalization of input
                    case "beginner":
                        modeNum = 3; // assigns variable to use as parameter to build new Board
                        break;
                    case "intermediate":
                        modeNum = 6;
                        break;
                    case "expert":
                        modeNum = 9;
                        break;
                    default:
                        System.out.println("Invalid Input! Switch Break Error!");
                        break;
                }
                break;      // Exits while loop
            }
        }
        Board playerBoard = new Board(modeNum);     // Initiates Board object with modeNum assigned above
        Game myGame = new Game();    // Creates new Game
        myGame.playGame(playerBoard);   // Calls helper function playGame() on Game myGame to play using Board playerBoard
    }
}
