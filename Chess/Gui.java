import javax.swing.*;
import java.awt.*;

public class Gui extends javax.swing.JFrame {
    private GameBoard chessBoard;

    public Gui(GameBoard g) {
        initComponents(g);
    }
                      
    private void initComponents(GameBoard g) {
	chessboardmain = new JPanel();
        piecegridmain = new JPanel();
	options = new JPanel();
	all = new JPanel();
	piecePane = new JLayeredPane();
        whitecapture = new javax.swing.JPanel();
        blackcapture = new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(800, 600));
        setName("main");
	chessboardmain.setMaximumSize(new Dimension(578, 578));
        chessboardmain.setMinimumSize(new Dimension(578, 578));
        chessboardmain.setLayout(new GridLayout(8, 8));
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
	       chessboardmain.add(g.pattern[x][y]);
	    }
	}
        piecegridmain.setMaximumSize(new Dimension(578, 578));
        piecegridmain.setMinimumSize(new Dimension(578, 578));
        piecegridmain.setLayout(new GridLayout(8, 8));
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
			piecegridmain.add(g.getBoard()[x][y].getAvatar());
	    }
	}

	piecegridmain.setOpaque(false);
	all.setMaximumSize(new Dimension(578, 578));
        all.setMinimumSize(new Dimension(578, 578));
        all.setLayout(new BorderLayout());
	piecePane.add(chessboardmain, new Integer(0));
	piecePane.add(piecegridmain, new Integer(100));
        piecegridmain.setBounds( 0, 0, 578, 578 ); 
	chessboardmain.setBounds( 0, 0,  578, 578 );
	all.add(piecePane, BorderLayout.CENTER);
	
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
				 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				 .addGroup(layout.createSequentialGroup()
					   .addContainerGap()
					   .addComponent(all, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
				 .addContainerGap())
        );
	    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
	        .addContainerGap()
               .addComponent(all, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
		); 
        pack();
    }       
    public void refresh(GameBoard g){
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
		piecegridmain.removeAll();
	    }
	}
	for(int y = 0; y < 8; y++){             
	    for(int x = 0; x < 8; x++){
		piecegridmain.add(g.getBoard()[x][y].getAvatar());
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
