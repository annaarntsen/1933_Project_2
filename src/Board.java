// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int boardLength;        // Square board so length and width same, used to make sure the length of the Board is not exceeded
    private int numBoats;
    private Battleboats[] boats;
    private Cells[][] board;
    private int[] boatSizes;
    public int boatsSunk = 0;   // Tracks boats that have been sunk


    public void placeBoats() {
        //Place boats randomly on the board
        int boatIndex = -1;     // Initialized to -1, so it will start at zero in while loop; tracks index for which each new boat should be placed in boats array
        for(int ship: boatSizes){       // Iterate through boat sizes/each boat
            boatIndex += 1;     // Will be placing at index 0 in boats initially, +1 for each after
            // Following variables store the assigned values to be able to access once assigned and validated by loop below
            int randX;
            int randY;
            boolean randOrientation;

            do {        // Assigns random x coordinate, y coordinate, and orientation
                Random r = new Random();
                randX = r.nextInt(0,boardLength-1);     // Assign coordinate between 0 and length of board - 1
                randY = r.nextInt(0,boardLength-1);
                randOrientation = r.nextBoolean();      // Boolean false is horizontal, true vertical
            }
            while(!(inBounds(randX, randY, randOrientation, ship)) || overLap(randX, randY, randOrientation, ship)); // Checks to make sure boat will be in bounds based and does not overlap another boat; loops until valid

            Battleboats newBoat = new Battleboats(ship,randOrientation);
            Cells[] boatCells = new Cells[ship];    // Cell array that has the locations of each cell of the current boat

            if(!randOrientation){    //If our orientation is horizontal, we want to place
                for (int x = randX,i = 0; x < randX + ship; x++, i++){      // Changes Cell status for each xy coordinate of Board and assigns each of the cells to boatCells (which is used to setSpaces() for the boat)
                board[x][randY].setStatus('B');
                boatCells[i] = board[x][randY];
                }
            }
            else{       // Vertical orientation
                for(int y = randY, i = 0; y < randY + ship; y++, i++){     // Changes Cell status for each xy coordinate of Board and assigns each of the cells to boatCells (which is used to setSpaces() for the boat)
                    board[randX][y].setStatus('B');
                    boatCells[i] = board[randX][y];
                }
            }
            newBoat.setSpaces(boatCells);       // Associates cells from boatCells with the Battleboat object newBoat
            boats[boatIndex] = newBoat;         // Adds Battleboat object to boat array
            }
        }

    public boolean inBounds(int x, int y, boolean orient, int sizeShip){    // This is a helper method that we use in placeBoats to check if the random placement is in bounds
        int shipSize = sizeShip -1;
        if(x > boardLength-1 || y > boardLength-1 || x <= 0 ||y <= 0) {       // Is out of bounds or not
            return false;
        }
        if(!orient){    // Horizontal, add size of ship and coordinate
            shipSize += x;
        }
        else{       // Vertical, add size of ship and coordinate
            shipSize += y;
        }
        return shipSize < boardLength;  // Boolean if coordinate out of bounds or not
    }

    public boolean overLap(int x, int y, boolean orient, int sizeShip) {  //This helper function is designed to check whether a boat's placement overlaps with the position of another.
        if(!orient) { //if horizontal
            for (int i = x; i < x + sizeShip; i++) {        // Iterate over the ship size from x and check if the status is 'B', signifying the presence of a boat
                if (board[i][y].getStatus() == 'B') {
                    return true;
                }
            }
            return false;
        } else { //if vertical
            for (int i = y; i < y + sizeShip; i++) {        // Iterate over the ship size from x and check if the status is 'B', signifying the presence of a boat
                if (board[x][i].getStatus() == 'B') {
                    return true;
                }
            }
                return false;
        }
    }


    public int fire(int x, int y) {     // The coordinated passed in will be checked to determine status, updated, and sent to Game to create correct interaction with player
        if ((x<0 || x>boardLength-1) || (y<0 || y>boardLength-1)) {     // Out of bounds catch, returns 0 = penalty in Game class
            return 0;
        }
        if ((board[x][y].getStatus() == 'H' || board[x][y].getStatus() == 'M')) {       // Coordinate already guessed catch, returns 0 = penalty in Game class
            return 0;
        }
        switch (board[x][y].getStatus()) {      // Return value is assigned to an int based on the status of the Cell, which is fed to Game to determine what to print to user
            case '-': // No boat, not guessed
                board[x][y].setStatus('M');        // Example: if the coordinate passed is in bounds, hasn't already been guessed, and there is no boat present, status is set to  'M', 1 is returned, and Game class will print "Miss" and continue to play
                return 1;
            case 'B':   // Hit, boat
                board[x][y].setStatus('H');         // Update status to hit
                boolean shipSunk2 = this.checkSunk(x,y);        // Calls helper function to check if whole boat was sunk
                    if (shipSunk2) {return 3;}      // Returns value associated with "sunk" in Game
                return 2;       // Returns value associated with "hit" in Game
            default:
                return 0;
        }
    }

    public boolean checkSunk(int x, int y) {        // Helper function checks if the coordinate that was hit in fire() sunk the whole ship, also updates boatsSunk variable
        int boatTally = 0;  // Counts number of boats sunk
        for (Battleboats eachBoat: boats) {
            int sunkVal = 0;       // Counts Cells of boat hit
            for (Cells thisCell: eachBoat.getSpaces()) {
                if (thisCell.getStatus() == 'H') {      // Iterate through each Cell of each Boat and check if status is hit
                    sunkVal += 1;
                }
            }
            if (sunkVal == eachBoat.getSize()) {    // If number of cells hit = size of boat, boat is sunk
                boatTally += 1;
                }
            }
        if (boatTally > boatsSunk) {       // Updates global boatsSunk counter
            boatsSunk = boatTally;
            return true;
        }
        return false;
    }

    public boolean checkWin(){      // Helper function to check if number of boats sunk = total boats on Board, if yes Game over
        if (boatsSunk == numBoats){
            return true;
        }
        return false;
    }


    public void display() {        // Displays board with location of boats and hit/misses (only shown in debug mode)
        int columnLabel = 0;
        System.out.print("    ");
        while (columnLabel<boardLength) {       // Labels printed board columns
            System.out.print(columnLabel+"   ");
            columnLabel++;
        }
        System.out.print("\n");
        for (int i = 0; i < boardLength; i++) {     // Iterates through all Cells and prints status in grid format including where the boats are (with length)
            System.out.print(i+"   ");      // Labels printed board rows
            for (int j = 0; j < boardLength; j++) {
                if (board[j][i].getStatus() == 'B') {
                    for (Battleboats eachBoat : boats) {
                        if (eachBoat.locateCoordinates(j,i)) {
                            System.out.print(board[j][i].getStatus() + "" + eachBoat.getSize() + "  ");
                        }
                    }
                }
                else {
                    System.out.print(board[j][i].getStatus()+"   ");
                }
            }
            System.out.println("\n");
        }
        System.out.println("Boat Coordinates with Status:");        // Print coordinates with status for each boat on Board
        for (int numberPrintBoat = 0; numberPrintBoat < numBoats; numberPrintBoat++) {
            System.out.println("Boat #" + numberPrintBoat + ": ");
            for (Cells boatCell : boats[numberPrintBoat].getSpaces()) {
                System.out.println(" (" + boatCell.getRow() + ", " + boatCell.getCol() + ") - " + boatCell.getStatus());
            }
        }
    }

    public void print() {          //Prints out board, including column and row labels, for user playing while NOT in debug mode (shows guesses but not where boats are)
        int columnLabel = 0;
        System.out.print("    ");
        while (columnLabel<boardLength) {       // Labels printed board columns
            System.out.print(columnLabel+"   ");
            columnLabel++;
        }
        System.out.print("\n");
        for (int i = 0; i < boardLength; i++) {     // Iterates through all Cells and prints status in grid format (but not where boats are)
            System.out.print(i+"   ");         // Labels printed board rows
            for (int j = 0; j < boardLength; j++) {
                if (board[j][i].getStatus() == 'B') {
                    System.out.print("-"+"   ");
                }
                else {
                    System.out.print(board[j][i].getStatus()+"   ");
                }
            }
            System.out.println("\n");
        }
    }

    public Board( int mode){
        //Board class constructor
        boardLength = mode;     // Assigns global variable based on input
        board = new Cells[mode][mode];      // Creates Cells array with mode^2 spaces
        for (int i = 0; i < mode; i++) {
            for (int j = 0; j < mode; j++) {
                board[i][j] = new Cells(i, j, '-');     // Creates new Cell object for each coordinate on the Board, initializing status to '-'
            }
        }
        // Assigns number of boats and their sizes according to Board size
       if(mode == 3) {
           boatSizes = new int[]{2};
           numBoats = 1;
           boats = new Battleboats[numBoats];
           }
       else if(mode == 6){
           boatSizes = new int[]{2, 3, 4};
           numBoats = 3;
           boats = new Battleboats[numBoats];
       }
           else{
           boatSizes = new int[]{2, 3, 3, 4, 5};
           numBoats = 5;
           boats = new Battleboats[numBoats];
       }
       this.placeBoats();
    }
}
