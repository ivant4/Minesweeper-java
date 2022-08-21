package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AppFrame extends JFrame{
    private UtilPanel utilPanel;
    private MinefieldPanel minefieldPanel;
    private int WIDTH = 915;
    private int HEIGHT = 600;

    public AppFrame(Game game) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // when this closes, this closes the event dispatch thread
        this.setTitle("Minesweeper");
        this.setResizable(false);
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
