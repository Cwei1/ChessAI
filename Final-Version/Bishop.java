import java.util.*;
public class Bishop extends Piece{
    public Bishop(Coordinate location){
	super(location);
    }
    public void setImage(){
	super.setImage("Pics/bbishop.png","Pics/wbishop.png");
    }
    public ArrayList<Coordinate> getMoves(GameBoard g){
	moves(g);
	return moves;
    }
    public void moves(GameBoard g){
	moves = new ArrayList<Coordinate>();
	bishop(g);
    }
    public String toString(){
	return "B";
    }
} 
