import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import GamePanelpk.*;
import KeyHandelerpk.*;
public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    
        
    }
}