import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Nicholas Ciublea
 */
public class RenameElement {

    public static List<File> renameFiles (String directoryPath, String suffix) throws IOException {
        List<File> renamedEntries = new ArrayList<>();         //places all file objects into an array
        
        File directory = new File(directoryPath);      //checks if item is a folder or file
        if (!directory.isDirectory()){                        //throws an 'IllegalArgumentException' if it is not a directory
            throw new IllegalArgumentException("Path does not point to a directory!");
        }
        
        File[] files = directory.listFiles();                 //checks if directory is empty
        if (files == null){
            System.out.println(directoryPath + " is empty.\n Please provide a directory with files");        
        
            FileStore file_Location = Files.getFileStore(Paths.get(directoryPath));
            String type = file_Location.type();                   //checks if files are local or remote
            if(type.equals("NTFS")){                      //unable to process remote files (read only permissions)
            
                for (File entry : files){
                    if(entry.isFile()){
                    String originalName = entry.getName();
                    String newName = originalName.substring(0, originalName.lastIndexOf('.')) + suffix
                        + originalName.substring(originalName.lastIndexOf('.'));
                    File renamedEntry = new File(entry.getParent(), newName);
                
                    if (entry.renameTo(renamedEntry)) {
                    renamedEntries.add(renamedEntry);
                }
                        else {renamedEntries.add(entry);}
                    }
                }
            }
                else {System.out.println("Renaming is not supported on files in the Repository. ");
                    System.out.println("If you wish to rename the files, please do so on the local drive");
                }           //error message to remind user or bring attention to this limitation
        
        return renamedEntries; 
    }
    return renamedEntries;
}
    
    public static void main(String[] args) {
        List<File> renamedEntries = renameFiles.renameEntries("path/to/directory", "_new");
    //this is an example of how the method would be called
    //'path/to/directory' is where the directory will be placed
    //'_new' is a placeholder for the desired suffix
    }
    
}
