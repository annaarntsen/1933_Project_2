// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int boardLength;        // square board so length and width same
    private int numBoats;
    private Battleboats[] boats;
    private Cells[][] board;
    private int[] boatSizes;
    public int boatsSunk;


    public void placeBoats() {      //Place boats randomly on the board
        int boatIndex = -1;
        for(int ship: boatSizes){
            boatIndex += 1;
            int randX;                     //We use a while loop so as long as we are out of bounds, a random x and y position and orientation will be assigned.
            int randY;
            boolean randOrientation;

            do {
                Random r = new Random();
                randX = r.nextInt(0,boardLength-1);                     //We use a while loop so as long as we are out of bounds, a random x and y position and orientation will be assigned.
                randY = r.nextInt(0,boardLength-1);
                randOrientation = r.nextBoolean();
            }
            while(!(inBounds(randX, randY, randOrientation, ship)));
            Battleboats newBoat = new Battleboats(ship,randOrientation);

            Cells[] boatCells = new Cells[ship];
            if(!randOrientation){    //If our orientation is horizontal, we want to place
                for (int x = randX,i = 0; x < randX + ship; x++, i++){
                board[x][randY].setStatus('B');
                boatCells[i] = board[x][randY];
            }
                //iterate through boat sizes/each boat. Then, for each size/boat, randomly determine and x and y position for each boat and an orientation.
                //Check orientation and x,y placement is valid as in within the cell array/board.
                //Set up a while loop that checks until it finds a random valid place to put a boat
                //Once place is valid, we actually place the boat. To accomplish that, we must alter the status of each cell in the board array
                // we need to create a cell array that has the locations of each cell of the current boat.
                //We pass that into boats
            }
            else{
                for(int y = randY, i = 0; y < randY + ship; y++, i++){      // vertical orientation
                    board[randX][y].setStatus('B');
                    boatCells[i] = board[randX][y];
                }
            }
            newBoat.SetSpaces(boatCells);       // associates cells from boatCells with the Battleboat object newBoat
            boats[boatIndex] = newBoat;         // adds Battleboat object to boat array
            }
        }

    public boolean inBounds(int x, int y, boolean orient, int sizeShip){    //This is a helper method that we use in placeBoats to check if the random placement
        int shipSize = sizeShip;
        if(x >= boardLength-1 || y >= boardLength-1 || x < 0 ||y < 0){       //is out of bounds or not.
            return false;
        }
        if(orient == true){
            shipSize += x;
        }
        else{
            shipSize += y;
        }
        if(shipSize >= boardLength-1){
            return false;
        }
        else{ return true; }
    }

    public int fire(int x, int y) {     // xy coordinates called from Game class
        if ((x<0 || x>boardLength-1) || (y<0 || y>boardLength-1)) {     // seperate so doesn't throw out of bounds exception
            return 0;
        }
        if ((board[x][y].getStatus() == 'H' || board[x][y].getStatus() == 'M')) {
            return 0;      // "penalty" assigned to 0 in Game
        }
        // if coordinate already "M" miss or "H" hit or out of bounds assign penalty is applied  (basically check if valid coordinates and/or already fired on --> if yes, then automatically return 0)
        switch (board[x][y].getStatus()) {      // work in progress: return value is assigned to an int based on the status of the cell that is fed to Game to determine what to print to user
            case '-': // No boat, not guessed        - once the function is created, the coordinated passed in will be checked to determine status, updated, and sent to Game to create correct interaction with player
                board[x][y].setStatus('M');        // - ex: if the coordinate passed is in bounds, hasn't already been guessed, and there is no boat present, 1 is returned and Game will print "Miss" and continue to play
                boolean shipSunk = this.checkSunk(x,y);
                    if (shipSunk) {return 3;}
                return 1;
            case 'B': // hit, boat
                board[x][y].setStatus('H');         // update status to hit
                boolean shipSunk2 = this.checkSunk(x,y);        // calls helper function to check if boat was sunk
                    if (shipSunk2) {return 3;}      // returns value associated with "hit" in Game
                return 2;
            default:
                return 0;
        }
    }

    public boolean checkSunk(int x, int y) {
        int sunkVal = 0;
        for (Battleboats eachBoat: boats) {
            for (Cells thisCell: eachBoat.getSpaces()) {
                if (thisCell.getStatus() == 'H') {
                    sunkVal += 1;
                }
            }
            if (sunkVal >= eachBoat.getSize()) {
                boatsSunk += 1;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(){
        if (boatsSunk == numBoats){
            return true;
        }
        return false;
    }


    public void display () {
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[j][i].getStatus() == 'B') {
                    for (Battleboats eachBoat : boats) {
                        if (eachBoat.locateCoordinates(j,i));
                        System.out.print(board[j][i].getStatus()+""+eachBoat.getSize()+ " ");
                    }
                }
                else {
                    System.out.print(board[j][i].getStatus()+"   ");
                }
            }
            System.out.println("\n");
        }
    }

    public void print () {
        //Prints out the fully revealed board if a player types in the print command (This
        //would be used for debugging purposes)
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[j][i].getStatus() == 'B') {
                    System.out.print("-"+"  ");
                }
                else {
                    System.out.print(board[j][i].getStatus()+"  ");
                }
            }
            System.out.println("\n");
        }
    }

    public Board( int mode){
        //Board class constructor
        boardLength = mode;
        board = new Cells[mode][mode];
        for (int i = 0; i < mode; i++) {
            for (int j = 0; j < mode; j++) {
                board[i][j] = new Cells(i, j, '-');
            }
        }
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
//       board.placeBoats();
    }
}
