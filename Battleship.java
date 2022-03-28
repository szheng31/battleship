/***
* Stanley Zheng, Aileen Ruan, Subaah Syed
*
* Subclass of Ship, describes a ship of length 4
***/
public class Battleship extends Ship{
  public Battleship(){
    length = 4;
  }
  @Override
  public String getShipType(){
    return "battleship";
  }
}
