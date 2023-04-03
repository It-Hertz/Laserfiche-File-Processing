import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Dista
 */

public class main {
    public static void main(String[] args) {
        
        Remote dnld = new Remote();
        //dnld.info();
        //dnld.downloadDir(18);
        //dnld.downloadEntry(28, "NamesList2", null);
        dnld.close();
        
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
            
            System.out.println("Name Filter");
            List<String> filtered_entries1 = new ArrayList<>();
            filtered_entries1 = filter1.nameFilter("Countries");
            for (String i : filtered_entries1) {
                System.out.println(i);
            }

            System.out.println();

            System.out.println("Length Filter");
            List<String> filtered_entries2 = new ArrayList<>();
            filtered_entries2 = filter1.lengthFilter(37786, "LTE");
            for (String i : filtered_entries2) {
                System.out.println(i);
            }
        }
        catch(Exception e) {
            System.out.println("The file is corrupt/ does not exist");
        }
       
    }
}


