package minesweeper;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResetButton extends JButton {
    private Game game;
    public ResetButton(Game game) {
        super();
        this.game = game;
        this.setText("Reset");
        this.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                game.reset();
            }
        });
    }
}
