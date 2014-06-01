import java.util.*;
import javax.swing.*;
public class GameBoard{
    protected Piece[][] board;
    protected JLabel[][] pattern;
    protected boolean checkmate,stalemate;
    protected Player p1, p2;
    protected int l,w,turns;
    private String light = "light.jpg";
    private String dark ="dark.jpg";
    private String now = light; 
    private boolean bck,wck;
    public GameBoard(int x, int y){ 
	board = new Piece[y][x];
        pattern = new JLabel[y][x];
	p1 = new Player(true);
	p2 = new Player(false);
	l=x;
	w=y;
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
	    return "Player 1 Wins!";
	}else if(checkmate && wck){
	    return "Player 2 Wins!";
	}else if(stalemate){
	    return "Tie!";
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
    public void rbc(){
	bck=false;
    }
    public void rwc(){
	wck=false;
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
		    board[x][y] = new Pawn(new Coordinate(x,y),l,w);
		}else if((x==0||x==board.length-1)&&(y==0||y==board.length-1)){
		    board[x][y]=new Rook(new Coordinate(x,y),l,w);
		}else if((x==1||x==board.length-2)&&(y==0||y==board.length-1)){
		    board[x][y]=new Knight(new Coordinate(x,y),l,w);
		}else if((x==2||x==board.length-3)&&(y==0||y==board.length-1)){
		    board[x][y]=new Bishop(new Coordinate(x,y),l,w);
		}
	    }
	}
	board[3][0]=new King(new Coordinate(3,0),l,w);
	board[4][0]=new Queen(new Coordinate(4,0),l,w);
	board[3][board.length-1]=new King(new Coordinate(3,board.length-1),l,w);
	board[4][board.length-1]=new Queen(new Coordinate(4,board.length-1),l,w);
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
	board[3][0].setPlayer(p1);
	board[4][0].setPlayer(p1);
	board[3][board.length-1].setPlayer(p2);
	board[4][board.length-1].setPlayer(p2);
    }
    public void iniImages(){
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
	        if(board[y][x] instanceof Pawn){
		    ((Pawn)board[y][x]).setImage();
		} else if(board[y][x] instanceof Rook){
		    ((Rook)board[y][x]).setImage();
		} else if(board[y][x] instanceof Bishop){
		    ((Bishop)board[y][x]).setImage();
		} else if(board[y][x] instanceof Knight){
		    ((Knight)board[y][x]).setImage();
		} else if(board[y][x] instanceof Queen){
		    ((Queen)board[y][x]).setImage();
		} else if(board[y][x] instanceof King){
		    ((King)board[y][x]).setImage();
		} else if(board[y][x] instanceof NullPiece){
		    ((NullPiece)board[y][x]).setImage();
		} 
	    }
	}
    }
    public void cleanBoard(){
	for(int x = 0; x < board.length; x++){
	    for(int y = 0; y < board[x].length; y++){
		board[x][y] = new NullPiece(new Coordinate(x,y),l,w);
	    }
	}
    } 
    public Piece getPiece(int x, int y){
	return board[x][y];
    }
    public void setPiece(int x, int y,Piece p){
	board[x][y]=p;
    }
    public boolean castle(boolean b){
	if((b&&wck)||(!b&&bck)){
	    return false;
	}
	Scanner s=new Scanner(System.in);
	String ln="";
	System.out.println("Choose the king then the piece to castle.\t");
	ln=s.nextLine();
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
	    if(rk.getx()>(int)(.5*w)){
		for(int i=px.getx()+1;i<rk.getx();i++){
		    if(!(board[i][px.gety()] instanceof NullPiece)){
			return false;
		    }
		}
		board[px.getx()+3][px.gety()]=px;
		board[px.getx()+3][px.gety()].setxy(px.getx()+3,px.gety());
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
		board[px.getx()-2][px.gety()].setxy(px.getx()-3,px.gety());
		board[px.getx()+1][px.gety()]=rk;
		board[px.getx()+1][px.gety()].setxy(px.getx()+1,px.gety());
		board[px.getx()][px.gety()].setFirst(false);
		board[px.getx()][px.gety()].setFirst(false);
	    }
	    board[x1][y1]=new NullPiece(new Coordinate(x1,y1),l,w);
	    ((NullPiece)board[x1][y1]).setImage();
	    board[x2][y2]=new NullPiece(new Coordinate(x2,y2),l,w);
	    ((NullPiece)board[x2][y2]).setImage();
	    return true;	    
	}catch(Exception e){
	    return false;
	}
    }
    public boolean movePiece(int x,int y, int a, int b){
	Coordinate temp=new Coordinate(a,b);
	Piece p= getPiece(x,y);
	ArrayList<Coordinate> moves=new ArrayList();
	if(p instanceof Pawn){
	    moves=((Pawn)p).getMoves(this);
	}else if(p instanceof Rook){
	    moves=((Rook)p).getMoves(this);
	}else if(p instanceof Knight){
	    moves=((Knight)p).getMoves(this);
	}else if(p instanceof Bishop){
	    moves=((Bishop)p).getMoves(this);
	}else if(p instanceof Queen){
	    moves=((Queen)p).getMoves(this);
        }else if(p instanceof King){
	    moves=((King)p).getMoves(this);
	} 
	for(Coordinate c: moves){
	    if(c.getx()<0||c.getx()>=w||c.gety()<0||c.gety()>=l){
		moves.remove(c);
	    }
	}
	for(int i=0;i<moves.size();i++){
	    if(temp.equals(moves.get(i))){
		Piece t= getPiece(a,b);
		boolean first=false;
		board[a][b]=getPiece(x,y);
		board[x][y]=new NullPiece(new Coordinate(x,y),l,w);
		((NullPiece)board[x][y]).setImage();
		getPiece(a,b).setxy(a,b);
		first=p.isFirst();
		p.setFirst(false);
		if(checkCheck(getPiece(a,b).isWhite())){
		    board[x][y]=getPiece(a,b);
		    board[a][b]=t;
		    getPiece(x,y).setFirst(first);
		    getPiece(x,y).setxy(x,y);
		    turns++;
		    if(turns ==3&&(checkCheck(true)||checkCheck(false))){
			checkmate=true;
		    }else if(turns ==3){
			stalemate=true;
		    }
		    return false;
		}	
		if(getPiece(a,b) instanceof Pawn&&((getPiece(a,b).isWhite()&&b==l-1)||(!getPiece(a,b).isWhite()&&b==0))){
		    board[a][b]=((Pawn)getPiece(a,b)).upgrade();
		}
		checkCheck(true);
		checkCheck(false);
		return true;
	    }	 
	}   
	return false;
    }
    public JLabel[][] getPattern(){
        return pattern;
    }
   
    public String toString(){
	String s="";
	for(int i = 0; i<board.length;i++){
	    s+=(i+1)+"  ";
	    for(int j=0;j<board[i].length;j++){
		s+=board[j][i]+" ";
	    }
	    s+="\n";
	}
	return s+"\n   a b c d e f g h\n";
    }
    public boolean checkCheck(boolean boo){
	rwc();
	rbc();
	ArrayList<Coordinate>white=new ArrayList<Coordinate>();
	ArrayList<Coordinate>black=new ArrayList<Coordinate>();
	Piece bk=new NullPiece(l,w);
	Piece wk=new NullPiece(l,w);
	for(int x=0;x<w;x++){
	    for(int y=0;y<l;y++){
		if(!(board[x][y] instanceof NullPiece)){
		    ArrayList<Coordinate>temp=new ArrayList<Coordinate>();
		    Piece t= board[x][y];
		    if(t instanceof King){
			temp=((King)t).getMoves(this);
			if(t.isWhite()){
			    wk=t;
			}else{
			    bk=t;
			}
		    }else if(t instanceof Pawn){
			temp=((Pawn)t).getMoves(this);
		    }else if(t instanceof Rook){
			temp=((Rook)t).getMoves(this);
		    }else if(t instanceof Knight){
			temp=((Knight)t).getMoves(this);
		    }else if(t instanceof Bishop){
			temp=((Bishop)t).getMoves(this);
		    }else if(t instanceof Queen){
			temp=((Queen)t).getMoves(this);
		    }
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
	    }
	}
	for(int i=0;i<black.size();i++){
	    if(wk.getLocation().equals(black.get(i))){
		wck=true;
	    }
	}
	if(boo){
	    return wck;
	}
	return bck;
    }
}

    
