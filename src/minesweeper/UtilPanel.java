package minesweeper;

import javax.swing.JPanel;

public class UtilPanel extends JPanel {
    private final TimerLabel timerLabel;
    private final MineCountLabel mineCountLabel;
    private final ResetButton resetButton;

    public UtilPanel(Game game, int WIDTH, int HEIGHT) {
        super();
        this.setBounds(0, 4 * HEIGHT / 5, WIDTH, HEIGHT / 5);
        timerLabel = new TimerLabel(game);
        this.add(timerLabel);
        resetButton = new ResetButton(game);
        this.add(resetButton);
        mineCountLabel = new MineCountLabel(game);
        this.add(mineCountLabel);
    }
}
