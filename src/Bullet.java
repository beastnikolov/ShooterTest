import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet implements Runnable {
    private Player player;
    private int bulletX;
    private int bulletY;
    private boolean collided = false;
    private BufferedImage bulletSprite;
    private Graphics2D graphics2D;

    public Bullet(Player player,Graphics2D graphics2D) {
        this.player = player;
        this.bulletX = player.getPlayerX();
        this.bulletY = player.getPlayerY();
        this.graphics2D = graphics2D;
    }


    @Override
    public void run() {
        init();
        while (!collided) {
            moveBullet();
            drawBullet(graphics2D);
        }
    }

    public void init() {
        try {
            bulletSprite = ImageIO.read(new File("src//Sprites//bulletSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBullet(Graphics2D g) {
        g.drawImage(bulletSprite,player.getPlayerX(),player.getPlayerY()+20,null);
    }

    public void moveBullet() {
        if (player.getFacing() == 1) {
            bulletX++;
        } else {
            bulletX--;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
