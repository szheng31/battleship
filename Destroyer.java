public class Destroyer extends Ship{
  //Describes a ship of length 2.
  public Destroyer(){
    length = 2;
  }
  @Override
  public String getShipType(){
    return "destroyer";
  }
}
