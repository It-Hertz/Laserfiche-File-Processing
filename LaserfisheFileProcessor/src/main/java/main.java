import java.io.File;

/**
 *
 * @author Dista
 */

public class main {
    public static void main(String[] args) {
                      
        try {
            File entry = new File("GeoNames");
        }
        catch(Exception e) {
            System.out.println("The file is corrupt/ does not exist");
        }
        
        
    }
}
