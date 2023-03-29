import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    public static List<String> nameFilter(ArrayList<String> entries, String key) {
        ArrayList<String> filteredList = new ArrayList<String>();

        for (int i = 0; i < entries.size(); i++) {
            if (entries.contains(key)) {
                filteredList.add(entries.get(i));
            }
        }

        return filteredList;
    }

    public static List<File> lengthFilter(List<File> entries, long length, String operator) {
        List<File> files = new ArrayList<>();

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).isFile() && checkLength(entries.get(i), length, operator)) {
                files.add(entries.get(i));
            }
        }

        return files;
    }

    public static boolean checkLength(File file, long length, String operator) {
        long fileLength = file.length();
        switch (operator) {
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

    public static void main(String[] args) {

    }
}