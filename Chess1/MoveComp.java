import java.util.*;
public class MoveComp implements Comparator<Move>{
    private HashMap<Piece,Integer> points;
    private Piece[][] board;
    private boolean color;

    public MoveComp(GameBoard g, boolean color){
	board = g.getBoard();
	this.color = color;
	points = new HashMap<Piece,Integer>();
	Coordinate fake = new Coordinate(0,0);

	points.put(new NullPiece(fake), new Integer(0));
	points.put(new King(fake), new Integer(0));

	points.put(new Pawn(fake), new Integer(1));
	points.put(new Bishop(fake), new Integer(3));
	points.put(new Knight(fake), new Integer(3));
	points.put(new Rook(fake), new Integer(5));
	points.put(new Queen(fake), new Integer(9));
    }

    public int getPointValue (GameBoard g, boolean color){
	Piece[][] board = g.getBoard();
	int value = 0;
	for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board[i].length; j++){
		Piece thing = board[i][j];
		if (thing.getPlayer().isWhite() == color){
		    for (Piece p: points.keySet()){
			if (thing.getClass().equals(p.getClass())){
			    value += points.get(p);
			    break;
			}
		    }
		}
	    }
	}
	return value;
    }

    public int compare(Move a, Move b){

	return 0;
    }


}
