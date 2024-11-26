import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextField inputField;
    private JTextField searchField;
    private JTextArea displayArea;

    public SortedListGUI() {
        sortedList = new SortedList();
        setTitle("Sorted List with Binary Search");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(20);
        add(inputField);

        JButton addButton = new JButton("Add to List");
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (!input.isEmpty()) {
                    sortedList.add(input);
                    updateDisplay();
                    inputField.setText("");
                }
            }
        });

        searchField = new JTextField(20);
        add(searchField);

        JButton searchButton = new JButton("Search List");
        add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                if (!search.isEmpty()) {
                    int result = sortedList.binarySearch(search);
                    String message = (result < sortedList.getList().size() && sortedList.getList().get(result).equals(search)) ?
                            "Found: " + search :
                            "Not found. It would be inserted at position " + result;
                    JOptionPane.showMessageDialog(SortedListGUI.this, message);
                    searchField.setText(""); // Clear search field
                }
            }
        });

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));
        
        updateDisplay();
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder("Sorted List:\n");
        for (String item : sortedList.getList()) {
            sb.append(item).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SortedListGUI().setVisible(true);
            }
        });
    }
}
