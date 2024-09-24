import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ShortLister {
    public static void main(String[] args)
    {
        String srcDir = System.getProperty("user.dir") + "/src";

        JFileChooser fileChooser = new JFileChooser(new File(srcDir));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(file))
            {
                ShortWordFilter filter = new ShortWordFilter();
                System.out.println("Short words in the file:");
                while (scanner.hasNext())
                {
                    String word = scanner.next();
                    if (filter.accept(word))
                    {
                        System.out.println(word);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}