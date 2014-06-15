import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Gui extends JFrame implements ActionListener{
    /*
    protected JPanel chessboardmain,piecegridmain, all, options, blackcapture, whitecapture; 
    protected JLayeredPane piecePane;
    private JLabel black, white, turn;
    private JButton newGame;
    JPanel panel=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JPanel panel4=new JPanel();
    JPanel panel5=new JPanel();
    */
    private GameBoard board= new GameBoard();
    private String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private JPanel chessboardmain;

    JFrame frame=new JFrame();
    Color black=new Color(139,69,19);
    Color white=new Color(244,164,96);
    Color now=white;
    Scanner s;
    boolean turn=true;
    boolean auto=false;
    boolean singlePlayMode = false;
    Coordinate start,end;
    Piece p1;
    Piece p2;
    boolean comp = false;
    boolean firstMove = true;
    
    public Gui(String[]args) {
	Object[] possibilities = {"Two-Player Mode", "Single-Player Mode"};
	String k = (String)JOptionPane.showInputDialog(frame, "Choose Your Preferred Mode of Game:","Game Mode",JOptionPane.PLAIN_MESSAGE,null, possibilities,"Two-Player Mode");
	if (k == null){
	    System.exit(0);
	}
	String q = null;
	if (k.equals("Single-Player Mode")){
	    singlePlayMode = true;
	    Object[] pos = {"White", "Black"};
	    q = (String)JOptionPane.showInputDialog(frame, "Choose Your Color:","Color Choice",JOptionPane.PLAIN_MESSAGE,null, pos,"White");
	    if (q.equals("Black"))
		comp = true;
	}
	if (q == null){
	    System.exit(0);
	}
	board =new GameBoard();
	board.initialize();
        initComponents();
	if(args.length>0){
	    try{
		s= new Scanner(new File("Games/"+args[0]));
		auto=true;
		play();
	    }catch(FileNotFoundException e){
		System.out.println("File not found!");
	    }
	}
	if ((comp == true)&&  
	    (singlePlayMode == true)){
	    ChessAI computer = new ChessAI(true,board);
	    Move compMove = computer.bestMove();
	    board.movePiece(compMove.getStart(), compMove.getEnd(), true);
	    refresh();
	    firstMove = false;
	    turn = !turn;
	}
    }
    
    public GameBoard getBoard(){
	return board;
    }
    
    private void initComponents() {
	GridLayout grid1=new GridLayout(1, 8);
	GridLayout grid2=new GridLayout(8, 1);
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JPanel panel4=new JPanel();
	panel1.setLayout(grid1);
	panel2.setLayout(grid2);
	panel3.setLayout(grid1);
	panel4.setLayout(grid2);
	panel1.add(new JPanel());
	panel3.add(new JPanel());	
	chessboardmain = new JPanel();
	for(int y = 0; y< 8; y++){             
	    for(int x = 0;x < 8; x++){
		ImageIcon icon=board.getBoard()[x][7-y].getAvatar();
		board.pattern[x][7-y].setIcon(icon);
		board.pattern[x][7-y].setPreferredSize(new Dimension(75, 75));
		board.pattern[x][7-y].addActionListener(this);
		board.pattern[x][7-y].setBackground(now);
		if(now.equals(white)){
		    now=black;
		}else{
		    now=white;
		}
		chessboardmain.add(board.pattern[x][7-y]);		
	    }
	    if(now.equals(white)){
		now=black;
	    }else{
		now=white;
	    }
	    panel1.add(new JLabel("  "+letters.substring(y,y+1)+"  "));
	    panel2.add(new JLabel(Integer.toString(8-y)));
	    panel3.add(new JLabel("  "+letters.substring(y,y+1)+"  "));
	    panel4.add(new JLabel(Integer.toString(8-y)));
	}
	frame.setTitle("Chess");
	frame.getContentPane().add(BorderLayout.CENTER, chessboardmain);
	frame.getContentPane().add(BorderLayout.NORTH, panel1);
	frame.getContentPane().add(BorderLayout.WEST, panel2);
	frame.getContentPane().add(BorderLayout.SOUTH, panel3);
	frame.getContentPane().add(BorderLayout.EAST, panel4);
	frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	frame.setSize(675, 700);
	frame.setLocation(100, 0);
	frame.setVisible(true);
	frame.setResizable(false);
    }  
    
    public void actionPerformed(ActionEvent event){
	if(board.getdone()){
	    return;
	}
	if (firstMove == true &&
	    comp == true)
	    return;
	for(int x = 0; x< 8; x++){             
	    for(int y = 0;y < 8; y++){
		if (event.getSource()==board.pattern[x][y]){
		    if(start == null ){
			if(!(board.getPiece(x,y) instanceof NullPiece)){   
			    now=board.pattern[x][y].getBackground();
			    board.pattern[x][y].setBackground(Color.GREEN);
			    start=new Coordinate(x,y);
			}
		    }
		    else if(!(board.getPiece(x,y) instanceof NullPiece) && board.getPiece(x, y).isWhite() && turn){ 
			clearBackground();  
			now=board.pattern[x][y].getBackground();
			board.pattern[x][y].setBackground(Color.GREEN);
			start=new Coordinate(x,y);
		    }
		    else if(!(board.getPiece(x,y) instanceof NullPiece) && !(board.getPiece(x, y).isWhite()) && !turn){ 
			clearBackground();  
			now=board.pattern[x][y].getBackground();
			board.pattern[x][y].setBackground(Color.GREEN);
			start=new Coordinate(x,y);
		    }
		    else{
			end=new Coordinate(x,y);
			if(!turn(new Move(start,end))){
			    start=null;
			    end=null;
			}else if(board.getdone()){
			    JOptionPane.showMessageDialog(new JFrame(),board.win());
			}
			clearBackground();
		    }
		}
	    }
	}
    }

    public void clearBackground(){
	for (int x=0; x<8;x++){
	    for(int y=0;y<8;y++){
		if (board.pattern[x][y].getBackground().equals(Color.GREEN)){
		    board.pattern[x][y].setBackground(now);
		}
	    }
	}
    }
    public void delay(int time){
	try {
	    Thread.sleep(time);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }
    public void refresh(){
        chessboardmain.removeAll();
	if(turn){
	    for(int y = 0; y< 8; y++){             
		for(int x = 0;x < 8; x++){	      
		    ImageIcon icon=board.getBoard()[x][7-y].getAvatar();
		    board.pattern[x][7-y].setIcon(icon);
		    board.pattern[x][7-y].addActionListener(this);
		    chessboardmain.add(board.pattern[x][7-y]);
		    
		}
	    }
	}
	else{
	    for(int y = 0; y< 8; y++){             
		for(int x = 0;x < 8; x++){	      
		    ImageIcon icon=board.getBoard()[x][y].getAvatar();
		    board.pattern[x][y].setIcon(icon);
		    board.pattern[x][y].addActionListener(this);
		    chessboardmain.add(board.pattern[x][y]);
		    
		}
	    }
	}
	chessboardmain.validate();
	chessboardmain.repaint();
    }     

    public void refresh2(){
        chessboardmain.removeAll();
	    for(int y = 0; y< 8; y++){             
		for(int x = 0;x < 8; x++){	      
		    ImageIcon icon=board.getBoard()[x][7-y].getAvatar();
		    board.pattern[x][7-y].setIcon(icon);
		    board.pattern[x][7-y].addActionListener(this);
		    chessboardmain.add(board.pattern[x][7-y]);
		}
	    }
	    chessboardmain.validate();
	    chessboardmain.repaint();
    }     

    public boolean turn(Move m){
	p1=board.getPiece(m.getStart());
	p2=board.getPiece(m.getEnd());
	
	if(p1 instanceof NullPiece ||(p1.isWhite()&&!turn)||(!p1.isWhite()&&turn)){
	    return false;
	}else if(p1 instanceof King && p2 instanceof Rook){
	    if(board.castle(turn,p1,p2)){
		turn=!turn;
	    } 
	}else if(board.movePiece(m.getStart(),m.getEnd(),true)){
	    p2=board.getPiece(m.getEnd());
	    if(p2 instanceof Pawn && (p2.gety()==0||p2.gety()==7)){
		String upgrade="";
		if(auto){
		    upgrade=s.nextLine();
		}
		else if (singlePlayMode){
		    upgrade = "Queen";
		}
		else{
		    Object[] possibilities = {"Queen", "Knight", "Rook", "Bishop"};
		    String s = (String)JOptionPane.showInputDialog(frame, "Promote your pawn:","Pawn promotion",JOptionPane.PLAIN_MESSAGE,null, possibilities,"Queen");
		    if ((s != null) && (s.length() > 0)) {
			upgrade=s;
		    }
		}
		board.upgrade(p2,upgrade);
	    }
	    turn=!turn;
	    if(auto){
		delay(300);
		refresh2();
	    }
	    else if (singlePlayMode){
		refresh2();
	    }
	    else{
		refresh();
	    }
	}
	if (singlePlayMode){
	    ChessAI master = new ChessAI(comp,board);
	    Move masterMove = master.bestMove();
	    turn = comp;
	    board.movePiece(masterMove.getStart(), masterMove.getEnd(),true);
	    refresh2();
	    turn = !turn;
	}
	return true;
    }
    //=================================Auto play=============================
    public void play(){
	while(!board.getdone()){
	    String l = "";
	    try {
		l=s.nextLine();
	    }
	    catch (NoSuchElementException e){
		System.out.println("Game is over");
		break;
	    }
	    try{
		int x1=l.charAt(0)-'a';
		int y1=Integer.parseInt(""+l.charAt(1))-1;
		int x2=l.charAt(3)-'a';
		int y2=Integer.parseInt(""+l.charAt(4))-1;
		System.out.println(board.getPiece(x1,y1)+l.substring(l.length()-2) + "\n");
		if(!turn(new Move(new Coordinate(x1,y1),new Coordinate(x2,y2)))){
		    break;
		}
	    }catch(Exception e){
		System.out.println("Invalid move: "+l);
		e.printStackTrace();
		break;
	    }
	}
	System.out.println(board.win());	
    }
    //=================================ChessAI=========================================




}
