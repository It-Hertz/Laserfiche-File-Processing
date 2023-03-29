
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

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
}
