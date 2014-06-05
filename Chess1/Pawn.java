import java.util.*;
import javax.swing.*;
public class Pawn extends Piece{
    public Pawn(Coordinate location){
	super(location);
	first=true;
    }
    public void setImage(){
	super.setImage("bpawn.png","wpawn.png");
    }
    public Piece upgrade(){
	Piece p = new NullPiece();
	Scanner s=new Scanner(System.in);
	System.out.println("Choose a piece for your pawn to upgrade into. The choices are Knight, Rook, Bishop, Queen\n");
	String ln= s.nextLine();
	if(ln.equals("Knight")){
	    p=new Knight(getLocation());
	}else if(ln.equals("Rook")){
	    p=new Rook(getLocation());
	}else if(ln.equals("Bishop")){
	    p=new Bishop(getLocation());
	}else if(ln.equals("Queen")){
	    p=new Queen(getLocation());
	}
	p.setPlayer(new Player(isWhite()));
	p.setFirst(false);
	return p;
    }
    public ArrayList<Coordinate> getMoves(GameBoard g){
	moves(g);
	return moves;
    }
    public void moves(GameBoard g){
	moves = new ArrayList<Coordinate>();
	Piece temp= new NullPiece(new Coordinate(0,0));
	int w=-1;
	if(isWhite()){
	    w=1;
	}
	try{
	    temp=g.getPiece(getx()+1,gety()+w);
	    if(!(temp instanceof NullPiece)){	    
		if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())){
		    moves.add(new Coordinate(getx()+1,gety()+w));
		}
	    }
	}catch(Exception e){
	}try{
	    temp=g.getPiece(getx()-1,gety()+w);
	    if(!(temp instanceof NullPiece)){	
		if((temp.isWhite()&&!isWhite())||(!temp.isWhite()&&isWhite())){
		    moves.add(new Coordinate(getx()-1,gety()+w));
		}
	    }
	}catch(Exception e){
	}
	if(first){
	    for(int i=1;i<3;i++){		
		temp=g.getPiece(getx(),gety()+(i*w));
		if(temp instanceof NullPiece){
		    moves.add(new Coordinate(getx(),gety()+(i*w)));
		}
	    }

	}else{
	    try{
		temp=g.getPiece(getx(),gety()+w);
		if(temp instanceof NullPiece){
		    moves.add(new Coordinate(getx(),gety()+w));
		}
	    }catch(Exception e){
	    }
	}	
    }
    public String toString(){
	return "P";
    }
} 
