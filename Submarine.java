public class Submarine extends Ship{
  //Describes a ship of length 1.
  public Submarine(){
    length = 1;
  }
  @Override
  public String getShipType(){
    return "submarine";
  }
}
