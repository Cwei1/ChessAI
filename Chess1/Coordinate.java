public class Coordinate{
    int x, y;
    public Coordinate(int x, int y){
	this.x=x;
	this.y=y;
    }
    public int getx(){
	return x;
    }
    public int gety(){
	return y;
    }
    public void setxy(int x, int y){
	this.x=x;
	this.y=y;
    }
    public String toString(){
	return ""+((char)('a'+x))+""+(y+1);
    }
    public boolean equals(Coordinate o){
	return this.x==o.getx()&&this.y==o.gety();
    }
}
    
	
