
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dista
 * 
 * 
 * 
 */

public class Filter {
    
    File[] entries;
    
        public Filter (File f){
        entries = f.listFiles();
    }
    
    public String[] content(String key) {
        
        ArrayList<String> output = new ArrayList<>();
        
        for(File entry : entries) {
            try (Scanner scan = new Scanner(entry)) {
                while (scan.hasNextLine()) {
                    if (scan.nextLine().toLowerCase().contains(key)) {
                        output.add(entry.getName());
                        break;
                    }
                }
                scan.close();
            }
            catch (Exception e1) {
                System.out.println("There was an error with the file");
            }
            
        }
        
        String[] foutput = output.toArray(String[]::new);
        return foutput;
    }
    
    public String[] count(String key, int min) {
        
        ArrayList<String> output = new ArrayList<>();
        int count = 0;
        
        for(File entry : entries) {
            
            try (Scanner scan = new Scanner(entry)) {
                while (scan.hasNextLine()) {
                    if (scan.nextLine().toLowerCase().contains(key)) {
                        count++;                       
                    }
                }
                scan.close();
            }
            catch (Exception e1) {
                System.out.println("There was an error with the file");
            }
            
            if (count >= min) {
                output.add(entry.getName());
            }
            
        }
        
        String[] foutput = output.toArray(String[]::new);
        return foutput;
    }
    
    public List<String> nameFilter(String key) {
        ArrayList<String> filteredList = new ArrayList<String>(); // create a new ArrayList to store the filtered
                                                                  // entries

        // iterate through the array of entries and add the ones whose name contains the
        // key to the filteredList
        for (File entry : entries) {
            if (entry.toString().contains(key)) {
                filteredList.add(entry.toString());
            }
        }

        return filteredList; // return the filtered list
    }

    public List<String> lengthFilter(long length, String operator) {
        List<String> files = new ArrayList<>(); // create a new ArrayList to store the filtered files

        // iterate through the array of entries and add the ones that are files and
        // whose length satisfies the given operator to the files list
        for (File entry : entries) {
            if (entry.isFile() && checkLength(entry, length, operator)) {
                files.add(entry.toString());
            }
        }

        return files; // return the filtered list of files
    }

    // helper method to check file length
    public static boolean checkLength(File file, long length, String operator) {
        long fileLength = file.length(); // get the length of the file
        switch (operator) { // evaluate the given operator and return true or false based on the comparison
            case "EQ":
                return fileLength == length;
            case "NEQ":
                return fileLength != length;
            case "GT":
                return fileLength > length;
            case "GTE":
                return fileLength >= length;
            case "LT":
                return fileLength < length;
            case "LTE":
                return fileLength <= length;
            default:
                return false;
        }
    }
    
}
