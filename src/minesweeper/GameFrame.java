package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class GameFrame extends JFrame{
    private UtilPanel utilPanel;
    private MinefieldPanel minefieldPanel;
    private int WIDTH, HEIGHT;

    public GameFrame(Game game) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // when this closes, this closes the event dispatch thread
        this.setTitle("insert title");
        this.setResizable(false);
        WIDTH = 915;
        HEIGHT = 600;
        this.setSize(WIDTH, HEIGHT);
        init(game);
    }
    private void init(Game game) {
        utilPanel = new UtilPanel(game, WIDTH, HEIGHT);
        minefieldPanel = new MinefieldPanel(game, WIDTH, HEIGHT);
        this.add(utilPanel);
        this.add(minefieldPanel);
        this.setVisible(true);
    }
    public void reset(Game game) {
        this.remove(utilPanel);
        this.remove(minefieldPanel);
        init(game);
    }
}
