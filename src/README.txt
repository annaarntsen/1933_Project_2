README File - Project 2

•  Group member’s names and x500
	- Anna Arntsen (arnts071) and Ariel Larin (larin006)
		
•  Contributions of each partner (if working with a partner) 
	- Created project outline and wrote / tested accordingly - collaborated for all parts. 
	- Ariel Contributions: Battleboats.java, Cells.java, placeBoats in Board.java, inBounds in Board.java, overLap in Board.java
	- Anna Contributions:
		- Wrote README and project outline
		Battleboats:
		- locateCoordinates() and Battleboats(length, orient)
		Board:
		- boardLength and boatsSunk variables
		- Updates in placeBoats(), inBounds(), overLap(), Board()
		- Wrote fire(), checkSunk(), checkWin(), display(), print()
		Game:
		- Wrote Game class

•  How to compile and run your program 
	1. Open terminal and enter "javac Game.java" to compile program
	2. Follow with "java Game.java" to run the program
	3. Follow prompts to begin battleship game
        	a. Either "y" or "n" to chose whether to run in debug mode or not
		b. Enter "Beginner", "Intermediate", or "Expert" to initialize preferred game
	4. Game data will be shown, follow prompts unit all ships have been hit and the game exits
		a. Note: xy coordinates need to be in format "x y", cannot be "xy"
				
•  Any assumptions
	- Entered game coordinates will be within the correct range and not repeated - a penalty will be enforced if so
		
•  Additional features that you implemented (if applicable)
	- imported Scanner, Math, and Random 
	Board:
	- private int boardLength is initialized according to type of Game
	- public int boatsSunk variable tracks total of boats sunk as game is played (public because accessed for printing total to user in Game class)
	- public boolean inBounds(int x, int y, boolean orient, int sizeShip) checks randomly generated xy coordinates to ensure the boat being created in placeBoats() will be within the bounds of the Board
	- public boolean overLap(int x, int y, boolean orient, int sizeShip) checks the assigned xy coordinates do not overlap with boats already placed on the Board
	- public boolean checkSunk(int x, int y) is called in fire() when a boat coordinate is hit to determine if the whole boat was sunk
	- public boolean checkWin() determines if the boatsSunk = numBoats, called in Game class as while parameter in playGame() to exit loop when all boats have been sunk 
	Battleboats:
	-  public boolean locateCoordinates(int x, int y) iterates through the cells of a boat to determine if the provided xy coordinates are in that boat
	- public Battleboats(int length, boolean orient) is an alternative constructor including determined orientation
	Cells:
	- Cell() class is the same but named Cells()		
		
•  Any known bugs or defects in the program 
	- Entering anything other than numbers when prompted for xy coordinates will kill the program
•  Any outside  sources  (aside  from  course  resources)  consulted  for  ideas  used  in  the  project,  in  the 
format: 
	- While(true) invalid input catch case in Game class main: https://stackoverflow.com/questions/18721884/re-prompt-user-after-invalid-input-in-java
 
“I certify that the information contained in this README file is complete 
and accurate. I have both read and followed the course policies in the ‘Academic Integrity - Course 
Policy’ section of the course syllabus.” and type your name(s) underneath 

Anna Arntsen, Ariel Larin

