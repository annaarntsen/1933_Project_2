// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)

import java.util.Random;
import java.util.Scanner;

public class Battleboats {
    private int size; //Indicates the length of the Battleboat
    private boolean orientation; //Indicates  the  orientation  (horizontal or vertical)  of  the  Battleboat.
    //Remember, a boolean is either true or false.
    private Cells[] spaces; //A Cell array representing the cells that a Battleboat is in.



    public boolean getOrientation(){
        return this.orientation;
        //Getter method for orientation attribute
    }

    public Cells[] getSpaces(){
        return spaces;
        //Getter method for spaces attribute
    }

    public void SetSpaces(Cells[] cells){
        spaces = cells;
        //Setter method for spaces attribute
    }

    public int getSize(){
        return size;
        //Getter method for size attribute
    }

    public Battleboats(int length){
        size = length;
        spaces = new Cells[size];
        Random r = new Random();
        orientation = r.nextBoolean();//true is horizontal, false is vertical orientation

        //Battleboat class constructor
    }

    public boolean locateCoordinates(int x, int y) {
        for (Cells i: spaces){
            if ((i.getRow() == x) && (i.getCol() == y)) {
                return true;
            }
        }
        return false;
    }

    public Battleboats(int length, boolean orient){
        size = length;
        spaces = new Cells[size];
        orientation = orient;

    }

}
