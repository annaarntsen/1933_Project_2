
import java.util.Scanner;

public class Board {

    private int numBoats;
    private Battleboats[] boats;
    private Cells[][] board;
    private int[] boatSizes;


    public static void main(String[] args) {
//main
        Scanner choice = new Scanner(System.in); //Our scanner object
        System.out.println("Select your difficulty: Beginner, Intermediate, Expert");
        String input = choice.nextLine();
    }

    public void placeBoats() {
        int rows = 0;
        int coulmns = 0;

    }

    //Places boats randomly on the board
    public int fire(int rows, int columns) {

    }

    //Handles attacking a coordinate
    public void display() {
//Prints out the player board state every turn
        System.out.println();
    }

    public void print() {
        //Prints out the fully revealed board if a player types in the print command (This
        //would be used for debugging purposes)
        for(int i = 0;i < board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getStatus());
            }
            System.out.println();
        }
    }

    public Board(int mode) {
//Board class constructor
        board = new Cells[mode][mode];
        for(int i = 0;i < mode;i++) {
            for(int j = 0; j < mode; j++){
                board[i][j] = new Cells(i, j, '-');
            }
        }
    }
}