package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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
