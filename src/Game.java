// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.util.Random;
import java.util.Scanner;

public class Game {

    private int trackTurns;
    private static String debugDecision;

    public Game() {     }


    public void playGame(Board b) {
        boolean sunk = false;
        switch (debugDecision) {    // Based on yes or no to debug input, prints associated board
            case "y":
                b.display();
                break;
            case "n":
                b.print();
                break;
            default:
                System.out.println("Error!");
                System.exit(0);
        }
        trackTurns += 1;    // Tracks how many guesses' player has done
        while (!sunk) {
            Scanner xyInput = new Scanner(System.in);    // Asks for xy input
            System.out.println("Enter an x and y coordinate: ");
            int xChoice = xyInput.nextInt();
            int yChoice = xyInput.nextInt();
            int report = b.fire(xChoice, yChoice);   // Inputs xy coordinates by player as parameters for Board fire function
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
            if ( ! ((debugDecision.equalsIgnoreCase("x")) || (debugDecision.equalsIgnoreCase("y") || (mode.equalsIgnoreCase("Beginner") || (mode.equalsIgnoreCase("Intermediate") || (mode.equalsIgnoreCase("Expert"))))))) {
                System.out.println("Invalid Input - Try Again!");   // Makes sure both inputs are valid, loops until correct
            }
            else {
                switch (mode) {     // Exits while loop and assigns input mode to usable parameter value
                    case "Beginner":
                        modeNum = 3;
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
        Board playerBoard = new Board(modeNum);     // initiates Board object
        Game myGame = new Game();    // initiates Game object
        myGame.playGame(playerBoard);   // calls helper function to play game
    }
}
