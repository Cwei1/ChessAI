public class NullPiece extends Piece{
    public NullPiece(Coordinate location, int l, int w){
	super(location,l,w);
    }
    public NullPiece(int l, int w){
	this(new Coordinate(0,0),l,w);
    }
    public String toString(){
	return "_";
    }
    public void setImage(){
	super.setImage("null.png","null.png");
    }
} 
