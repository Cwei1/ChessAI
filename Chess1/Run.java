import java.util.*;
import java.io.*;

public class Run{
    public static void delay(int time){
	try {
	    Thread.sleep(time);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }
    public static void main(String[]args){
	GameBoard g= new GameBoard();
	Gui main=new Gui(g);
	int turn=0;
	boolean auto=false;
	String response = "";
	Scanner s=new Scanner(System.in);
	if(args.length>0){
	    try{
		s= new Scanner(new File("Games/"+args[0]));
		auto=true;
	    }catch(FileNotFoundException e){
		System.out.println("File not found!");
		auto=true;
	    }
	}
	System.out.println(g);
	System.out.println("How to play: just chess.");
	while(!g.getdone()){
	    String x=" ";
	    if(turn%2==0){
		x="White";
	    }else{
		x="Black";
	    }
	    System.out.println("It is "+x+"'s turn!\n");
	    String l = "";
	    try {
		l=s.nextLine();
		System.out.println(l + "\n");
	    }
	    catch (NoSuchElementException e){
		System.out.println("Game is over");
		break;
	    }
	    if(l.equals("Castle")){
		System.out.println("Choose the king then the piece to castle.\t");
		String move=s.nextLine();
		if(x.equals("White")){
		    if(!g.castle(true,move)){
			System.out.println(g);
			System.out.println("Illegal Move!");
		    }else{
			System.out.println(g);
			turn++;
		    }
		}else{
		    if(!g.castle(false,move)){
			System.out.println(g);
			System.out.println("Illegal Move!");
		    }else{
			System.out.println(g);
			turn++;
		    }
		}
	    }else if(l.length()==5&&l.charAt(2)==' '){
		try{
		    int x1=l.charAt(0)-'a';
		    int y1=Integer.parseInt(""+l.charAt(1))-1;
		    int x2=l.charAt(3)-'a';
		    int y2=Integer.parseInt(""+l.charAt(4))-1;
		    Piece px=g.getPiece(x1,y1);
		    if(px instanceof NullPiece ||(px.isWhite()&&x.equals("Black"))||(!px.isWhite()&&x.equals("White"))){
			System.out.println(g);
			System.out.println("Invalid move!");
		    }else if(g.movePiece(x1,y1,x2,y2,true)){
			if(g.getPiece(x2,y2) instanceof Pawn&&((g.getPiece(x2,y2).isWhite()&&y2==7)||(!g.getPiece(x2,y2).isWhite()&&y2==0))){
			    System.out.println("Choose a piece for your pawn to upgrade into. The choices are Knight, Rook, Bishop, Queen\n");
			    String upgrade= s.nextLine();
			    g.upgrade(g.getPiece(x2,y2),upgrade);
			}
			System.out.println(g);
			turn++;
		    }else{
			System.out.println(g);
			System.out.println("Illegal move!");
		    }
		}catch(Exception e){
		    e.printStackTrace();
		    System.out.println(g);
		    System.out.println("Invalid move!");
		}
	    }else{
		System.out.println(g);
		System.out.println("Use format:a3 b4");
	    }
	    if(auto){
		delay(1000);
	    }
	    main.refresh();
	}
	System.out.println(g.win());	
    }

}
