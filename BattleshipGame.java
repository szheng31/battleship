/***
* Stanley Zheng, Aileen Ruan, Subaah Syed
* Compilation: javac BattleshipGame.java
* Execution: java BattleshipGame
*
* This is the "main" class, containing the main method and a variable of type Ocean. It uses a Scanner object to allow the player to input the coordinates
* of the point they want to shoot at. If the player successfully sinks a ship, the game returns what type of ship they sank, and if they enter coordinates
* that are not from 0-9 inclusive, then a message reminds them that their input should be within the bounds. If the game is over, the player has the option
* choosing to play again or not. 
* https://www.cis.upenn.edu/~matuszek/cit590-2016/Assignments/13-battleship.html
* 
* %java BattleshipGame
* You just sank a submarine
*   0 1 2 3 4 5 6 7 8 9
* 0 - - - - x x x - - -
* 1 - - - - - - - - x -
* 2 - x - x - - - . x .
* 3 . x - x - - - . . .
* 4 . x . x . - x x . -
* 5 . x . . . . . . . -
* 6 . . . - x . - - - -
* 7 - - - . . - - - x -
* 8 x . - - . - - - x .
* 9 . . - x . - x . . .
* You have won. Final Score:69
* Would you like to play again? (y/n)
* y
***/
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
            System.out.println("Would you like to play again? (y/n)");
            String s = input.nextLine();
            if (s.equals("y")) {
                play = true;
            }
            else {
                play = false;
            }
        }



    }
}
