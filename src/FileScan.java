import javax.swing.JFileChooser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileScan {
    public static void main(String[] args) {

        Scanner inFile;
        String line;
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Path target;

        if (args.length > 0) {
            target = Paths.get(args[0]);}
        else {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
            } else {
                System.out.println("No such file or directory. Terminating.");
                System.exit(0);
                return;}
        }

        try {
            inFile = new Scanner(target);
            while (inFile.hasNextLine()) {
                line = inFile.nextLine();
                System.out.println(line);
                lineCount = lineCount + 1;
                wordCount = line.split("\\s+").length + wordCount;
                charCount = line.length() + charCount;}

            System.out.println("\n Summary report: ");
            System.out.println("File: " + target.toFile().getName());
            System.out.println("Line: " + lineCount);
            System.out.println("Word: " + wordCount);
            System.out.println("Characters: " + charCount);
            inFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("No such file or directory. Terminating.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong. Terminating.");
            e.printStackTrace();}
    }
}