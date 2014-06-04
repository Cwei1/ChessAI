import java.util.*;
import java.io.*;

public class Run{
    public static void main(String[]args){
	GameBoard g= new GameBoard(8,8);
	g.initialize();
	Gui main = new Gui(g);
	main.setVisible(true);
	int turn=0;
	String response = "";
	do{
	    System.out.println("Would you like to play a game,or see one played? options are: <play> and <auto>");
	    Scanner sc = new Scanner(System.in);
	    response = sc.nextLine();
	}
	while(!(response.equals("play")) &&
	      !(response.equals("auto")));
	Scanner s = null;
	if (response.equals("play")){
	    s=new Scanner(System.in);
	}
	else{
	    try{
		s = new Scanner(new File("autoMoves.txt"));
	    }
	    catch (FileNotFoundException e){}
	}
	
	System.out.println(g);
	System.out.println("How to play: use the coordinate system to move the pieces just like normal chess.Except you only have 3 chances to get out of check and the there will be no hints. if after 3 'tries you're still in check' you lose!");
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
	    }
	    catch (NoSuchElementException e){
		System.out.println("Game is over");
		break;
	    }
	    if(l.equals("Castle")){
		if(x.equals("White")){
		    if(!g.castle(true)){
			System.out.println(g);
			System.out.println("Illegal Move!");
		    }else{
			System.out.println(g);
			turn++;
		    }
		}else{
		    if(!g.castle(false)){
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
		    }else if(g.movePiece(x1,y1,x2,y2)){
			System.out.println(g);
			if(g.inCheckW()){
			    System.out.println("Check!");
			}else if(g.inCheckB()){
			    System.out.println("Check!");
			}
			turn++;
		    }else{
			System.out.println(g);
			System.out.println("Illegal move!");
		    }
		}catch(Exception e){
		    System.out.println(g);
		    System.out.println("Invalid move!");
		}
	    }else{
		System.out.println(g);
		System.out.println("Use format:a3 b4");
	    }
		main.refresh(g);
	}
	System.out.println(g.win());	
    }

}
