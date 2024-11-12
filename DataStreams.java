import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStreams extends JFrame {
    private JTextArea originalTextArea;
    private JTextArea filteredTextArea;
    private JTextField searchField;
    private JButton loadButton, searchButton, quitButton;
    private Path loadedFilePath;

    public DataStreams() {
        setTitle("Data Stream File Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        originalTextArea = new JTextArea();
        originalTextArea.setEditable(false);
        JScrollPane originalScrollPane = new JScrollPane(originalTextArea);

        filteredTextArea = new JTextArea();
        filteredTextArea.setEditable(false);
        JScrollPane filteredScrollPane = new JScrollPane(filteredTextArea);

        searchField = new JTextField(20);

        loadButton = new JButton("Load File");
        searchButton = new JButton("Search");
        quitButton = new JButton("Quit");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(loadButton);
        topPanel.add(searchButton);
        topPanel.add(quitButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        textPanel.add(originalScrollPane);
        textPanel.add(filteredScrollPane);
        add(textPanel, BorderLayout.CENTER);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInFile();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadedFilePath = fileChooser.getSelectedFile().toPath();
            try (Stream<String> lines = Files.lines(loadedFilePath)) {
                originalTextArea.setText("");
                lines.forEach(line -> originalTextArea.append(line + "\n"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage());
            }
        }
    }

    private void searchInFile() {
        if (loadedFilePath == null) {
            JOptionPane.showMessageDialog(this, "Please load a file first.");
            return;
        }

        String searchString = searchField.getText().trim();
        if (searchString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search string.");
            return;
        }

        try (Stream<String> lines = Files.lines(loadedFilePath)) {
            List<String> filteredLines = lines
                    .filter(line -> line.contains(searchString))
                    .collect(Collectors.toList());

            filteredTextArea.setText("");
            filteredLines.forEach(line -> filteredTextArea.append(line + "\n"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error searching file: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataStreams app = new DataStreams();
            app.setVisible(true);
        });
    }
}
