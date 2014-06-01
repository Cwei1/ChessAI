import java.util.*;
public class Rook extends Piece{
    public Rook(Coordinate location, int l, int w){
	super(location,l,w);
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
