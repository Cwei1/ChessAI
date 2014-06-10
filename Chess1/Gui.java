import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener{
    private GameBoard chessBoard;

    JPanel panel=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JPanel panel4=new JPanel();
    JPanel panel5=new JPanel();

    JFrame frame=new JFrame();

    public String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    GameBoard g= new GameBoard();
    
    Color back;
 
    public Gui() {
	g.initialize();
        initComponents(g);
    }
    
    public GameBoard retBoard(){
	return g;
    }
                      
    private void initComponents(GameBoard g) {

	GridLayout grid2=new GridLayout(1, 8);
	GridLayout grid3=new GridLayout(8, 1);

	panel2.setLayout(grid2);
	panel3.setLayout(grid3);
	panel4.setLayout(grid2);
	panel5.setLayout(grid3);
	
	panel2.add(new JPanel());
	panel4.add(new JPanel());
	
	chessboardmain = new JPanel();
        piecegridmain = new JPanel();

	for(int x = 0; x< 8; x++){             
	    for(int y = 0;y < 8; y++){
	      
		ImageIcon icon=g.getBoard()[y][x].getAvatar();
		g.pattern[y][x].setIcon(icon);
		g.pattern[y][x].setPreferredSize(new Dimension(80, 80));
		g.pattern[x][y].addActionListener(this);
		back=g.pattern[x][y].getBackground();
		chessboardmain.add(g.pattern[y][x]);
		
	    }
	    panel2.add(new JLabel(Integer.toString(x+1)));
	    panel3.add(new JLabel("  "+letters.substring(x, x+1)+"  "));
	    panel4.add(new JLabel(Integer.toString(x+1)));
	    panel5.add(new JLabel("  "+letters.substring(x,x+1)+"  "));
	}

	
	frame.setTitle("Chess");
    	frame.getContentPane().add(BorderLayout.CENTER, chessboardmain);
	frame.getContentPane().add(BorderLayout.NORTH, panel2);
	frame.getContentPane().add(BorderLayout.WEST, panel3);
	frame.getContentPane().add(BorderLayout.SOUTH, panel4);
	frame.getContentPane().add(BorderLayout.EAST, panel5);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setSize(750, 750);
	frame.setLocation(100, 100);
	frame.setVisible(true);
	frame.setResizable(false);
    }  

    public void actionPerformed(ActionEvent event){
	for(int x = 0; x< 8; x++){             
	    for(int y = 0;y < 8; y++){
		if (event.getSource()==g.pattern[x][y]){
		    clearBackground();
		    g.pattern[x][y].setBackground(Color.GREEN);
		    JFrame box=new JFrame();
		    JOptionPane.showMessageDialog(box, "Hey, it's green!");
		}
	    }
	}
    }

    public void clearBackground(){
	for (int x=0; x<8;x++){
	    for(int y=0;y<8;y++){
		if (g.pattern[x][y].getBackground().equals(Color.GREEN)){
		    g.pattern[x][y].setBackground(back);
		}
	    }
	}
    }

    public void refresh(GameBoard g){
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
		piecegridmain.removeAll();
	    }
	}
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
		ImageIcon icon=g.getBoard()[x][8-y].getAvatar();
		g.pattern[x][y].setIcon(icon);
		chessboardmain.add(g.pattern[x][8-y]);
	    }
	}
	piecegridmain.validate();
	piecegridmain.repaint();
    }     
    protected JPanel chessboardmain,piecegridmain, all, options, blackcapture, whitecapture; 
    protected JLayeredPane piecePane;
    private JLabel black, white, turn;
    private JButton newGame;
}
