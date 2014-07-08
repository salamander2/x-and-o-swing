import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Tictactoe3 {

	//constants
	final static int SIZE = 3;		//size of board & grid
	/*** you can set this to any size, but the winning only works for the top 3x3 corner ***/
	
	final static int XX = -1;
	final static int OO = 1;	//capital o, not zero
	final static Color COLOURGRID = new Color(140, 140,140);	
	final static Color COLOURBACK = new Color(240, 240, 240);
	
	//global variables
	int player = XX;				//whose turn it is
	int xwins = 0;
	int owins = 0;

	int[][] board = new int[SIZE][SIZE];
	JLabel lblStart = new JLabel();		//must be created & initialized here to avoid nullPointer error in initGame().
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new Tictactoe3(); }
		});
	}
	
	public Tictactoe3() {	//constructor
		initGame();
		createAndShowGUI();
	}
	
	void initGame() {		
		//clear board
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				board[i][j]=0;
			}
		}
		lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=" + (player==XX ? "X" : "O"));
	}
	
	void createAndShowGUI() {
		JFrame frame = new JFrame("TicTacToe");			
		Container content = frame.getContentPane();
		content.setBackground(Color.BLUE);
		
		//setup top label
		content.setLayout(new BorderLayout(2,2));				
		lblStart.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=X");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBackground(new Color(255,255,222));
		lblStart.setOpaque(true);		
		content.add(BorderLayout.NORTH, lblStart);
		
		//make main panel
		DrawingPanel gridPanel = new DrawingPanel();
		content.add(gridPanel, BorderLayout.CENTER);
		
		//finish setting up the frame
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(500, 450);		
		frame.setLocationRelativeTo(null);  //must be AFTER setSize
		//frame.pack();	//uncomment with lines #A#
		frame.setVisible(true);
		
		//Once the panel is visible, initialize the graphics. 
		//*** This is no longer needed here since it's at the beginning of paintComponent()
		//gridPanel.initGraphics();
		
	}
	
	private class DrawingPanel extends JPanel implements MouseListener{

		//instance variables
		int jpanW, jpanH;	//size of JPanel
		int blockX, blockY;	//size of each square
		int spc = 6;		//spacing around X and O
		
		DrawingPanel() {
			this.addMouseListener(this);
			setBackground(COLOURBACK);
			//this.setPreferredSize(new Dimension(SIZE*50, SIZE*50)); 	//uncomment with lines #A#
			
			//** Because the panel size variables don't get initialized until the panel is displayed,
			//** we can't do a lot of graphics initialization here in the constructor.
		}
		
		//** Called by createAndShowGUI()
		void initGraphics() {
			jpanW = this.getSize().width;		
			jpanH = this.getSize().height;	
			blockX = (int)((jpanW/SIZE)+0.5);
			blockY = (int)((jpanH/SIZE)+0.5);			
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g); //needed for background colour to paint
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			initGraphics(); //needed if the window is resized.
			
			//Draw grid
			g.setColor(COLOURGRID);			
			for (int i=0;i<SIZE;i++) {
				g.drawLine(blockX*i,0,blockX*i,jpanH);
				g.drawLine(0,blockY*i,jpanW,blockY*i);
			}
			
			g.setColor(Color.RED);
			g2.setStroke(new BasicStroke(2));
			//draw all X and Os
			for (int i=0;i<SIZE;i++) {
				for (int j=0;j<SIZE;j++) {
					if (board[i][j] == XX) {
						g.drawLine(i*blockX +spc, j*blockY +spc, (i+1)*blockX -spc, (j+1)*blockY -spc);
						g.drawLine(i*blockX +spc, (j+1)*blockY -spc, (i+1)*blockX -spc, j*blockY +spc);
					}
					if (board[i][j] == OO) {
						g.drawOval(i*blockX +spc, j*blockY +spc, blockX -2*spc, blockY -2*spc);
					}
				}
			}	
		}
		
		boolean emptySquare(int bx, int by) {
			if (board[bx][by] == 0) return true;
			return false;
		}
		
		void drawXO(int bx, int by) {
			board[bx][by] = player;
			
			if (player == OO) {				
				lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=X");
				player = XX;
			}
			else {				
				lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=O");
				player = OO;
			}
			this.repaint();
		}	
		
		void checkWin(){
			
			//check for a win
			//check rows
			int win = 0;
			int total = 0;
			for (int i=0; i<3; i++) {				
				total = board[i][0] + board[i][1] + board[i][2];
				if (total == 3) win = OO;
				if (total == -3) win = XX;
				total = 0;
			}
		
			//check columns			
			for (int i=0; i<3; i++) {				
				total = board[0][i] + board[1][i] + board[2][i];
				if (total == 3) win = OO;
				if (total == -3) win = XX;
				total = 0;
			}
						
			//check diagonals
			total = board[0][0] + board[1][1] + board[2][2];
			if (total == 3) win = OO;
			if (total == -3) win = XX;
			
			total = board[0][2] + board[1][1] + board[2][0];
			if (total == 3) win = OO;
			if (total == -3) win = XX;
			
			//handle winner
			if (win != 0) {		
				if (win == XX) xwins++;
				if (win == OO) owins++;			
				lblStart.setText("Score: X=" + xwins + "   O=" + owins);
				String str = (win==XX ? "X " : "O ") + "wins";
				JOptionPane.showMessageDialog(null, str, "WINNER!", JOptionPane.WARNING_MESSAGE);
				initGame();
				return;
			}
			
			//check for tie game
			boolean tie = true;
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					if (board[i][j] == 0) tie = false;
				}
			}
			if (tie) {
				JOptionPane.showMessageDialog(null, "Tie Game", "OOPS!", JOptionPane.INFORMATION_MESSAGE);
				initGame();
				return;
			}
		}
		
		/* merged with initGame	
		void restart() {		
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					board[i][j] = 0;
				}
			}
			lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=" + (player==XX ? "X" : "O"));
		}
		*/

		//******************* MouseListener methods *****************//
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			//calculate which square you clicked on
			int i = (int) x/blockX;
			int j = (int) y/blockY;	
			//System.out.println("You clicked "+ i + " " + j);
			
			if (! emptySquare(i,j)) return;
			drawXO(i,j);
			checkWin();
			this.repaint();
		}		
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	} //end of DrawingPanel class

}
