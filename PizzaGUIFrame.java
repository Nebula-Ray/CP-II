import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {
    // Components for the GUI
    private JRadioButton thinCrust, regularCrust, deepDishCrust;
    private JComboBox<String> sizeComboBox;
    private JCheckBox[] toppingsCheckBoxes;
    private JTextArea orderArea;
    private JButton orderButton, clearButton, quitButton;

    public PizzaGUIFrame() {
        setTitle("Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        JPanel crustPanel = new JPanel();
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust Type"));
        thinCrust = new JRadioButton("Thin");
        regularCrust = new JRadioButton("Regular");
        deepDishCrust = new JRadioButton("Deep-Dish");
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDishCrust);
        crustPanel.add(thinCrust);
        crustPanel.add(regularCrust);
        crustPanel.add(deepDishCrust);

        JPanel sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        String[] sizes = {"Small ($8.00)", "Medium ($12.00)", "Large ($16.00)", "Super ($20.00)"};
        sizeComboBox = new JComboBox<>(sizes);
        sizePanel.add(sizeComboBox);


        JPanel toppingsPanel = new JPanel();
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        toppingsCheckBoxes = new JCheckBox[]{
                new JCheckBox("Pepperoni"), new JCheckBox("Mushrooms"),
                new JCheckBox("Onions"), new JCheckBox("Bacon"),
                new JCheckBox("Olives"), new JCheckBox("Extra Cheese")
        };
        for (JCheckBox topping : toppingsCheckBoxes) {
            toppingsPanel.add(topping);
        }

        JPanel orderPanel = new JPanel();
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(orderArea);
        orderPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);


        add(crustPanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.WEST);
        add(toppingsPanel, BorderLayout.CENTER);
        add(orderPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateOrder();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOrder(crustGroup);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }


    private void calculateOrder() {
        String crust = thinCrust.isSelected() ? "Thin" : regularCrust.isSelected() ? "Regular" : "Deep-Dish";
        String size = (String) sizeComboBox.getSelectedItem();
        double baseCost = size.contains("Small") ? 8.00 : size.contains("Medium") ? 12.00 : size.contains("Large") ? 16.00 : 20.00;

        StringBuilder toppings = new StringBuilder("Toppings: ");
        double toppingsCost = 0;
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                toppings.append(checkBox.getText()).append(", ");
                toppingsCost += 1.00;
            }
        }

        double subtotal = baseCost + toppingsCost;
        double tax = subtotal * 0.07;
        double total = subtotal + tax;

        
        orderArea.setText(String.format(
                "=========================================\n" +
                        "Type of Crust: %s\nSize: %s\n" +
                        "Toppings: %s\n" +
                        "Sub-total: $%.2f\nTax: $%.2f\n" +
                        "-----------------------------------------\n" +
                        "Total: $%.2f\n" +
                        "=========================================",
                crust, size, toppings.toString(), subtotal, tax, total
        ));
    }
    private void clearOrder(ButtonGroup crustGroup) {
        crustGroup.clearSelection();
        sizeComboBox.setSelectedIndex(0);
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            checkBox.setSelected(false);
        }
        orderArea.setText("");
    }
}
