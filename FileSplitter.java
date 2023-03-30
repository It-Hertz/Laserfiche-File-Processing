package FileSplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSplitter {
    public static List<File> splitEntries(List<File> entries, int lines) throws IOException {
        List<File> outputFiles = new ArrayList<>();

        for (File entry : entries) {
            if (entry.isFile()) {
                // Split the file
                BufferedReader reader = new BufferedReader(new FileReader(entry));
                String line;
                int partNumber = 1;
                while ((line = reader.readLine()) != null) {
                    // Create a new part file every "lines" number of lines
                    if (partNumber == 1 || (partNumber - 1) % lines == 0) {
                        String filename = entry.getName() + ".part" + partNumber + ".txt";
                        File partFile = new File(entry.getParentFile(), filename);
                        outputFiles.add(partFile);

                        // Write the part file header
                        FileWriter writer = new FileWriter(partFile);
                        writer.write("Part " + partNumber + " of " + entry.getName() + "\n\n");
                        writer.flush();
                        writer.close();
                    }

                    // Append the line to the current part file
                    File partFile = outputFiles.get(outputFiles.size() - 1);
                    FileWriter writer = new FileWriter(partFile, true);
                    writer.write(line + "\n");
                    writer.flush();
                    writer.close();

                    // Move to the next part if we reached "lines" number of lines
                    if (partNumber % lines == 0) {
                        partNumber++;
                    }
                }
                reader.close();
            }
        }

        return outputFiles;
    }

}
