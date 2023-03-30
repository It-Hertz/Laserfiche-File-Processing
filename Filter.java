import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    File[] entries;

    public Filter(File f) {
        entries = f.listFiles();
    }

    public List<File> nameFilter(String key) {
        ArrayList<File> filteredList = new ArrayList<File>();

        for (File entry : entries) {
            if (entry.toString().contains(key)) {
                filteredList.add(entry);
            }
        }

        return filteredList;
    }

    public List<File> lengthFilter(long length, String operator) {
        List<File> files = new ArrayList<>();

        for (File entry : entries) {
            if (entry.isFile() && checkLength(entry, length, operator)) {
                files.add(entry);
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
}