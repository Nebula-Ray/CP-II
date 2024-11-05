import javax.swing.*;
import java.awt.*;

public class GameController {
    private GameBoard gameBoard;
    private MainFrame mainFrame;
    private int missCounter = 0, strikeCounter = 0, totalMissCounter = 0, totalHitCounter = 0;

    public GameController(GameBoard gameBoard, MainFrame mainFrame) {
        this.gameBoard = gameBoard;
        this.mainFrame = mainFrame;

        setupBoard();
    }

    private void setupBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton cell = gameBoard.getCell(i, j);
                int finalI = i;
                int finalJ = j;
                cell.addActionListener(e -> handleCellClick(finalI, finalJ, cell));
            }
        }
    }

    private void handleCellClick(int row, int col, JButton cell) {
        if (cell.getText().equals("S")) {
            totalHitCounter++;
            cell.setBackground(Color.RED);
            cell.setText("X");
            missCounter = 0;
            JOptionPane.showMessageDialog(mainFrame, "Hit!");


            if (totalHitCounter == 17) {
                JOptionPane.showMessageDialog(mainFrame, "You won! Play again?");
                resetGame();
            }
        } else {
            missCounter++;
            totalMissCounter++;
            cell.setBackground(Color.YELLOW);
            cell.setText("M");

            if (missCounter >= 5) {
                strikeCounter++;
                missCounter = 0;
            }
            if (strikeCounter >= 3) {
                JOptionPane.showMessageDialog(mainFrame, "You lost! Try again.");
                resetGame();
            }
        }

        mainFrame.updateCounters(missCounter, strikeCounter, totalMissCounter, totalHitCounter);
        cell.setEnabled(false);
    }

    public void resetGame() {
        missCounter = 0;
        strikeCounter = 0;
        totalMissCounter = 0;
        totalHitCounter = 0;
        gameBoard.placeShips();
        mainFrame.updateCounters(missCounter, strikeCounter, totalMissCounter, totalHitCounter);
    }
}
