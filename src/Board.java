// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int numBoats;
    private Battleboats[] boats;
    private Cells[][] board;
    private int[] boatSizes;


    public void placeBoats() {      //Place boats randomly on the board
        Cells[] boatCells;
        for(int ship: boatSizes){
            int max = board.length;
            int randX;                     //We use a while loop so as long as we are out of bounds, a random x and y position and orientation will be assigned.
            int randY;
            boolean randOrientation;

            do {
                randX = (int) Math.random() * max ;                     //We use a while loop so as long as we are out of bounds, a random x and y position and orientation will be assigned.
                randY = (int) Math.random() * max;
                Random r = new Random();
                randOrientation = r.nextBoolean();
            }
            while(inBounds(randX, randY,randOrientation, ship));

            boatCells = new Cells[ship];
            if(!randOrientation){    //If our orientation is horizontal, we want to place
                for (int x = randX; x < x + ship; x++){
                board[randX][randY].setStatus('B');
                boatCells[x - randX] = board[randX][randY];
            }

                //iterate through boat sizes/each boat. Then, for each size/boat, randomly determine and x and y position for each boat and an orientation.
                //Check orientation and x,y placement is valid as in within the cell array/board.
                //Set up a while loop that checks until it finds a random valid place to put a boat
                //Once place is valid, we actually place the boat. To accomplish that, we must alter the status of each cell in the board array
                // we need to create a cell array that has the locations of each cell of the current boat.
                //We pass that into boats

            }
            else{
                for(int y = randY; y < y + ship; y++){
                    board[randX][randY].setStatus('B');
                    boatCells[y - randY] = board[randX][randY];
                }
            }
            boats[ship] = new Battleboats(boatSizes[ship]);
            }
        }

    public boolean inBounds(int x, int y, boolean orient, int shipSize){    //This is a helper method that we use in placeBoats to check if the random placement
        if(x >= board.length || y >= board.length || x < 0 ||y < 0){       //is out of bounds or not.
            return false;
        }
        if(orient == true){
            shipSize += x;
        }
        else{
            shipSize += y;
        }
        if(shipSize >= board.length){
            return false;
        }
    }

    public int fire(int x, int y) {     // xy coordinates called from Game class

        // if already "M" or "H" or out of bounds assign  (check if already fired on --> yes auto = 0)
        switch (tbd) {      // refer to Game for reference cases on returns
            case "P": // penalty
                return 0;
            case "M": // miss
                return 1;
            case "H": // hit
                return 2;
            case 3: // sunk
                return 3;
            default:
                return 0;
        }
    }

        //Handles attacking a coordinate
        public void display () {         //Prints out the player board state every turn

            System.out.println();
        }

        public void print () {
            //Prints out the fully revealed board if a player types in the print command (This
            //would be used for debugging purposes)
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j].getStatus());
                }
                System.out.println();
            }
        }

    public Board( int mode){
            //Board class constructor
            board = new Cells[mode][mode];
            for (int i = 0; i < mode; i++) {
                for (int j = 0; j < mode; j++) {
                    board[i][j] = new Cells(i, j, '-');
                }
            }
       if(mode == 3) {
           boatSizes = new int[]{2};
           }
       else if(mode == 6){
           boatSizes = new int[]{2, 3, 4};
       }
           else{
           boatSizes = new int[]{2, 3, 3, 4, 5};
       }
       }
        }
    }

}