import java.util.*;
import javax.swing.*;
public class GameBoard{
    protected Piece[][] board;
    protected JLabel[][] pattern;
    protected boolean checkmate,stalemate;
    protected Player p1, p2;
    private String light = "Pics/light.jpg";
    private String dark ="Pics/dark.jpg";
    private String now = light; 
    private boolean bck,wck;
    public GameBoard(){ 
	board = new Piece[8][8];
        pattern = new JLabel[8][8];
	p1 = new Player(true);
	p2 = new Player(false);
	bck=false;
	wck=false;
	stalemate=false;
	checkmate=false;
    }
    public boolean getdone(){
	return stalemate || checkmate;
    }
    public String win(){
	if(checkmate && bck){
	    return "Checkmate!\nPlayer 1 Wins!";
	}else if(checkmate && wck){
	    return "Checkmate!\nPlayer 2 Wins!";
	}else if(stalemate){
	    return "Stalemate!!";
	}
	return "";
    }
    public Piece[][] getBoard(){
	return board;
    }
    public boolean inCheckB(){
    	return bck;
    }
    public boolean inCheckW(){
    	return wck;
    }
    public Piece getPiece(int x, int y){
	return board[x][y];
    }
    public void setPiece(int x, int y,Piece p){
	board[x][y]=p;
    }
    public void cleanBoard(){
	for(int x = 0; x < board.length; x++){
	    for(int y = 0; y < board[x].length; y++){
		board[x][y] = new NullPiece(new Coordinate(x,y));
	    }
	}
    } 
    public Piece[][] copyOf (){
	Piece[][] temp = new Piece[board.length][board[0].length];
	for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board.length; j++){
		Coordinate c=new Coordinate(board[i][j].getx(),board[i][j].gety());
		if(board[i][j] instanceof Pawn){
		    temp[i][j]=new Pawn(c);
		}else if(board[i][j] instanceof Rook){
		    temp[i][j]=new Rook(c);
		}else if(board[i][j] instanceof Knight){
		    temp[i][j]=new Knight(c);
		}else if(board[i][j] instanceof Bishop){
		    temp[i][j]=new Bishop(c);
		}else if(board[i][j] instanceof Queen){
		    temp[i][j]=new Queen(c);
		}else if(board[i][j] instanceof King){
		    temp[i][j]=new King(c);
		}else{
		    temp[i][j]=new NullPiece(c);
		}
		temp[i][j].setPlayer(board[i][j].getPlayer());
		temp[i][j].setImage(board[i][j].getAvatar(),board[i][j].getPic());
		temp[i][j].setFirst(board[i][j].isFirst());
	    }
	}
	return temp;
    }
    //--------------------------------------Gui Stuff for Cardy to fix--------------------------------
    public JLabel[][] getPattern(){
        return pattern;
    }
    public void initialize(){
	cleanBoard();
        for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
		pattern[y][x] = new JLabel();
		pattern[y][x].setIcon(new javax.swing.ImageIcon(getClass().getResource(now)));
		if (now.equals(dark)){
		    now = light;
		}else now = dark;
	    }
            if (now.equals(dark)){
		now = light;
            }else now = dark;
	}
	for(int x = 0; x < board.length; x++){
	    for(int y = 0; y < board[x].length; y++){
		if(y==1||y==board.length-2){		    
		    board[x][y] = new Pawn(new Coordinate(x,y));
		}else if((x==0||x==board.length-1)&&(y==0||y==board.length-1)){
		    board[x][y]=new Rook(new Coordinate(x,y));
		}else if((x==1||x==board.length-2)&&(y==0||y==board.length-1)){
		    board[x][y]=new Knight(new Coordinate(x,y));
		}else if((x==2||x==board.length-3)&&(y==0||y==board.length-1)){
		    board[x][y]=new Bishop(new Coordinate(x,y));
		}
	    }
	}
	board[3][0]=new Queen(new Coordinate(3,0));
	board[4][0]=new King(new Coordinate(4,0));
	board[3][board.length-1]=new Queen(new Coordinate(3,board.length-1));
	board[4][board.length-1]=new King(new Coordinate(4,board.length-1));
	iniSide();
	iniImages();
    } 
    public void iniSide(){
	for(int i = 0; i < board.length; i++){	    
	    board[i][0].setPlayer(p1);
	    board[i][1].setPlayer(p1);
	    board[i][board.length-1].setPlayer(p2);
	    board[i][board.length-2].setPlayer(p2);
	}
    }
    public void iniImages(){
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
	        board[x][y].setImage(); 
	    }
	}
    }

    //----------------------------CASTLING---------------------------------------
    public boolean castle(boolean b,String ln){
	if((b&&wck)||(!b&&bck)){
	    return false;
	}
	try{
	    int x1=ln.charAt(0)-'a';
	    int y1=Integer.parseInt(""+ln.charAt(1))-1;
	    int x2=ln.charAt(3)-'a';
	    int y2=Integer.parseInt(""+ln.charAt(4))-1;
	    Piece px=getPiece(x1,y1);
	    Piece rk=getPiece(x2,y2);
	    Piece temp=getPiece(x1,y1);
	    if(!(px instanceof King)||!px.isFirst()||!(rk instanceof Rook)||!rk.isFirst()||(b&&!px.isWhite())||(!b&&px.isWhite())){
		return false;
	    }
	    if(rk.getx()>(int)(4)){
		for(int i=px.getx()+1;i<rk.getx();i++){
		    if(!(board[i][px.gety()] instanceof NullPiece)){
			return false;
		    }
		}
		board[px.getx()+2][px.gety()]=px;
		board[px.getx()+2][px.gety()].setxy(px.getx()+2,px.gety());
		board[px.getx()-1][px.gety()]=rk;
		board[px.getx()-1][px.gety()].setxy(px.getx()-1,px.gety());
		board[px.getx()][px.gety()].setFirst(false);
		board[px.getx()][px.gety()].setFirst(false);
	    }else{
		for(int i=px.getx()-1;i>rk.getx();i--){
		    if(!(board[i][px.gety()] instanceof NullPiece)){
			return false;
		    }
		}
		board[px.getx()-2][px.gety()]=px;
		board[px.getx()-2][px.gety()].setxy(px.getx()-2,px.gety());
		board[px.getx()+1][px.gety()]=rk;
		board[px.getx()+1][px.gety()].setxy(px.getx()+1,px.gety());
		board[px.getx()][px.gety()].setFirst(false);
		board[px.getx()][px.gety()].setFirst(false);
	    }
	    board[x1][y1]=new NullPiece(new Coordinate(x1,y1));
	    ((NullPiece)board[x1][y1]).setImage();
	    board[x2][y2]=new NullPiece(new Coordinate(x2,y2));
	    ((NullPiece)board[x2][y2]).setImage();
	    return true;	    
	}catch(Exception e){
	    return false;
	}
    } 
    public void upgrade(Piece x,String ln){
	Piece p = new NullPiece();
	if(ln.equals("Knight")){
	    p=new Knight(x.getLocation());
	}else if(ln.equals("Rook")){
	    p=new Rook(x.getLocation());
	}else if(ln.equals("Bishop")){
	    p=new Bishop(x.getLocation());
	}else if(ln.equals("Queen")){
	    p=new Queen(x.getLocation());
	}
	p.setPlayer(x.getPlayer());
	p.setImage();
	board[x.getx()][x.gety()]=p;
    }
    //-----------------------------------Moving a piece--------------------------------------------
    public boolean movePiece(int x,int y, int a, int b,boolean check){
	Coordinate temp=new Coordinate(a,b);
	Piece p= getPiece(x,y);
	ArrayList<Coordinate> moves=new ArrayList();
	moves=p.getMoves(this);
	for(int i=0;i<moves.size();i++){
	    if(temp.equals(moves.get(i))){
		Piece t= getPiece(a,b);
		board[a][b]=getPiece(x,y);
		board[x][y]=new NullPiece(new Coordinate(x,y));
		((NullPiece)board[x][y]).setImage();
		getPiece(a,b).setxy(a,b);
		p.setFirst(false);
		inCheck(check);
		return true;
	    }	 
	}   
	return false;
    }
    //---------------------------------------Check and Checkmate--------------------------------------------
    public void inCheck(boolean first){
	wck=false;
	bck=false;
	ArrayList<Coordinate>white=new ArrayList<Coordinate>();
	ArrayList<Coordinate>black=new ArrayList<Coordinate>();
	Piece bk=new NullPiece();
	Piece wk=new NullPiece();
	for(int x=0;x<8;x++){
	    for(int y=0;y<8;y++){
		if(!(board[x][y] instanceof NullPiece)){
		    ArrayList<Coordinate>temp=new ArrayList<Coordinate>();
		    Piece t= board[x][y];
		    if(t instanceof King){
			if(t.isWhite()){
			    wk=t;
			}else{
			    bk=t;
			}
		    }
		    temp=t.getMoves(this);
		    if(t.isWhite()){
			for(int i=0;i<temp.size();i++){
			    white.add(temp.get(i));
			}
		    }else{
			for(int i=0;i<temp.size();i++){
			    black.add(temp.get(i));
			}
		    }
		}
	    }
	}
	for(int i=0;i<white.size();i++){
	    if(bk.getLocation().equals(white.get(i))){
		bck=true;
		if(first){
		    checkmate(false);
		}
	    }
	}
	for(int i=0;i<black.size();i++){
	    if(wk.getLocation().equals(black.get(i))){
		wck=true;
		if(first){
		    checkmate(true);
		}
	    }
	}
    }
    public void checkmate(boolean white){
	checkmate=true;
	for(int i=0;i<8;i++){
	    for(int j=0;j<8;j++){
		Piece temp= board[i][j];
		if((white && temp.isWhite())||(!white && !temp.isWhite())){
		    ArrayList<Coordinate> moves= temp.getMoves(this);
		    for(Coordinate c: moves){
			Piece[][]fake=copyOf();
			movePiece(i,j,c.getx(),c.gety(),false);
		        for(int a=0;a<8;a++){
			    for(int b=0;b<8;b++){
				board[a][b]=fake[a][b];
			    }
			}
			if(white && !wck){
			    checkmate=false;
			    return;
			}else if(!white && !bck){
			    checkmate=false;
			    return;
			}		
		    }
		}
	    }
	}
    }
		
    //---------------------------To String Method-------------------------------------
    public String toString(){
	String s="";
	for(int i = 0; i<board.length;i++){
	    s+=(8-i)+"  ";
	    for(int j=0;j<board[i].length;j++){
		s+=board[j][7-i]+" ";
	    }
	    s+="\n";
	}
	return s+"\n   a b c d e f g h\n";
    }
}

    
