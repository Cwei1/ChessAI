public class Pawn extends Piece{
    protected boolean firstMove;
    
    public Pawn (boolean color,String name, boolean firstMove){
	super(color, name);
	this.firstMove = firstMove;
	actualPiece = true;
    }
    
    public static boolean isValidMove (Piece[][] board, int row1, int col1, int row2, int col2){
	if ((row1 == row2) && (col1 == col2)){
	    return false;
	}
	try{
	    if (((Pawn)(board[row1][col1])).getFirstMove()){
		if (row1-row2 == 1 && col1-col2 == 0){
		    return true;
		}
		if (row1-row2 == 2 && col1 - col2 == 0){
		    return true;
		}
	    }
	    else{
		return row1-row2 == 1 && col1-col2 == 0;
	    }
	}
	catch(IndexOutOfBoundsException e){
	    return false;
	}
	return false;
    }
    
    public boolean getFirstMove(){
	return firstMove;
    }
    
    public void setFirstMove(boolean firstMove){
	this.firstMove = firstMove;
    }
    
    public static boolean pawnEat (int row1, int col1, int row2, int col2){
	if (row1-row2 == 1 && Math.abs(col1-col2) == 1){
	    return true;
	}
	else{
	    return false;
	}
    }
}
