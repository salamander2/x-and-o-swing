import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe2{

	final static int XX = -1;
	final static int OO = 1;	//capital o, not zero
	
	int player = XX;				//whose turn it is
	int xwins = 0;
	int owins = 0;

	int[][] board = new int[3][3];
	int bx,by;					//location of mouse click (on board)
	
	JLabel lblStart;
	JLabel[] grid;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				new Tictactoe2(); 
			}
		});
	}


	Tictactoe2() {

		JFrame frame = new JFrame("TicTacToe");			
		Container content = frame.getContentPane();
		content.setBackground(Color.BLUE);
		content.setLayout(new BorderLayout(2,2));
		
		//set up the information on the top panel
		JPanel topPanel = new JPanel();		   
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));    
		topPanel.setBackground(new Color(255,255,222));
		lblStart = new JLabel();		
		lblStart.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=X");
		lblStart.setAlignmentX(0.5f);
		topPanel.add(lblStart);
		content.add(BorderLayout.NORTH, topPanel);
		
		//set up the buttons and button panel 
		JButton btnNext = new JButton("Help");
		JButton btnExit = new JButton("Exit");
		JButton btnRestart = new JButton("Restart");
		JPanel btnPanel = new JPanel(); // holds btnPanel at SOUTH
		BtnListener bl = new BtnListener();
		btnExit.addActionListener(bl);
		btnNext.addActionListener(bl);
		btnRestart.addActionListener(bl);
		btnPanel.add(btnNext);
		btnPanel.add(btnExit);
		btnPanel.add(btnRestart);
		btnPanel.setBackground(Color.GRAY);
		content.add(BorderLayout.SOUTH, btnPanel);
		

		//set up the grid panel in the middle of the screen (borderlayout.center)
		JPanel gridPanel = new JPanel(new GridLayout(3,3,3,3));
		gridPanel.setBackground(Color.RED);

		grid = new MyLabel[9]; 	
		for (int i=0; i<9; i++){
			grid[i] = new MyLabel(i);
			gridPanel.add(grid[i]);
		}		
		content.add(BorderLayout.CENTER, gridPanel);
		
		//finish setting up the frame
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(500, 450);
		frame.setLocationRelativeTo(null);  //must be AFTER setSize
		frame.setVisible(true);
	}

		
	class GridListener extends MouseAdapter{

		int gridLoc;
		
		public GridListener(int gridLoc){
			this.gridLoc = gridLoc;
		}

		public void mouseClicked(MouseEvent e){			
			bx = gridLoc%3;
			by = gridLoc/3;
			
			if (! emptySquare()) return;
			drawXO(gridLoc);
			checkWin();
		}
	}
	
	boolean emptySquare() {
		if (board[bx][by] == 0) return true;
		return false;
	}
	
	void drawXO(int loc) {
		board[bx][by] = player;
		
		if (player == OO) {
			grid[loc].setText("O");
			lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=X");
			player = XX;
		}
		else {			
			grid[loc].setText("X");
			lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=O");
			player = OO;
		}		
	}	
		
	public void checkWin(){
				
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
			restart();
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
			restart();
			return;
		}

	}
	

	
	private class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e){			
			if (e.getActionCommand() == "Exit") exitPgm();
			if (e.getActionCommand() == "Help") showHelp();
			if (e.getActionCommand() == "Restart") restart();
		}
	}
	
	void exitPgm() {
		String str = "Thanks for playing";
		JOptionPane.showMessageDialog(null, str, "Goodbye, Exitting program", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	//this could be a separate JFrame that pops up, but it would have to be modal.
	void showHelp() {
		String str = "Welcome to X's and O's\n";
		str = str + "Click on a square to start playing";				
		JOptionPane.showMessageDialog(null, str, "HELP", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void restart() {
		for (int i=0; i<9; i++){
			grid[i].setText(" ");
		}
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				board[i][j] = 0;
			}
		}
		lblStart.setText("Score: X=" + xwins + "   O=" + owins + "      Turn=" + (player==XX ? "X" : "O"));
	}
		
	
	private class MyLabel extends JLabel {
		
		MyLabel(int n) {
			super(" ");
			this.setFont(new Font("Dialog", Font.PLAIN, 84));
			this.setBackground(Color.WHITE);			
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setOpaque(true);
			this.addMouseListener(new GridListener(n));
		}
	}
	
}
