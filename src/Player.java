import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements Runnable {
    private boolean isAlive = true;
    private int playerX;
    private int playerY;
    private BufferedImage sprite;
    private int facing = 0;
    private int playerHP = 100;

    public Player(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    @Override
    public void run() {
        init();
        while (isAlive) {
            if (playerHP < 100) {
                isAlive = false;
            }
        }
    }

    public void drawPlayer(Graphics2D g) {
        g.drawImage(sprite,playerX,playerY,96,96,null);
    }

    public void init() {
        try {
            sprite = ImageIO.read(new File("src//Sprites//playerSpriteRIGHT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSpriteDirection(int facing) {
        try {
            if (facing == 1) {
                sprite = ImageIO.read(new File("src//Sprites//playerSpriteRIGHT.png"));
            } else {
                sprite = ImageIO.read(new File("src//Sprites//playerSpriteLEFT.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void movePlayer(int direction) {
        if (direction == 1) {
            loadSpriteDirection(1);
            playerX = playerX+3;
        } else {
            loadSpriteDirection(0);
            playerX = playerX-3;
        }
    }

    public void jumpPlayer() {
        for (int i = 0; i < 170; i++) {
            playerY--;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 170; i++) {
            playerY++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getFacing() {
        return facing;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void Shoot(Graphics2D graphics2D) {
        Bullet bullet = new Bullet(this,graphics2D);
        Thread thread = new Thread(bullet);
        thread.start();
    }
}
