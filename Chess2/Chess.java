import java.io.*;
import java.util.*;
public class Chess{
    public static String displayBoard (Piece[][] board){
	//the dimensions of this board are best suited for being run through cmd, if you run it through terminal it will appear longer than wider.
	String game = "      ";
	char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	for (int i = 0; i < letters.length; i++){
	    game += " " + letters[i] + "  ";
	}
	game += "\n\n\n";
	for (int i = 0; i < board.length; i++){
	    String row = i + 1 + "   ";
	    for (int j = 0; j < board[i].length; j++){
		row += board[i][j].getName() + " ";
	    }
	    game += "  " + row + "\n\n\n";
	}
	return game;
    }
    public static void switchPerspective (Piece[][] board){
	//this function allows the player whose turn it is to see the board from his perspective the way it would be in a real-life chess game (switches each turn)
	for (int i = 0; i < board.length/2; i++){
	    Piece[] temp  = board[i];
	    board[i] = board[board.length - 1 - i];
	    board[board.length-1-i] = temp;
	}

    }
    public static boolean move(int k, Piece[][] board, int row1, int col1, int row2, int col2){
	try{
	    if (board[row2][col2] instanceof King){
		return false;
	    }
	    if (k % 2 == 1){
		if (board[row1][col1].getColor() != true){
		    return false;
		}
		if (board[row2][col2].getColor() != false &&
		    board[row2][col2].getActualPiece() != false){
		    return false;
		}
	    }
	    if (k % 2 == 0){
		if (board[row1][col1].getColor() != false ||
		    board[row2][col2].getColor() != true){
		    return false;
		}
	    }
	    boolean can = false;
	    if (board[row1][col1] instanceof Pawn){
		can = Pawn.isValidMove(board, row1, col1, row2, col2);
		if (can == true){
		    if (board[row2][col2].getActualPiece()){
			return false;
		    }
		}
		if (can == false && board[row2][col2].getActualPiece()){
		    can = Pawn.pawnEat(row1,col1,row2,col2);
		}
	    }
	    else if (board[row1][col1] instanceof Rook){
		can = Rook.isValidMove(board, row1, col1, row2, col2);
	    }
	    else if (board[row1][col1] instanceof Bishop){
		can = Bishop.isValidMove(board, row1, col1, row2, col2);
	    }
	    else if (board[row1][col1] instanceof Knight){
		can = Knight.isValidMove(row1, col1, row2, col2);
	    }
	    else if (board[row1][col1] instanceof Queen){
		can = Queen.isValidMove(board, row1, col1, row2, col2); 
	    }
	    else if (board[row1][col1] instanceof King){
		can = King.isValidMove(row1, col1, row2, col2);
	    }
	    else{
		return false;
	    }
	    if (can){
		board[row2][col2] = board[row1][col1];
		board[row1][col1] = new Piece(true, " - ");
	    }
	    else{
		return false;
	    }
	}
	catch(IndexOutOfBoundsException e){
	    return false;
	}
	return true;
    }
    //a function that will make a copy of the board
    public static Piece[][] copyOf (Piece[][] board){
	Piece[][] temp = new Piece[board.length][board[0].length];
	for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board.length; j++){
		temp[i][j] = board[i][j];
	    }
	}
	return temp;
    }
    public static int[] findKing(Piece[][] board, boolean color){
	int[] ans = new int[2];
	for(int i = 0; i < board.length; i++){
	    for (int j = 0; j < board[i].length; j++){
		if (board[i][j] instanceof King &&
		    board[i][j].getColor() == color){
		    ans[0] = i;
		    ans[1] = j;
		}
	    }
	}
	return ans;
    }
    public static void morph(Piece[][] board){
	boolean truth = false;
	int i = 0, j = 0;
	find: {
	    for (; i < board.length; i++){
		for (; j < board[i].length; j++){
		    if (board[i][j] instanceof Pawn){
			if (i == 0 ||
			    i == 7){
			    truth = true;
			    break find;
			}
		    }
		}
	    }
	}
	if (truth){
	    Scanner sc;
	    String response;
	    do{
		System.out.println("What would you like your Pawn to tranform into?\n");
		System.out.println("(Options are <Rook>, <Knight>, <Bishop>, <Queen>)\n");
		sc = new Scanner(System.in);
		response = sc.nextLine();
	    }
	    while(!response.equals("Rook")&&
		  !response.equals("Knight")&&
		  !response.equals("Bishop")&&
		  !response.equals("Queen"));
	    String name = "";
	    if (board[i][j].getColor()){
		name += "W";
	    }
	    else{
		name += "B";
	    }
	    if (response.equals("Rook")){
		name += "R ";
		board[i][j] = new Rook(board[i][j].getColor(), name);
	    }	
	    else if (response.equals("Bishop")){
		name += "B ";
		board[i][j] = new Bishop(board[i][j].getColor(), name);
	    }	
	    else if (response.equals("Knight")){
		name += "K ";
		board[i][j] = new Knight(board[i][j].getColor(), name);
	    }	
	    else if (response.equals("Queen")){
		name += "Q ";
		board[i][j] = new Queen(board[i][j].getColor(), name);
	    }	
	}
    }
    public static boolean inCheck(int k, Piece[][] board){
	boolean a;
	if (k % 2 == 1){
	    a = true;
	}
	else{
	    a = false;
	}
	for(int i = 0; i < board.length; i++){
	    for (int j = 0; j < board[i].length; j++){
		if (board[i][j].getActualPiece() == true &&
		    board[i][j].getColor() == a){
		    int row1 = i;
		    int col1 = j;
		    int[] temp = findKing(board, !a);
		    int row2 = temp[0];
		    int col2 = temp[1];
		    boolean check = false;
		    if (board[row1][col1] instanceof Pawn){
			check = Pawn.pawnEat(row1, col1, row2, col2);
		    }
		    if (check){
			return true;
		    }
		    else if (board[row1][col1] instanceof Rook){
			check = Rook.isValidMove(board, row1, col1, row2, col2);
		    }
		    if (check){
			return true;
		    }
		    else if (board[row1][col1] instanceof Bishop){
			check = Bishop.isValidMove(board, row1, col1, row2, col2);
		    }
		    if (check){
			return true;
		    }
		    else if (board[row1][col1] instanceof Knight){
			check = Knight.isValidMove(row1, col1, row2, col2);
		    }
		    if (check){
			return true;
		    }
		    else if (board[row1][col1] instanceof Queen){
			check = Queen.isValidMove(board, row1, col1, row2, col2); 
		    }
		    if (check){
			return true;
		    }
		}
	    }
	}
	return false;
    }
    public static boolean checkmate(int r, Piece[][] board){
	if (inCheck(r,board)){
	    for (int i = 0; i < board.length; i++){
		for (int j = 0; j < board[i].length; j++){
		    for (int k = 0; k < board.length; k++){
			for (int l = 0; l < board[k].length; l++){
			    Piece[][] temp = copyOf(board);
			    if(move(r+1, temp, i,j,k,l)){
				if (!inCheck(r,temp)){
				    return false;
				}
			    }
			}
		    }
		}
	    }
	}
	if (!inCheck(r,board)){
	    return false;
	}
	return true;
    }
    
    //a function that tests to see if the user input if correctly formatted
    public static boolean correctFormat(String response){
	if (response.length() != 5){
	    return false;
	}
	boolean first = (int)(response.charAt(0)) >= (int)'A' && (int)(response.charAt(0)) <= (int)'H';
	boolean second = (int)(response.charAt(1)) >= (int)'1' && (int)(response.charAt(1)) <= (int)'8';
	boolean third = response.charAt(2) == ' ';
	boolean fourth = (int)(response.charAt(3)) >= (int)'A' && (int)(response.charAt(3)) <= (int)'H';
	boolean fifth = (int)(response.charAt(4)) >= (int)'1' && (int)(response.charAt(4)) <= (int)'8';
	return first && second && third && fourth && fifth;
    }
    public static void main(String[] args){
	String response;
	System.out.println("\n\n\nWELCOME TO CHESS TITANS!!!\n\nMade by Devendra Shivraj and David Lee\n\n\nWould you like to view the Instruction Manual or play a Game?\n");
	do{
	    System.out.println("(Options are <Instruction Manual> or <Game>)\n");
	    Scanner sc = new Scanner(System.in);
	    response = sc.nextLine();
	}
	while(!(response.equals("Instruction Manual")) &&
	      !(response.equals("Game")));
	if (response.equals("Instruction Manual")){
	    try{
		File f = new File("manual.txt");
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()){
		    System.out.println(sc.nextLine());
		}
	    }
	    catch (Exception FileNotFoundException){
		//this is something that should only get printed if the file is in the wrong place
		System.out.println("Note to Programmer: manual.txt file not in correct directory");
	    }
	}
	else{
	    String summary = "";
	    //creating the game board
	    Piece[][] board = new Piece[8][8];
	    //black pieces
	    board[0][0] = new Rook(false, "BR ");
	    board[0][1] = new Knight(false, "BK ");
	    board[0][2] = new Bishop(false, "BB ");
	    board[0][3] = new Queen(false, "BQ ");
	    board[0][5] = new Bishop(false, "BB ");
	    board[0][6] = new Knight(false, "BK ");
	    board[0][7] = new Rook(false, "BR ");
	    board[0][4] = new King(false, "BK-");
	    //white pieces
	    board[7][0] = new Rook(true, "WR ");
	    board[7][1] = new Knight(true, "WK ");
	    board[7][2] = new Bishop(true, "WB ");
	    board[7][3] = new Queen(true, "WQ ");
	    board[7][5] = new Bishop(true, "WB ");
	    board[7][6] = new Knight(true, "WK ");
	    board[7][7] = new Rook(true, "WR ");
	    board[7][4] = new King(true, "WK-");
	     for (int i = 1; i < board.length - 1; i++){
		for (int j = 0; j < board[i].length; j++){
		    if (i == 1){
			//black pawns
			board[i][j] = new Pawn(false,"BP ", true);
		    }
		    else if (i == 6){
			//white pawns
			board[i][j] = new Pawn(true, "WP ",true);
		    }
		    else{
			board[i][j] = new Piece(true, " - ");
			//it's just a placeholder, color is unimportant
		    }
		}
	    }
	    int i = 1;
	    summary += displayBoard(board);
	    System.out.println("\n" + displayBoard(board));
	    while (!checkmate(1,board) &&
		   !checkmate(2,board)){
		do{
		    if (i % 2 == 1){
			System.out.println("It's white's turn\n");
		    }
		    if (i % 2 == 0){
			System.out.println("It's black's turn\n");
		    }
		    //Once a piece has been moved (or the game has started) the player whose turn it is will be notified
		    System.out.println("(Proper format is location of piece <Letter><Number> and endspot <Letter><Number>)\n");
		    Scanner sc = new Scanner (System.in);
		    response = sc.nextLine();
		    Piece[][] tiger = copyOf(board);
		    //the function of "tiger" is to check if moving the piece will put the player whose turn it is in check (if so, then it's not a valid move)
		    if (correctFormat(response)){
			boolean truth = false;
			move(i, tiger,Integer.parseInt(response.charAt(1)+"")-1,(int)(response.charAt(0))-(int)'A',Integer.parseInt(response.charAt(4)+"")-1,(int)(response.charAt(3))-(int)'A');
			if (!inCheck(i+1, tiger)){
			    truth = move(i,board,Integer.parseInt(response.charAt(1)+"")-1,(int)(response.charAt(0))-(int)'A',Integer.parseInt(response.charAt(4)+"")-1,(int)(response.charAt(3)) - (int)'A');
			    morph(board);
			    if (board[Integer.parseInt(response.charAt(4) + "") - 1][(int)(response.charAt(3)) - (int)'A'] instanceof Pawn){
				((Pawn)(board[Integer.parseInt(response.charAt(4) + "") - 1][(int)(response.charAt(3)) - (int)'A'])).setFirstMove(false);
			    }
			    if (inCheck(i,board)){
				if (checkmate(i, board)){
				    break;
				}
				switchPerspective(board);
				String str,r;
				if (i % 2 == 1){
				    str = "Black";
				}
				else{
				    str = "White";
				}
				Piece[][] temp = null;
				//the purpose of temp, is to force the player who's in check to make a move that will get themself out of check
				do{
				    System.out.println(str + " Player is in Check\n");
				    System.out.println("\n" + displayBoard(board));
				    System.out.println("(Proper format is location of piece <Letter><Number> and endspot <Letter><Number>)\n");
				    Scanner s = new Scanner(System.in);
				    r = s.nextLine();
				    if (correctFormat(r)){
					temp = copyOf(board);
					move(i + 1, temp, Integer.parseInt(r.charAt(1) + "") - 1,(int)(r.charAt(0)) - (int)'A',Integer.parseInt(r.charAt(4) + "")-1,(int)(r.charAt(3))-(int)'A');
				    }
				}
				while (!correctFormat(r) || inCheck(i,temp));
				if(move(i+1, board,Integer.parseInt(r.charAt(1) + "") - 1,(int)(r.charAt(0)) - (int)'A',Integer.parseInt(r.charAt(4) + "") - 1,(int)(r.charAt(3)) - (int)'A')){
				    summary += displayBoard(board);
				}
				morph(board);
				if (board[Integer.parseInt(r.charAt(4) + "") - 1][(int)(r.charAt(3)) - (int)'A'] instanceof Pawn){
				    ((Pawn)(board[Integer.parseInt(r.charAt(4) + "") - 1][(int)(r.charAt(3)) - (int)'A'])).setFirstMove(false);
				}
				i++;
			    }
			    if (truth){
				i++;
				summary += displayBoard(board);
				switchPerspective(board);
			    }
			}
		    }
		    System.out.println("\n" + displayBoard(board));
		}
		while (!correctFormat(response));
	    }
	    String str = "",s = "";
	    if (!checkmate(2,board)){
		str += "Black";
		s += "White";
	    }
	    else{
		str += "White";
		s += "Black";
	    }
	    switchPerspective(board);
	    System.out.println(displayBoard(board)+"\n\n"+str + " Player is in Checkmate\n\n\n\nCongratulations " + s + " Player!!! You have won the Game!!!\n\n" );
	    Scanner end;
	    String res;
	    do{
		System.out.println("Would you like to see a summary of the game");
		System.out.println("\n(Options are <yes> or <no>)\n");
		end = new Scanner(System.in);
		res = end.nextLine();
	    }
	    while(!res.equals("yes") &&
		  !res.equals("no"));
	    if (res.equals("yes")){
		System.out.println(summary + displayBoard(board));
	    }
	}
    }
}
