import java.awt.Color;

import javax.swing.*;

public class Gameplay extends JFrame {
  
    
    public static void main(String[] args) {
        JFrame board=new JFrame("Snake Game by MasteOFNothing");
        Setup setup=new Setup();

        board.setBounds(10,10,905,700);
        board.setBackground(Color.DARK_GRAY);
        board.setResizable(false);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
        board.add(setup);


    }
}