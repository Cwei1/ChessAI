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
	GameBoard temp = new GameBoard((new GameBoard(board)).copyOf());
	GameBoard temp0 = new GameBoard((new GameBoard(board)).copyOf());
	GameBoard temp3 = new GameBoard((new GameBoard(board)).copyOf());
	temp0.movePiece(a.getStart(), a.getEnd(), true);
	temp3.movePiece(b.getStart(), b.getEnd(), true);
	if (temp0.getCheckmate())
	    return 1;
	if (temp3.getCheckmate())
	    return -1;
	
	GameBoard temp1 = new GameBoard((new GameBoard(board)).copyOf());
	GameBoard temp2 = new GameBoard((new GameBoard(board)).copyOf());
	temp1.movePiece(a.getStart(), a.getEnd(), true);
	temp2.movePiece(b.getStart(), b.getEnd(), true);
	if (color){
	    if (temp1.inCheckB() &&
		!(temp1.inDanger(a.getEnd()))){
		return 1;
	    }
	    if (temp2.inCheckB() &&
		!(temp2.inDanger(b.getEnd()))){
		return -1;
	    }
	}
	if (!color){
	    if (temp1.inCheckW() &&
		!(temp1.inDanger(a.getEnd()))){
		return 1;
	    }
	    if (temp2.inCheckW() &&
		!(temp2.inDanger(b.getEnd()))){
		return -1;
	    }
	}

	if (!(temp.getPiece(a.getEnd()) instanceof NullPiece) &&
	    !(temp1.inDanger(a.getEnd()))){
	    return 1;
	}
	if (!(temp.getPiece(b.getEnd()) instanceof NullPiece) &&
	    !(temp2.inDanger(b.getEnd()))){
	    return -1;
	}

	if (!(temp.getPiece(a.getEnd()) instanceof NullPiece) &&
	    temp1.inDanger(a.getEnd())){
	    int val1 = 0;
	    int val2 = 0;
	    for (Piece obj: points.keySet()){
		if (obj.getClass().equals(temp.getPiece(a.getEnd()).getClass())){
		    val2 = (points.get(obj)).intValue();
		}
		if (obj.getClass().equals(temp.getPiece(a.getStart()).getClass())){
		    val1 = (points.get(obj)).intValue();
		}
	    }
	    if (val1 < val2)
		return 1;
	}
	if (!(temp.getPiece(b.getEnd()) instanceof NullPiece) &&
	    temp2.inDanger(b.getEnd())){
	    int val1 = 0;
	    int val2 = 0;
	    for (Piece obj: points.keySet()){
		if (obj.getClass().equals(temp.getPiece(b.getEnd()).getClass())){
		    val2 = (points.get(obj)).intValue();
		}
		if (obj.getClass().equals(temp.getPiece(b.getStart()).getClass())){
		    val1 = (points.get(obj)).intValue();
		}
	    }
	    if (val1 < val2)
		return -1;
	}
	return 0;
    }
}
