package splitprocess;

/**
 *
 * @author Salma Mobasher
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "/path/to/file.txt";
        File inputFile = new File(filePath);
        if (!inputFile.exists()) {
            System.out.println("File not found.");
            return;
        }

        List<File> inputList = new ArrayList<>();
        inputList.add(inputFile);

        int linesPerFile = 100; // Change this value to the desired number of lines per output file.

        List<File> outputList = SplitProcess.splitEntries(inputList, linesPerFile);
        System.out.println("Output files:");
        for (File file : outputList) {
            System.out.println(file.getAbsolutePath());
        }
    }
}


