public class Bishop extends Piece{
    public Bishop (boolean color, String name){
	super(color,name);
	actualPiece = true;
    }
    public static boolean isValidMove (Piece[][] board, int row1, int col1, int row2, int col2){
	if (row1 == row2){
	    return false;
	}
	if (Math.abs(row1-row2) == Math.abs(col1-col2)){
	    int dir1, dir2;
	    if (row1 - row2 < 0){
		dir1 = 1;
	    }
	    else{
		dir1 = -1;
	    }
	    if (col1-col2 < 0){
		dir2 = 1;
	    }
	    else{
		dir2 = -1;
	    }
	    do{
		row1 += (1 * dir1);
		col1 += (1 * dir2);
	    }
	    while (!board[row1][col1].getActualPiece() && 
		   row1 != row2 && 
		   col1 != col2);
	    return row1 == row2 && col1  == col2;
	}
	return false;
    }
}
