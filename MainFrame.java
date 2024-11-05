import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GameBoard gameBoard;
    private JLabel hitCounterLabel;
    private JLabel missCounterLabel;
    private JLabel strikeCounterLabel;

    public MainFrame() {
        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the game board
        gameBoard = new GameBoard(this);

        // Set up counters panel
        JPanel countersPanel = new JPanel();
        countersPanel.setLayout(new GridLayout(1, 3));

        // Counter labels
        hitCounterLabel = new JLabel("Hits: 0");
        missCounterLabel = new JLabel("Total Misses: 0");
        strikeCounterLabel = new JLabel("Strikes: 0");

        countersPanel.add(hitCounterLabel);
        countersPanel.add(missCounterLabel);
        countersPanel.add(strikeCounterLabel);

        add(gameBoard.getBoardPanel(), BorderLayout.CENTER);
        add(countersPanel, BorderLayout.NORTH);

        setSize(600, 600);
        setVisible(true);
    }

    public void updateCounters(int hits, int totalMisses, int strikes, int totalHitCounter) {
        hitCounterLabel.setText("Hits: " + hits);
        missCounterLabel.setText("Total Misses: " + totalMisses);
        strikeCounterLabel.setText("Strikes: " + strikes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
