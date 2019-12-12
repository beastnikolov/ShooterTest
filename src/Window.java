import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static GameView gameView = new GameView();

    public Window() {
        setPreferredSize(new Dimension(1024,768));
        setTitle("Shooter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(gameView);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}
