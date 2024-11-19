import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {
    private JTextField nameField, descriptionField, idField, costField, recordCountField;
    private RandomAccessFile file;
    private int recordCount = 0;

    public RandProductMaker() {
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // GUI Components
        add(new JLabel("Name (35 chars):"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Description (75 chars):"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("ID (6 chars):"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Cost:"));
        costField = new JTextField();
        add(costField);

        add(new JLabel("Record Count:"));
        recordCountField = new JTextField("0");
        recordCountField.setEditable(false);
        add(recordCountField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addProduct());
        add(addButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);

        try {
            file = new RandomAccessFile("products.dat", "rw");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    private void addProduct() {
        try {
            String name = Product.padString(nameField.getText(), 35);
            String description = Product.padString(descriptionField.getText(), 75);
            String id = Product.padString(idField.getText(), 6);
            double cost = Double.parseDouble(costField.getText());

            file.seek(file.length());
            file.writeUTF(name);
            file.writeUTF(description);
            file.writeUTF(id);
            file.writeDouble(cost);

            recordCount++;
            recordCountField.setText(String.valueOf(recordCount));

            // Clear fields
            nameField.setText("");
            descriptionField.setText("");
            idField.setText("");
            costField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}
