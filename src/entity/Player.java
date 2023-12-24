package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";  //TODO replace with enum

    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_up_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_left_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Walking sprites/boy_down_2.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if(keyHandler.downPressed || keyHandler.leftPressed || keyHandler.upPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }

            if (y < 0)
                y = 0;
            if (x < 0)
                x = 0;
            if (x >= gp.SCREEN_WIDTH - gp.TILE_SIZE)
                x = gp.SCREEN_WIDTH - gp.TILE_SIZE;
            if (y >= gp.SCREEN_HEIGHT - gp.TILE_SIZE)
                y = gp.SCREEN_HEIGHT - gp.TILE_SIZE;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {

       // g2.setColor(Color.white);
       // g2.fillRect(x,y, gp.TILE_SIZE, gp.TILE_SIZE);

        BufferedImage image = null;
        Boolean one = spriteNumber == 1;

        switch (direction) {
            case "up":
                image = one ? up1 : up2;
                break;
            case "down":
                image = one ? down1 : down2;
                break;
            case "left":
                image = one ? left1 : left2;
                break;
            case "right":
                image = one? right1 : right2;
                break;
        }

        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);


    }





}
