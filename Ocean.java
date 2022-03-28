import java.util.Random;

public class Ocean{
  private Ship[][] ships = new Ship[10][10] ;
  private boolean[][] shots = new boolean[10][10];
  private int shotsFired, hitCount;

  public Ocean(){
    shotsFired = 0;
    hitCount = 0;
    for (int i=0; i<10;i++){
      for (int j=0; j<10;j++){
        ships[i][j] = new EmptySea();
      }
    }
  }

  public void placeAllShipsRandomly(){
    /*Places all ten ships randomly on the (initially empty) ocean, making certain that all ships are placed legally (within bounds). 
      Places the larger ships first, starting from Battleship (length=4) to Submarine (length=1).
    */
	  
    //battleship
    Ship battleship = new Battleship();
    boolean placeable = false;
    while (!placeable) {
        int i = (int) (Math.random() * 10);
        int j = (int) (Math.random() * 10);
        boolean horizontal = (Math.random() > 0.5);
        
        
        
        if (battleship.okToPlaceShipAt(i,j,horizontal,this)) {
            
            placeable = true;
            battleship.placeShipAt(i,j,horizontal,this);
        }
    }

    // two cruisers
    for (int i = 0; i < 2; i++) {
        Ship cruiser = new Cruiser();
        placeable = false;
        while (!placeable) {
            int k = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() > 0.5);
            if (cruiser.okToPlaceShipAt(k,j,horizontal,this)) {
                placeable = true;
                cruiser.placeShipAt(k,j,horizontal,this);
            }
        }
    }
    // three destroyers
    for (int i = 0; i < 3; i++) {
        Ship destroyer = new Destroyer();
        placeable = false;
        while (!placeable) {
            int k = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() > 0.5);
            if (destroyer.okToPlaceShipAt(k,j,horizontal,this)) {
                placeable = true;
                destroyer.placeShipAt(k,j,horizontal,this);
            }
        }
    }

    // four submarines
    for (int i = 0; i < 4; i++) {
        Ship submarine = new Submarine();
        placeable = false;
        while (!placeable) {
            int k = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() > 0.5);
            if (submarine.okToPlaceShipAt(k,j,horizontal,this)) {
                placeable = true;
                submarine.placeShipAt(k,j,horizontal,this);
            }
        }
    }
    

  }

  public boolean isOccupied(int row, int column){
    //Returns true if the given location contains a ship, false if it does not.	  
    if (ships[row][column] instanceof EmptySea) return false;
    return true;
  }
  public boolean inBound(int row, int col) {
	//Checks to see if the coordinates are in bound, that is, if they are in the range of 0 to 9 inclusive.
	if (row < 0 || row > 9) return false;
    	if (col < 0 || col > 9) return false;
    	return true;
    }
  public boolean shootAt(int row, int column){
    /*Returns true if the given location contains a "real" ship, still afloat, (not an EmptySea), false if it does not. 
      In addition, this method updates the number of shots that have been fired, and the number of hits.
      */
    shots[row][column] = true;
    shotsFired++;
    if (ships[row][column].shootAt(row, column))
    {
      hitCount++;
      return true;
    }
    return false;
  }

  public int getShotsFired(){ //returns the total number of shots fired
    return shotsFired;
  }

  public int getHitCount(){ //returns the number of hits recorded (allows the user to get a worse score by shooting again at the same location)
    return hitCount;
  }

  public Ship[][] getShipArray(){ //returns the actual 10x10 array of ships
    return ships;
  }

  public void print(){
      /*Prints the ocean. To aid the user, row numbers are displayed along the left edge of the array, and column numbers are
        displayed along the top from 0 to 9. Prints 'S' to indicate a location that was fired upon and hit a ship, '-' to indicate a location 
	fired upon and found nothing there, 'x' to indicate a location containing a sunken ship, and '.' to indicate a location that was never fired upon.
      */
      System.out.println("  0 1 2 3 4 5 6 7 8 9");
      for (int i = 0; i < 10; i++) {
          System.out.print(i+ " ");
          for (int j = 0; j<10;j++) {
              if (shots[i][j]) {
                  
                System.out.print(ships[i][j] + " " );
            }
            else {
                
                System.out.print(". ");
            }
        }
        System.out.println();
    }
  }

  public boolean isGameOver() {
    //If the player has sunk all ships, the game returns a message declaring they've won along with the total shots fired in the game (the final score).
    for (int i = 0; i< 10; i++ ) {
      for(int j = 0; j < 10; j++ ) {
        if ((!(ships[i][j].isSunk())) && this.isOccupied(i,j)) {
          return false;
        } 
      }
    }
    System.out.println("You have won. Final Score:" + shotsFired);
    return true;
  }
  
  public Ship getShip(int row, int col) { //returns the Ship object at the specified row and column
    return ships[row][col];
  }

}
