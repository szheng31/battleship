/***
* Stanley Zheng, Aileen Ruan, Subaah Syed
*
* Describes characteristics common to all ships, has the subclasses: Battleship,
* Cruiser, Destroyer, Submarine, EmptySea
***/
public abstract class Ship {
	private int bowRow,bowColumn; //column + row of bow (front) of ship
	protected int length; // # of squares occupied by ship
	private boolean horizontal; // true if ship occupies single row
	private boolean[] hit = new boolean[4]; // tells if part of ship has been hit


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

	// checks if coordinates given are outside of the board
	private boolean inBound(int row, int col) {
		if (row < 0 || row > 9) return false;
    if (col < 0 || col > 9) return false;
    return true;
  }

	// checks if it is okay to place a ship of this length in this location
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// if coordinates given are not in the board
		if (!inBound(row,column)) return false;
		// if tiles are occupied
		if (ocean.isOccupied(row,column)) return false;

		// if ship is horizontal
		if (horizontal) {

			// if part of the ship is outside of the board
			if (column + length > 10) {
				return false;
			}
			else {
				for (int i = row-1; i<= row + 1;i++) {
					for (int j = column-1;j<=column+length;j++) {
						// if the coordinates given are valid
						if (inBound(i,j)) {
							// if there is already a ship here
							if((ocean.isOccupied(i,j))) return false;
						}
					}
				}
			}
		}
		// if ship is vertical
		else {
			// if part of the ship is out of the board
			if (row + length > 10) {
				return false;
			}
			else {
				for (int j = column - 1; j <= column + 1; j++) {
					for (int i = row - 1; i <= row+length; i++) {
						// if coordinates are valid
						if( inBound(i,j)) {
							// if there is already a ship here
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
		if (okToPlaceShipAt(row,column,horizontal,ocean)) {
			setBowRow(row);
			setBowColumn(column);
			setHorizontal(horizontal);

			Ship[][] board = ocean.getShipArray();

			// updates the board
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

	// updates hit array
	public boolean shootAt(int row, int column) {
		// if ship hasn't been sunk
		if (!isSunk()) {
			// if ship is horizontal
			if (horizontal) {
				if (row == getBowRow() && column < getBowColumn() + length) {
					hit[column - getBowColumn()] = true;
					return true;
				}

			}
			// if ship is vertical
			else {
				if (column == getBowColumn() && row < getBowRow() + length) {
					hit[row - getBowRow()] = true;
					return true;
				}
			}
		}
		return false;

	}

	// returns true if every part of the ship has been hit, false otherwise
	public boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) return false;
		}
		return true;
	}

	// returns a single-character String to use in Ocean's print to print locations that have been shot at
	@Override
	public String toString() {
		// if ship has been sunk
		if (this.isSunk()) {
			return "x";
		}
		// if ship has not been sunk but has been hit
		else {
			return "S";
		}
	}

}
