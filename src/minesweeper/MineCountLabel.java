package minesweeper;

import javax.swing.*;
import java.util.*;

public class MineCountLabel extends JLabel implements Observer {
    public MineCountLabel(Minefield minefield) {
        super();
//        minefield.addObserver(this);
//        this.setText(Integer.toString(minefield.getMineCount()));
    }
    public void update(Observable o, Object arg) {
        this.setText(Integer.toString((Integer) arg));
    }



}
