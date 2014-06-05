import java.util.*;
public class King extends Piece{
    public King(Coordinate location){
	super(location);
    }
    public void setImage(){
	super.setImage("Pics/bking.png","Pics/wking.png");
    }
    public ArrayList<Coordinate> getMoves(GameBoard g){
	moves(g);
	return moves;
    }
    public void moves(GameBoard g){
	moves = new ArrayList<Coordinate>();
	Piece temp=new NullPiece();
	try{
	    temp=g.getPiece(getx()+1,gety());
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()+1,gety()));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx()-1,gety());
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()-1,gety()));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx()+1,gety()+1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()+1,gety()+1));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx()+1,gety()-1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()+1,gety()-1));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx(),gety()-1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx(),gety()-1));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx(),gety()+1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx(),gety()+1));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx()-1,gety()+1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()-1,gety()+1));
	    }
	}catch(Exception e){}
	try{
	    temp=g.getPiece(getx()-1,gety()-1);
	    if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())||temp instanceof NullPiece){
		moves.add(new Coordinate(getx()-1,gety()-1));
	    }
	}catch(Exception e){}
    }
    public String toString(){
	return "K";
    }
}
