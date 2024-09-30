import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame
{

    private JTextArea fortuneDisplay;
    private ArrayList<String> fortunes;
    private Random random;
    private int lastFortuneIndex = -1;

    public FortuneTellerFrame()
    {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width * 3 / 4, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Fortune Teller", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        ImageIcon icon = new ImageIcon("path_to_your_image.png");
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(titleLabel, BorderLayout.NORTH);
        fortuneDisplay = new JTextArea(10, 30);
        fortuneDisplay.setFont(new Font("SansSerif", Font.PLAIN, 16));
        fortuneDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(fortuneDisplay);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton readFortuneButton = new JButton("Read My Fortune!");
        readFortuneButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        buttonPanel.add(readFortuneButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        fortunes = new ArrayList<>();
        fillFortunes();
        random = new Random();
        readFortuneButton.addActionListener((ActionEvent e) -> displayNewFortune());
        quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    }

    // Fill the fortunes list
    private void fillFortunes()
    {
        fortunes.add("You will have a great day!");
        fortunes.add("Something exciting is coming your way.");
        fortunes.add("Be cautious today.");
        fortunes.add("Luck will be on your side.");
        fortunes.add("A new adventure is ahead.");
        fortunes.add("You will meet someone important.");
        fortunes.add("Your hard work will pay off soon.");
        fortunes.add("Expect a pleasant surprise.");
        fortunes.add("Today is a good day to take a risk.");
        fortunes.add("An opportunity will present itself.");
        fortunes.add("Trust your instincts.");
        fortunes.add("Good fortune will come your way.");
    }
    private void displayNewFortune()
    {
        int newIndex;
        do
        {
            newIndex = random.nextInt(fortunes.size());
        }
        while (newIndex == lastFortuneIndex);

        String newFortune = fortunes.get(newIndex);
        fortuneDisplay.append(newFortune + "\n");
        lastFortuneIndex = newIndex;
    }
}
