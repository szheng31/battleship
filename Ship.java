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
							if(!(ocean.isOccupied(i,j))) return false;
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
							if (!(ocean.isOccupied(i,j)))return false;
						}
					}
				}
			}
		}
		return true;
	}

	//assumes it is safe to place ship at row, col
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
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

	public boolean shootAt(int row, int column) {
		//horizontal == true && bowRow == row && column - bowColumn >= 0 && column - bowColumn <= getLength()-1
		if (!this.isSunk()) {
			if (horizontal) {
				hit[column-bowColumn] = true;
				return true;
			}
		    else {
		// horizontal == false && bowColumn == column && row - bowRow >= 0 && row - bowRow <= getLength()-1

				hit[row - bowRow] = true;
			}
		}
		
		return false;

	}

	public boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		if (this.isSunk()) {
			return "x";
		}
		else {
			return "S";
		}
	}

}
