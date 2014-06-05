import java.util.*;
public class Rook extends Piece{
    public Rook(Coordinate location){
	super(location);
    }
    public void setImage(){
	super.setImage("brook.png","wrook.png");
    }
    public ArrayList<Coordinate> getMoves(GameBoard g){
	moves(g);
	return moves;
    }
    public void moves(GameBoard g){
	moves = new ArrayList<Coordinate>();
	rook(g);
    }
    public String toString(){
	return "R";
    }
}
