import java.util.*;
import java.io.*;
public class ChessAI{
    private PriorityQueue<Move> moves;
    private boolean color;
    private GameBoard board;
    
    public ChessAI (boolean color,GameBoard board){
	this.color = color;
	moves = new PriorityQueue<Coordinate>();
	this.board=board;
    }
    
    public void getMoves (){
	for(int i=0;i<8;i++){
	    for(int j=0;j<8;j++){
		if(board.getPiece(i,j).isWhite()&&color){
		    moves.add(new Move(new Coordinate(i,j),Board.bestMove(i,j)));
		}
	    }
	}
    }
    public Move Play(){
	return moves.poll();
    }
}
