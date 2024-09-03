import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class Product
{
    public static void main(String[] args) throws InterruptedException
    {
        ArrayList recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = true;
        String ID = "";
        String Name = "";
        String Description = "";
        String record = "";
        double Cost = 0;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the Product ID 000001");
            Name = SafeInput.getNonZeroLenString(in, "Enter the Name");
            Description = SafeInput.getNonZeroLenString(in, "Enter a Description");
            Cost= SafeInput.getDouble(in, "Enter the Cost");
            record = ID + ", " + Name + ", " + Description + ", " + Cost;
            System.out.println(record);
            recs.add(record);
            doneInput = SafeInput.getYNConfirm(in, "Do you have another record");
        } while (!doneInput);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file, CREATE)))) {
            for (Object r : recs) {
                writer.write(String.valueOf(r));     // Write the entire string
                writer.newLine();   // Adds a new line
            }
            System.out.println("Data file written!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}