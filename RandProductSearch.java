import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    private JTextField searchField;
    private JTextArea resultsArea;
    private RandomAccessFile file;

    public RandProductSearch() {
        setTitle("Random Product Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        add(new JLabel("Enter partial product name:"), BorderLayout.NORTH);

        searchField = new JTextField();
        topPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchProducts());
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Results Area
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton, BorderLayout.SOUTH);

        try {
            file = new RandomAccessFile("products.dat", "r");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    private void searchProducts() {
        try {
            file.seek(0);
            resultsArea.setText("");
            String searchTerm = searchField.getText().toLowerCase();

            while (file.getFilePointer() < file.length()) {
                String name = file.readUTF().trim();
                String description = file.readUTF().trim();
                String id = file.readUTF().trim();
                double cost = file.readDouble();

                if (name.toLowerCase().contains(searchTerm)) {
                    resultsArea.append(String.format("Name: %s, ID: %s, Cost: %.2f\n", name, id, cost));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}
