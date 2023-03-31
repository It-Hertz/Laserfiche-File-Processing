import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        File entry = new File("GeoNames");
        Filter filter1 = new Filter(entry);

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
}