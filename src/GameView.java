import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Random;

public class GameView extends Canvas implements Runnable {
    private boolean running;
    private Thread thread;
    private Thread obstacleThread;
    private BufferedImage background;

    private Player player;
    private Bullet bullet;


    @Override
    public void run() {
        init();
        createPlayer();
        KeyListening();
        while (running) {
            paint();
        }
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }

    public void init() {
        createBufferStrategy(2);
        running = true;
    }


    public void createPlayer() {
        player = new Player(50,616);
        thread = new Thread(player);
        thread.start();
    }

    public void paint() {
        BufferStrategy bs;
        Graphics2D g;
        bs = this.getBufferStrategy();

        if (bs == null) {
            return;
        }
        g = (Graphics2D) bs.getDrawGraphics();
        g.drawLine(0,700,1024,700);
        player.drawPlayer(g);
        bs.show();
        super.paint(g);
        g.dispose();
    }

    public void KeyListening() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    player.jumpPlayer();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    player.movePlayer(1);
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    player.movePlayer(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                   // player.Shoot();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }


}
