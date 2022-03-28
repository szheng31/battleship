/***
* Stanley Zheng, Aileen Ruan, Subaah Syed
*
* Subcless of Ship, describes a ship of length 3
***/
public class Cruiser extends Ship{
  public Cruiser(){
    length = 3;
  }
  @Override
  public String getShipType(){
    return "cruiser";
  }
}
