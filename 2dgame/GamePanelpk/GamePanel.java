package GamePanelpk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import KeyHandelerpk.*;
import Entitypk.*;

import java.awt.Graphics;
public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screeWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    final int fps = 60;
    KeyHandeler keyH = new KeyHandeler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    //set player default position
    int playerX= 100;
    int playerY = 100;
    int playerSpeed = 4;

    
    public GamePanel(){

        this.setPreferredSize(new Dimension(screeWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
   /*  public void run(){

        double drawInterval = 1000000000/fps; // 0.016666
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            
            //1 update: update information such as character position
            update();

            //2 draw: draw the screen with the updated information.
            repaint();  
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            

        }
        
    }*/
    public void run(){
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                
            update();
            repaint();
            delta--;
            drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}