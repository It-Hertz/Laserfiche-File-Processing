import java.io.File;

/**
 *
 * @author Dista
 */

public class main {
    public static void main(String[] args) {
        
        String key = "TORONTO";
        key = key.toLowerCase();
        int min = 2;

        try {
            File entry = new File("GeoNames");
            Filter filter1 = new Filter(entry);
            
            System.out.println("Content Filtered:");
            for (String i : filter1.content(key)){
                System.out.println(i);
            }
            
            System.out.println("Count Filtred:");
            for (String i : filter1.count(key, min)){
                System.out.println(i);
            }
        }
        catch(Exception e) {
            System.out.println("The file is corrupt/ does not exist");
        }
       
    }
}


