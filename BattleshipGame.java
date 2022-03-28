import java.util.Scanner;
public class BattleshipGame {
    public static void main(String[] args) {
        boolean play = true;
        while (play) {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();


            Scanner input = new Scanner(System.in);
            
            while (!(ocean.isGameOver())) {
                
                String s  = input.nextLine();
                String[] cord = s.split(" ");
                int x = Integer.parseInt(cord[0]);
                int y = Integer.parseInt(cord[1]);
                if (ocean.inBound(x,y)) {
                    ocean.shootAt(x,y);
                    if (ocean.getShip(x,y).isSunk()) {
                        System.out.println("You just sank a " + ocean.getShip(x,y).getShipType());
                    }
                    ocean.print();
                }
                else {
                    System.out.println("make sure your coordinate is in bounds");
                    ocean.print();
                }

            }
            String s = input.nextLine();
            System.out.println("Would you like to play again? (y/n)");
            if (s.equals("y")) {
                play = true;
            }
            else {
                play = false;
            }
        }
        
        

    }
}