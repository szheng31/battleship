import java.util.Random;

public class Ocean{
  private Ship[][] ships = new Ship[10][10] ;
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
    //battleship
    Ship battleship = new Battleship();
    boolean placeable = false;
    while (!placeable) {
        int i = (int) (Math.random() * 10) + 1;
        int j = (int) (Math.random() * 10) + 1;
        boolean horizontal = (Math.random() > 0.5);
        if (okToPlaceAt(i,j,horizontal,this)) {
            placeable = true;
            battleship.placeAt(i,j,horizontal,this);
        }
    }
    
    // two cruisers
    for (int i = 0; i < 2; i++) {
        Ship cruiser = new Cruiser();
        placeable = false;
        while (!placeable) {
            int i = (int) (Math.random() * 10) + 1;
            int j = (int) (Math.random() * 10) + 1;
            boolean horizontal = (Math.random() > 0.5);
            if (okToPlaceAt(i,j,horizontal,this)) {
                placeable = true;
                cruiser.placeAt(i,j,horizontal,this);
            }
        }
    }
    // three destroyers
    for (int i = 0; i < 3; i++) {
        Ship destroyer = new Destroyer();
        placeable = false;
        while (!placeable) {
            int i = (int) (Math.random() * 10) + 1;
            int j = (int) (Math.random() * 10) + 1;
            boolean horizontal = (Math.random() > 0.5);
            if (okToPlaceAt(i,j,horizontal,this)) {
                placeable = true;
                destroyer.placeAt(i,j,horizontal,this);
            }
        }
    }
    
    // four submarines
    for (int i = 0; i < 4; i++) {
        Ship submarine = new Submarine();
        placeable = false;
        while (!placeable) {
            int i = (int) (Math.random() * 10) + 1;
            int j = (int) (Math.random() * 10) + 1;
            boolean horizontal = (Math.random() > 0.5);
            if (okToPlaceAt(i,j,horizontal,this)) {
                placeable = true;
                submarine.placeAt(i,j,horizontal,this);
            }
        }
    }
            
  }

  public boolean isOccupied(int row, int column){
    if (ships[row][column] instanceof EmptySea) return false;
    return true;
    }

  public boolean shootAt(int row, int column){
    shotsFired++;
    if (isOccupied(row,column)==true && !(ships[row][column].toString() == "x"))
    {
      hitCount++;
    }
  }

  public int getShotsFired(){
    return shotsFired;
  }

  public int getHitCount(){
    return hitCount;
  }

  public boolean isGameOver(){
    int count = 0;
    for (int i=0; i<10; i++){
      for (int j=0; j<10;j++){
        if (ships[i][j].isSunk()) count++;
      }
    }
    if (count==10) return true;
  }

  public Ship[][] getShipArray(){
    return ships;
  }

  public void print(){
    String[][] ocean = new String[11][11];
    for (int i= 0;i < 10;i++){
      ocean[0][i] = Integer.toString(i);
      ocean[i][0] = Integer.toString(i);
    }
    for (int i = 1; i < 11; i++){
      for (int j = 1; j < 11; j++){
      if (shootAt(ships[i][j])==true  && isOccupied(ships[i][j]==true)) ocean[i][j]="S";
      if (shootAt(ships[i][j])==true  && isOccupied(ships[i][j]==false)) ocean[i][j]="-";
      if (ships[i][j].isSunk()==true) ocean[i][j]="x";
      if (ships[i][j].isSunk()==false) ocean[i][j]=".";
    }
  }
  System.out.print(ocean[i][j]);
  }

}
