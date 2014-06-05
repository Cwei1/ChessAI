public class NullPiece extends Piece{
    public NullPiece(Coordinate location){
	super(location);
    }
    public NullPiece(){
	this(new Coordinate(0,0));
    }
    public String toString(){
	return "_";
    }
    public void setImage(){
	super.setImage("null.png","null.png");
    }
} 
