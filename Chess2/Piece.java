public class Piece{
    protected boolean color; //true = white, false = black
    protected String name;
    protected boolean actualPiece;

    public Piece(boolean color, String name){
	this.color = color;
	this.name = name;
	actualPiece = false;
    }

    public String getName(){
	return name;
    }

    public boolean getActualPiece(){
	return actualPiece;
    }
    public boolean getColor(){
	return color;
    }
    public boolean equals(Piece other){
	return getColor() == other.getColor() && 
	    getName().equals(other.getName());
    }
}
