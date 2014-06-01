import java.util.*;
public class Queen extends Piece{
    public Queen(Coordinate location, int l, int w){
	super(location,l,w);
    }
    public void setImage(){
	super.setImage("bqueen.png","wqueen.png");
    }
    public ArrayList<Coordinate> getMoves(GameBoard g){
	moves(g);
	return moves;
    }
    public void moves(GameBoard g){
	moves = new ArrayList<Coordinate>();
        bishop(g);
	rook(g);
    }
    public String toString(){
	return "Q";
    }
}
