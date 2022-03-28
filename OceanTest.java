

public class OceanTest {
	public static void main(String[] args) {
		Ocean board = new Ocean();
		board.placeAllShipsRandomly();
		for(int i = 0; i < 10; i++){
     		for(int j = 0; j < 10; j++){
         		board.shootAt(i,j);
				board.print();
				System.out.println(board.isGameOver());
     		}
     	}
     	
		
	}
}
