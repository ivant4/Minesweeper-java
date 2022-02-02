package minesweeper;

import javax.swing.*;
import java.awt.*;

public class SquareButton extends JButton {
    private int[] position;
    private Dimension preferredSize;
    
    public SquareButton(int row, int col, int buttonWidth, int buttonHeight) {
        super();
        position = new int[] {row, col};
        preferredSize = new Dimension(buttonWidth, buttonHeight);
    }
    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }
    public int[] getPosition() { return position; }
}
