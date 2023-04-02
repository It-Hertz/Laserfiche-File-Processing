import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nicholas Ciublea
 */
public class Print_Element {

    public static void printProperties(String directoryPath){
        File directory = new File(directoryPath); //checks if item is a folder or a file
        if (!directory.isDirectory()){
            System.out.println(directoryPath + " is not a directory.");
        }
        
        File[] files = directory.listFiles();           //checks if file is empty
        if (files == null){System.out.println(directoryPath + " is empty.");}
        
        for (File file : files){
            if (file.isFile()){                         //obtains basic file properties
                String type = file.getName();
                String name = file.getName();
                long length = file.length();
                String absolutePath = file.getAbsolutePath();
                
                if(type.equals("local")){       //printing out local file properties
                    System.out.print("LOCAL File Name: " + name);
                    System.out.println("   ||   Size: " + length);
                    System.out.println("Path: " + absolutePath + "\n~~~~~\n");
                } else {                                //printing remote file properties while obtaining entryID
                    String entryId = name.substring(0, name.indexOf("_"));
                    System.out.print("REMOTE File Name: " + name);
                    System.out.print("   ||   Size: " + length);
                    System.out.println("   ||   Entry ID: " + entryId);
                    System.out.println("Path: " + absolutePath + "\n~~~~~\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        String directoryPath = "C:/example";            //to test with only local files (temporary)
        printProperties(directoryPath);                 //replace the path with a local path to test
    }
    
}
