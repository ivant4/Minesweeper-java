package minesweeper;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class MinefieldPanel extends JPanel implements MouseListener { // make this implements mouselistener
    private Minefield minefield;
    private SquareButton[][] squareButtons;
    private Game game;
    public MinefieldPanel(Game game, int frameWidth, int frameHeight) {
        super();
        this.game = game;
        minefield = game.getMinefield();
        int nrows = minefield.getNRows();
        int ncols = minefield.getNCols();
        this.setBounds(0, 0, frameWidth, 4 * frameHeight / 5);
        this.setLayout(null);
        squareButtons = new SquareButton[nrows][ncols];
        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                squareButtons[row][col] = new SquareButton(row, col, 30, 30);
                squareButtons[row][col].setBounds(col*30, row*30, 30, 30);
                squareButtons[row][col].addMouseListener(this);
                this.add(squareButtons[row][col]); 
            }
        }
    }
    public void mouseClicked(MouseEvent e) {;}
    public void mouseEntered(MouseEvent e) {;}
    public void mouseExited(MouseEvent e) {;}
    public void mousePressed(MouseEvent e) {;}
    public void mouseReleased(MouseEvent e) {
        if (game.isOver()) return;
        SquareButton source = (SquareButton) e.getSource();
        int[] sourcePos = source.getPosition();
        System.out.println(Arrays.toString(source.getPosition()));
        if (e.getButton() == 1) { leftClicked(sourcePos); }
        else if (e.getButton() == 3) { rightClicked(source); }
        
    }
    private void leftClicked(int[] sourcePos) {
        if (!minefield.canSquareBeRevealed(sourcePos)) return;
        ArrayList<Square> squaresToReveal = minefield.revealSquare(sourcePos);
        for (Square s : squaresToReveal) {
            int[] sPos = s.getPosition();
            int row = sPos[0];
            int col = sPos[1];
            SquareButton currSqrButt = squareButtons[row][col];
            if (s.hasMine()) {
                currSqrButt.setText("x"); // display a mine
            } else { // display its numMinesAdjToS
                int numOfAdjMines = s.getNumOfAdjSquaresWithMine();
                if (numOfAdjMines > 0) {
                    currSqrButt.setText(Integer.toString(numOfAdjMines));
                }
            }
            currSqrButt.setMargin(new Insets(0, 0, 0, 0));
            currSqrButt.setFont(new Font("Calibri", Font.BOLD, 20));
            currSqrButt.setEnabled(false);
        }
    }
    private void rightClicked(SquareButton source) {
        int[] sourcePos = source.getPosition();
        minefield.toggleSquareFlagStatus(sourcePos);
        if (minefield.isSquareRevealed(sourcePos)) return;
        if (minefield.isSquareFlagged(sourcePos)) source.setText("O"); // display a flag 
        else source.setText("");
        source.setMargin(new Insets(0, 0, 0, 0));
        source.setFont(new Font("Calibri", Font.BOLD, 20));
    }
}
