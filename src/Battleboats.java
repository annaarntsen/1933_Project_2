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
        return this.spaces;
        //Getter method for spaces attribute
    }

    public void SetSpaces(Cells[] cells){
        this.spaces = cells;
        //Setter method for spaces attribute
    }

    public int getSize(){
        return this.size;

        //Getter method for size attribute
    }

    public Battleboats(int length){
        this.size = length;

        //Battleboat class constructor
    }

}
