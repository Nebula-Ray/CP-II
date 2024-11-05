import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameBoard {
    private JButton[][] cells;
    private JPanel boardPanel;
    private int[][] shipLocations;
    private int[] shipSizes = {5, 4, 3, 3, 2};
    private int totalHitsNeeded = 0;
    private int currentHits = 0;
    private int totalMisses = 0;
    private int consecutiveMisses = 0;
    private static final int MAX_MISSES = 3;
    private static final int TOTAL_MISS_LIMIT = 83;
    private MainFrame mainFrame;

    public GameBoard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        cells = new JButton[10][10];
        shipLocations = new int[10][10];
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));
        initializeBoard();
        placeShips();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new JButton();
                cells[row][col].setBackground(Color.CYAN);
                cells[row][col].addActionListener(new CellClickListener(row, col));
                boardPanel.add(cells[row][col]);
                shipLocations[row][col] = 0;
            }
        }
    }

    void placeShips() {
        Random random = new Random();
        for (int shipSize : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                if (canPlaceShip(row, col, shipSize, horizontal)) {
                    for (int i = 0; i < shipSize; i++) {
                        if (horizontal) {
                            shipLocations[row][col + i] = 1;
                        } else {
                            shipLocations[row + i][col] = 1;
                        }
                    }
                    totalHitsNeeded += shipSize;
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int shipSize, boolean horizontal) {
        if (horizontal) {
            if (col + shipSize > 10) return false;
            for (int i = 0; i < shipSize; i++) {
                if (shipLocations[row][col + i] != 0) return false;
            }
        } else {
            if (row + shipSize > 10) return false;
            for (int i = 0; i < shipSize; i++) {
                if (shipLocations[row + i][col] != 0) return false;
            }
        }
        return true;
    }

    public JButton getCell(int i, int j) {
        return null;
    }

    private class CellClickListener implements ActionListener {
        private int row;
        private int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleCellClick(row, col);
        }
    }

    private void handleCellClick(int row, int col) {
        if (shipLocations[row][col] == 1) {

            cells[row][col].setBackground(Color.RED);
            cells[row][col].setEnabled(false);
            shipLocations[row][col] = 2;
            currentHits++;
            consecutiveMisses = 0;

            if (currentHits == totalHitsNeeded) {
                showEndMessage("You Win! Play again?");
                resetBoard();
                return;
            }

        } else if (shipLocations[row][col] == 0) {
            // MISS
            cells[row][col].setBackground(Color.YELLOW);
            cells[row][col].setEnabled(false);
            totalMisses++;
            consecutiveMisses++;

            if (consecutiveMisses >= MAX_MISSES) {
                showEndMessage("You Lose! Play again?");
                resetBoard();
                return;
            }
        }

        int totalHitCounter = 0;
        mainFrame.updateCounters(currentHits, totalMisses, consecutiveMisses, totalHitCounter);
    }

    private void showEndMessage(String message) {
        int response = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    public void resetBoard() {
        currentHits = 0;
        consecutiveMisses = 0;
        totalMisses = 0;
        totalHitsNeeded = 0;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col].setEnabled(true);
                cells[row][col].setBackground(Color.CYAN);
                shipLocations[row][col] = 0;
            }
        }
        int totalHitCounter = 0;
        mainFrame.updateCounters(0, 0, 0, totalHitCounter);
        placeShips();
    }
}
