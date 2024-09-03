import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args) throws InterruptedException
    {
        ArrayList recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = true;
        String ID = "";
        String fname = "";
        String lname = "";
        String title = "";
        String record = "";
        int yob = 0;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonData.txt");
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID 000001");
            fname = SafeInput.getNonZeroLenString(in, "Enter your First Name");
            lname = SafeInput.getNonZeroLenString(in, "Enter your Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter your Title");
            yob = SafeInput.getRangedInt(in, "Enter the year of your birth", 1000, 9999);
            record = ID + ", " + fname + ", " + lname + ", " + title + ", " + yob;
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