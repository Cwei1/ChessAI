import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

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

 
    public Gui(GameBoard g) {
	board =g;
	board.initialize();
        initComponents();
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
	for(int x = 0; x< 8; x++){             
	    for(int y = 0;y < 8; y++){
		if (event.getSource()==board.pattern[x][y]){
		    clearBackground();
		    now=board.pattern[x][y].getBackground();
		    board.pattern[x][y].setBackground(Color.GREEN);
		    JFrame box=new JFrame();
		    JOptionPane.showMessageDialog(box, "Hey, it's green!");
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

    public void refresh(){
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
    
}
