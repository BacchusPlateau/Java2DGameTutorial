package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int ORIGINAL_TILE_SIZE = 16;   // 16x16
    final int SCALE = 3; //so we can look good on a large res monitor
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;   //48x48 tile
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;  //768 px
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; //576 px
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/(FPS*1.0);
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            update();
            repaint();  //this will call paintComponent()

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;  //convert to milliseconds

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();

    }



}
