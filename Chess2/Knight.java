public class Knight extends Piece{
    public Knight (boolean color,String name){
	super(color,name);
	actualPiece = true;
    }
    public static boolean isValidMove (int row1, int col1, int row2, int col2){
	if ((row1 == row2) && (col1 == col2)){
	    return false;
	}
	if (Math.abs(row1-row2) == 2 && Math.abs(col1-col2) == 1){
	    return true;
	}
	else if (Math.abs(row1-row2) == 1 && Math.abs(col1-col2) == 2){
	    return true;
	}
	else{
	    return false;
	}
    }
}
