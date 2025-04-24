import javax.swing.JFileChooser;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan {
    public static void main(String[] args) {

        Scanner inFile = null;
        String line;
        String rec = "";
        JFileChooser chooser = new JFileChooser();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Path target = new File(System.getProperty("user.dir") + "\\src\\main\\java\\FileInspector.java").toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(new File("src"));
        File selectedFile;

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (args.length > 0) {
                selectedFile = new File(args[0]);
                if (selectedFile.exists()) {
                    System.out.println("File not found." + args[0]);
                    return;
                }
            } else {
                if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                } else {
                    System.out.println("No file selected.");
                    return;
                }
            }
            Path file = chooser.getSelectedFile().toPath();
            InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while (inFile.hasNextLine()) {
                line = inFile.nextLine();
                System.out.println(line);
                lineCount = lineCount + 1;
                wordCount = line.split("\\s+").length + wordCount;
                charCount = line.length() + charCount;
            }

            System.out.println("\n Summary report: ");
            System.out.println("File: " + target.toFile().getName());
            System.out.println("Line: " + lineCount);
            System.out.println("Word: " + wordCount);
            System.out.println("Characters: " + charCount);
            inFile.close();}
            
        catch (FileNotFoundException e){
            System.out.println ("File not found.");
            e.printStackTrace();}
        
        catch (IOException e) {
                    e.printStackTrace();}
    }
}
        