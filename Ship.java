public abstract class Ship {
	private int bowRow,bowColumn;
	protected int length;
	private boolean horizontal;
	private boolean[] hit;
		
	
	public abstract String getShipType();
	
	//getters
	public int getLength() {
		return length;
	}
	public int getBowRow() {
		return bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	//setters
	public void setBowRow(int row) {
		bowRow = row;
	}
	public void setBowColumn(int column) {
		bowColumn = column;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		int[] ship = new int[length];
		if (horizontal) {
			for (int i = 0; i < ship.length; i++) {
				ship[i] = column + i;
			}
		}
		else {
			for (int i = 0; i < ship.length; i++) {
				ship[i] = row + i;
			}
		}
		// if tiles are occupied
		if (Ocean.isOccupied(row,column)) return false;
	
		// if ship is horizontal
		if (horizontal && column + length-1 <= 9) {
			for (int i = 0; i < ship.length; i++) {
				//horizontally
				if (Ocean.isOccupied(row,column-1) || Ocean.isOccupied(row,column+length)) {
					return false;
					
				}
				
				// vertical
				if (Ocean.isOccupied(row-1,ship[i]) || Ocean.isOccupied(row+1,ship[i])) {
					return false;
				}
				
				// diagonal
				if (Ocean.isOccupied(row-1,column-1) || Ocean.isOccupied(row+1,column-1)) || Ocean.isOccupied(row-1,column+length) || Ocean.isOccupied(row+1,column+length) {
					return false;
				}
			}
			return true;
		}
		
		// if ship is vertical 
		if (!horizontal && row + length-1 <= 9) {
			for (int i = 0; i < ship.length; i++) {
				// vertically
				if (Ocean.isOccupied(row-1,column) || Ocean.isOccupied(row+length,column)) {
					return false;
				}
				
				//horizontal
				if (Ocean.isOccupied(ship[i],column-1) || Ocean.isOccupied(ship[i],column+1)) {
					return false;
				}
				
				//diagonal
				if (Ocean.isOccupied(row-1,column-1) || Ocean.isOccupied(row-1,column+1) || Ocean.isOccupied(row+length,column-1) || Ocean.isOccupied(row+length,column+1)) {
					return false;
				}
			}
			return true;
		}


		
		
	}
	
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) { //assumes it is safe to place ship at row, col
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		
		Ship[][] board = Ocean.getShipArray();
		
		if (horizontal) {
			for (int i = 0; i < ship.length; i++) {
				board[row][column+i] = this;
			}
		}
		else {
			for (int i = 0; i < ship.length; i++) {
				ship[i] = row + i;
			}
		}
		
	}
	
	public boolean shootAt(int row, int column) {
		if (horizontal == true && bowRow == row && column - bowColumn >= 0 && column - bowColumn <= getLength()-1) {
			hit[column-bowColumn] = true;
			return true;
		}
		else if (horizontal == false && bowColumn == column && row - bowRow >= 0 && row - bowRow <= getLength()-1) {
			hit[row - bowRow] = true;
			return true;
		}
		else {
			return false;
		}

	}
	
	public boolean isSunk() {
		for (int i = 0; i < hit.length; i++) {
			if (hit[i] == false) return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		
	}
	
}