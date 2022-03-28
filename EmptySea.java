/***
* Stanley Zheng, Aileen Ruan, Subaah Syed
*
* Subclass of Ship, describes a part of the ocean that doesn't have a ship in it
***/
public class EmptySea extends Ship{
  public EmptySea(){
    length = 1;
  }

  // indicates nothing was hit
  @Override
  public boolean shootAt(int row, int column){
    return false;
  }

  // indicate nothing was sunk
  @Override
  public boolean isSunk(){
    return false;
  }

  @Override
  public String toString(){
    return "-";
  }

  @Override
  public String getShipType(){
      return "emptysea";
    }
}
