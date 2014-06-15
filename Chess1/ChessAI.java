import java.util.*;
import java.io.*;
public class ChessAI{
    private PriorityQueue<Move> moves;
    private boolean color;
    private GameBoard board;
    
    public ChessAI (boolean color,GameBoard board){
	this.color = color;
	Comparator<Move> comp = new MoveComp(board, color);
	moves = new PriorityQueue<Move>(100,comp);
	this.board=board;
	getMoves();
    }
    
    public void getMoves (){
	for(int i=0;i<8;i++){
	    for(int j=0;j<8;j++){
		if(board.getPiece(i,j).isWhite() &&
		   color &&
		   !(board.getPiece(i,j) instanceof NullPiece)){
		    ArrayList<Coordinate> places = (board.getPiece(i,j)).getMoves(board);
		    for (Coordinate c: places){
			moves.add(new Move(new Coordinate (i,j), c));
		    }
		}
	    }
	}
    }
    public Move bestMove(){
	return moves.poll();
    }
}
