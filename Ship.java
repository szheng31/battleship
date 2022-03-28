public abstract class Ship {
	private int bowRow,bowColumn;
	protected int length;
	private boolean horizontal;
	private boolean[] hit = new boolean[4];


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
	
	private boolean inBound(int row, int col) {
		if (row < 0 || row > 9) return false;
    	if (col < 0 || col > 9) return false;
    	return true;
    }

	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	/*Returns true if it is okay to put a ship of this length with its bow in this location, with the given orientation, and returns false otherwise. 
	  The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally), and it must not "stick out" beyond the 
	  array. Does not actually change either the ship or the Ocean, just says whether it is legal to do so. Uses the inBound method from Ocean.java.
	  */
		if (!inBound(row,column)) return false;
		// if tiles are occupied
		if (ocean.isOccupied(row,column)) return false;

		// if ship is horizontal
		if (horizontal) {
			if (column + length > 10) {
				return false;
			}
			else {
				for (int i = row-1; i<= row + 1;i++) {
					for (int j = column-1;j<=column+length;j++) {
						if (inBound(i,j)) {
							if((ocean.isOccupied(i,j))) return false;
						}
					}
				}
			}
		}
		else {
			if (row + length > 10) {
				return false;
			}
			else {
				for (int j = column - 1; j <= column + 1; j++) {
					for (int i = row - 1; i <= row+length; i++) {
						if( inBound(i,j)) {
							if ((ocean.isOccupied(i,j)))return false;
						}
					}
				}
			}
		}
		return true;
	}

	//assumes it is safe to place ship at row, col
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	/* "Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship,
	   and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
	   */
		if (okToPlaceShipAt(row,column,horizontal,ocean)) {
			setBowRow(row);
			setBowColumn(column);
			setHorizontal(horizontal);

			Ship[][] board = ocean.getShipArray();

			if (horizontal) {
				for (int i = 0; i < length; i++) {
					board[row][column+i] = this;
				}
			}
			else {
				for (int i = 0; i < length; i++) {
					board[row + i][column] = this;
				}
			}
		}
		

	}

	public boolean shootAt(int row, int column) {
	/*If a part of the ship occupies the given row and column, and the ship hasn't already been sunk, mark that part of the ship 
	  as "hit" and return true, otherwise return false.
	  */
		if (!isSunk()) {
			if (horizontal) {
				if (row == getBowRow() && column < getBowColumn() + length) {
					hit[column - getBowColumn()] = true;
					return true;
				}

			}
			else {
				if (column == getBowColumn() && row < getBowRow() + length) {
					hit[row - getBowRow()] = true;
					return true;
				}
			}
		}
		return false;

	}

	public boolean isSunk() 
	//Return true if every part of the ship has been hit, false otherwise.
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) return false;
		}
		return true;
	}

	@Override
	public String toString() {
	//This method returns "x" if the ship has been sunk, "S" if it has not been sunk. 
		if (this.isSunk()) {
			return "x";
		}
		else {
			return "S";
		}
	}

}
