import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String id; // Fixed length of 6
    private double cost;

    public Product(String name, String description, String id, double cost) {
        this.name = padString(name, 35);
        this.description = padString(description, 75);
        this.id = padString(id, 6);
        this.cost = cost;
    }

    public static String padString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }

    public String getName() {
        return name.trim();
    }

    public String getDescription() {
        return description.trim();
    }

    public String getId() {
        return id.trim();
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Description: %s, Cost: %.2f", getId(), getName(), getDescription(), cost);
    }
}
