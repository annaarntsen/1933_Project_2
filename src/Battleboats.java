// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.util.Random;
import java.util.Scanner;

public class Battleboats {
    private int size; //Indicates the length of the Battleboat
    private boolean orientation; //Indicates  the  orientation  (horizontal or vertical)  of  the  Battleboat.
    private Cells[] spaces; //A Cell array representing the cells that a Battleboat is in.

    public boolean getOrientation(){
        return this.orientation;
        //Getter method for orientation attribute
    }

    public Cells[] getSpaces(){
        return this.spaces;
        //Getter method for spaces attribute
    }

    public void setSpaces(Cells[] cells){
        this.spaces = cells;
        //Setter method for spaces attribute
    }

    public int getSize(){
        return this.size;
        //Getter method for size attribute
    }

    public Battleboats(int length){
        this.size = length;
        this.spaces = new Cells[size];
        Random r = new Random();
        this.orientation = r.nextBoolean();//true is horizontal, false is vertical orientation
        //Battleboat class constructor
    }

    public Battleboats(int length, boolean orient){
        // Alternative constructor including orientation as well as length
        this.size = length;
        this.spaces = new Cells[size];
        this.orientation = orient;
    }

    public boolean locateCoordinates(int x, int y) {
        // Checks to see if parameter xy values are a coordinate containing part of the given boat
        for (Cells i: this.spaces){
            if ((i.getRow() == x) && (i.getCol() == y)) {
                return true;
            }
        }
        return false;
    }
}
