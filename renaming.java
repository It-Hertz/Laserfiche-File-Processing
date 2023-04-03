import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Nicholas Ciublea
 */
public class renaming {

public static void appendSuffixToFilesInFolder(String folderPath, String suffix) throws IOException {
    // Create a new folder with the updated files
    Path originalFolderPath = Path.of(folderPath);
    Path newFolderPath = Files.createDirectory(originalFolderPath.resolveSibling(folderPath + "-" + suffix));
    File folder = new File(folderPath);
    File[] files = folder.listFiles();
    if (files != null) {
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String[] parts = fileName.split("\\.");
                if (parts.length > 1) {
                    // Append the suffix before the file extension
                    String newName = parts[0] + "-" + suffix + "." + parts[parts.length - 1];
                    Path newFilePath = newFolderPath.resolve(newName);
                    // Copy the file to the new folder with the updated name
                    Files.copy(file.toPath(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}


    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
