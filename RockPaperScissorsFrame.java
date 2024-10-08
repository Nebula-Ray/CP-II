import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame
{
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextField playerWinsField;
    private JTextField computerWinsField;
    private JTextField tiesField;
    private JTextArea resultArea;

    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private final Random random = new Random();

    public RockPaperScissorsFrame()
    {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);

        rockButton.addActionListener(new GameButtonListener("Rock"));
        paperButton.addActionListener(new GameButtonListener("Paper"));
        scissorsButton.addActionListener(new GameButtonListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        add(buttonsPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));

        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 10);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 10);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 10);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        add(statsPanel, BorderLayout.CENTER);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private class GameButtonListener implements ActionListener
    {
        private final String playerChoice;

        public GameButtonListener(String playerChoice)
        {
            this.playerChoice = playerChoice;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            playGame(playerChoice);
        }
    }

    private void playGame(String playerChoice) {
        String computerChoice = choices[random.nextInt(3)];
        String result = determineWinner(playerChoice, computerChoice);

        resultArea.append(playerChoice + " vs " + computerChoice + ": " + result + "\n");
        updateStats(result);
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player wins!";
        } else {
            return "Computer wins!";
        }
    }

    private void updateStats(String result) {
        if (result.contains("Player wins!")) {
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else if (result.contains("Computer wins!")) {
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        } else {
            ties++;
            tiesField.setText(String.valueOf(ties));
        }
    }
}
