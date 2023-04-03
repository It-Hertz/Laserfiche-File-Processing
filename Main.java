package splitprocess;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("path/to/file.txt");
        SplitProcess splitProcess = new SplitProcess(inputFile);
        List<File> outputFiles = splitProcess.splitEntries(5);
        System.out.println("Output files:");
        for (File outputFile : outputFiles) {
            System.out.println(outputFile.getName());
        }
    }
}
