// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.util.Random;
import java.util.Scanner;

public class Game {

    private int trackTurns;
    private static String debugDecision;

    public Game() {     }


    public void playGame(Board b) {
        boolean sunk = false;
        trackTurns += 1;    // Tracks how many guesses' player has done
        while (!sunk) {     // checks to see if boolean 'sunk' evaluates to true
            switch (debugDecision) {    // Based on yes or no to debug input, prints associated board
                case "y":
                    b.display();    //calls Board function display() on b ('b' aka 'playerBoard' passed in main)
                    break;
                case "n":
                    b.print();      //calls Board function print() on b ('b' aka 'playerBoard' passed in main)
                    break;
                default:
                    System.out.println("Error in debugDecision!");  // should never occur since validity of debugDecision verified in main
                    System.exit(0);
            }
            Scanner xyInput = new Scanner(System.in);
            System.out.println("Enter an x and y coordinate: ");       // Asks for xy coordinate input from player
            int xChoice = xyInput.nextInt();    // x (xChoice) and y (yChoice) coordinates assigned per input
            int yChoice = xyInput.nextInt();
            int report = b.fire(xChoice, yChoice);   // Calls Board fire function (inputs xy coordinates by player as parameters) on the board, 'b', which returns an integer with correlating to the status of the hit and is assigned to varibale report
            if (this.checkWin() == true){
                report = 3;
            }
            switch (report) {
                case 0: // penalty
                    System.out.println("Wrong!");
                    trackTurns += 1;
                    System.out.println("Turn " + trackTurns + " skipped :/");
                    break;
                case 1: // miss
                    System.out.println("Miss!");

                    break;
                case 2: // hit
                    System.out.println("Hit!");
                    break;
                case 3: // sunk
                    System.out.println("Sunk!");
                    System.out.println("Congrats! You defeated the brigade in " + trackTurns + " turns!");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }


    public boolean checkWin() {     //      if win return true, lose false

    }


    public static void main(String[] args) {
        int modeNum = 0;
        while (true) {
            Scanner debug = new Scanner(System.in);    //Debug choice
            System.out.println("Do you want to run in debug mode? y/n ");
            debugDecision = debug.nextLine();
            Scanner gameChoice = new Scanner(System.in);    //Our scanner object
            System.out.println("Select your difficulty: Beginner, Intermediate, Expert ");
            String mode = gameChoice.nextLine();
            if ( ! ((debugDecision.equalsIgnoreCase("n")) || (debugDecision.equalsIgnoreCase("y") || (mode.equalsIgnoreCase("Beginner") || (mode.equalsIgnoreCase("Intermediate") || (mode.equalsIgnoreCase("Expert"))))))) {
                System.out.println("Invalid Input - Try Again!");   // Makes sure both inputs are valid, loops until correct
            }
            else {
                switch (mode) {     // Exits while loop and assigns input mode to usable parameter value
                    case "Beginner":
                        modeNum = 3; // assigns variable to use as parameter to build new board
                        break;
                    case "Intermediate":
                        modeNum = 6;
                        break;
                    case "Expert":
                        modeNum = 9;
                        break;
                    default:
                        break;
                }
                break;
            }
        }
        Board playerBoard = new Board(modeNum);     // initiates Board object with modeNum assigned above
        Game myGame = new Game();    // initiates Game object
        myGame.playGame(playerBoard);   // calls helper function playGame on myGame to play game on using playerBoard
    }
}
