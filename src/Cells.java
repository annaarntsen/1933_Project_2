// Written by Anna Arntsen (arnts071) and Ariel Larin (larin006)



public class Cells {
    private int row;     //Indicates the row value of the Cell
    private int col;     //Indicates the column value of the Cell
    private char status;
    /*
    Character indicating the status of the Cell. There are three different possibilities
    for this field: '-' Has not been guessed, no boat present | 'B' Has not been guessed, boat present | 'H' Has been guessed, boat present
    | 'M' Has been guessed, no boat present
     */

    public char getStatus() {
        //Getter method for status attribute
        return this.status;
    }
    public void setStatus(char c){
        //Setter method for status attribute
        this.status = c;
    }
    public int getRow(){
        //Getter method for row attribute
        return this.row;
    }
    public int getCol(){
        //Getter method for column attribute
        return this.col;
    }
    public Cells(int row, int col, char status){
        //Cell class constructor
        this.row = row;
        this.col = col;
        this.status = status;


    }
}
