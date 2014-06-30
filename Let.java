import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe{

    public static final String START1 = "Welcome to X's and O's";
    public static final String START2 = "Click on a star to begin the game as X";
    public static final String START3 = "The next person to take their turn will be O";
    JLabel start = new JLabel(START1);
    public int trackStart = 1;
    
    public String player = "x";
    public boolean tutDone = false; //whether or not tutorial is finished
    
    public int xwins = 0;
    public int owins = 0;
    
    JLabel[] grid;

    public static void main(String[] args){
    
        Tictactoe gui = new Tictactoe();
        gui.go();
    
    }
    
    public void go(){
    
        JFrame frame = new JFrame();
    
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel MainPanel = new JPanel(); //CENTER
        
        JPanel buttons = new JPanel(); // holds buttons at SOUTH
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        
        
        JButton next = new JButton("Next");
        JButton exit = new JButton("Exit");
        JButton restart = new JButton("Restart");
        
        buttons.add(next);
        buttons.add(exit);
        buttons.add(restart);
        
        exit.addActionListener(new ExitListener());
        next.addActionListener(new NextListener());
        restart.addActionListener(new RestartListener());
        
        grid = new JLabel[9];  
        frame.getContentPane().add(BorderLayout.CENTER, MainPanel);   
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));    
           
        MainPanel.add(panel);
        MainPanel.add(panel2);
        MainPanel.add(panel3);
        MainPanel.add(start);
                
        for (int i=0; i<9; i++){
        
            grid[i] = new JLabel("*");
            grid[i].setFont(new Font("Dialog", Font.PLAIN, 84));
            
            if (i < 3) panel.add(grid[i]);
            else if (i < 6) panel2.add(grid[i]);
            else panel3.add(grid[i]);
            
            grid[i].addMouseListener(new GridListener(i));
         
        }
        
        start.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        frame.setSize(500, 450);
        frame.setVisible(true);
    
    }
    
    class GridListener extends MouseAdapter{
    
        int gridLoc;
        
        public void mouseClicked(MouseEvent e){
        
            System.out.println(gridLoc + " clicked " + grid[gridLoc].getText());
            
            if (tutDone){
            
                if (checkPlace(gridLoc)){
            
                    grid[gridLoc].setText(player);
                
                    if (player == "x") player = "o";
                    else player = "x";
                    
                }
                
           } else start.setText("You must complete the tutorial first");
           

               if ((grid[0].getText() == grid[1].getText()) && (grid[0].getText() == grid[2].getText()) && (grid[0].getText() != "*" && grid[1].getText() != "*" && grid[2].getText() != "*")) win(grid[0].getText());
               else if ((grid[3].getText() == grid[4].getText()) && (grid[3].getText() == grid[5].getText()) && (grid[3].getText() != "*" && grid[4].getText() != "*" && grid[5].getText() != "*")) win(grid[3].getText());
               else if ((grid[6].getText() == grid[7].getText()) && (grid[6].getText() == grid[8].getText()) && (grid[6].getText() != "*" && grid[7].getText() != "*" && grid[8].getText() != "*")) win(grid[6].getText());
               else if ((grid[0].getText() == grid[3].getText()) && (grid[0].getText() == grid[6].getText()) && (grid[0].getText() != "*" && grid[3].getText() != "*" && grid[6].getText() != "*")) win(grid[0].getText());
               else if ((grid[1].getText() == grid[4].getText()) && (grid[1].getText() == grid[7].getText()) && (grid[1].getText() != "*" && grid[4].getText() != "*" && grid[7].getText() != "*")) win(grid[1].getText());
               else if ((grid[2].getText() == grid[5].getText()) && (grid[2].getText() == grid[8].getText()) && (grid[2].getText() != "*" && grid[5].getText() != "*" && grid[8].getText() != "*")) win(grid[2].getText());
               else if ((grid[0].getText() == grid[4].getText()) && (grid[0].getText() == grid[8].getText()) && (grid[0].getText() != "*" && grid[4].getText() != "*" && grid[8].getText() != "*")) win(grid[0].getText());
               else if ((grid[2].getText() == grid[4].getText()) && (grid[2].getText() == grid[6].getText()) && (grid[2].getText() != "*" && grid[4].getText() != "*" && grid[6].getText() != "*")) win(grid[2].getText());
           }
        
           
        public GridListener(int gridLoca){
        
            gridLoc = gridLoca;
        
        }
        
        public boolean checkPlace(int gridPlace){
        
            if (grid[gridPlace].getText() == "*") return true;
            
            else return false;
        
        }
        
        public void win(String player){
        
            if (player == "x") xwins++;
            else owins++;
            
            for (int i=0; i<9; i++){
            
             grid[i].setText("*");
             
            }
            
            start.setText("X - " + xwins + " O - " + owins);
            
        }
    
    
    }
    
    class ExitListener implements ActionListener{
    
        public void actionPerformed(ActionEvent e){
        
            System.exit(0);
        
        }
    
    }
    
    class NextListener implements ActionListener{
    
        public void actionPerformed(ActionEvent e){
        
            if (trackStart == 1){
                start.setText(START2);
                trackStart++;

            }
            
            else if (trackStart == 2){
            
                start.setText(START3);
                trackStart++;
           }
            else {
            
                start.setText("X - " + xwins + " O - " + owins);
                tutDone = true;
                trackStart++;
        }   
    
    }
    
    
    
    

   }
   
   class RestartListener implements ActionListener{
   
       public void actionPerformed(ActionEvent e){
       
           for (int i=0; i<9; i++){
               grid[i].setText("*");
           
           }
       
       }
   
   }
   
   
}
