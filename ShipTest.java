public class ShipTest{
  public static void main(){
    Ship test = new Destroyer();
    Ocean o = new Ocean();
    System.out.println("length:" + test.getLength() + " " + test.getBowRow() + " " + test.getBowColumn());
    System.out.println("alignment:" + test.isHorizontal());
    test.setBowRow(4);
    System.out.println(test.getBowRow());
    System.out.println(test.okToPlaceShipAt(0, 0, true, o));

  }
}
