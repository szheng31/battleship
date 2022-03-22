public class EmptySea extends Ship{
  public EmptySea(){
    length = 1;
  }

  @Override
  public boolean shootAt(int row, int column){
    return false;
  }

  @Override
  public boolean isSunk(){
    return false;
  }

  @Override
  public String getShipType(){
    return "-";
  }
}
