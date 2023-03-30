package filesplitter;


import FileSplitter.FileSplitter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the file to split: ");
        String filePath = scanner.nextLine();

        List<File> entries = new ArrayList<>();
        entries.add(new File(filePath));

        try {
            List<File> outputFiles = FileSplitter.splitEntries(entries, 10);

            for (File outputFile : outputFiles) {
                System.out.println(outputFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
