package splitprocess;

/**
 *
 * @author Salma Mobasher
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main 
{
    
    public static void main(String[] args) 
	{
        try 
		{
            // Here we are making a file to test the project...we will change it
            String fileData = "This is a test 1\nTo show it works 2\nvery cool right 3\nSo we can see that 4\nthere is lots of data 5\nthat is being split 6\ninto 7\ndifferent 8\nfiles 9\nif it works 10\n";
            File fileIn = File.createTempFile("input", ".txt");
            FileWriter fwriter = new FileWriter(fileIn);
            try (BufferedWriter buffwriter = new BufferedWriter(fwriter)) 
            {
                buffwriter.write(fileData);
            }
            List<File> entries = new ArrayList<>(); // this makes the list
            entries.add(fileIn);
            List<File> outputSplit = SplitProcess.splitEntries(entries, 2); //HERE IS WHERE THE LINES NUMBER IS...can be by user input or hardcoded
            List<String> lines = Files.readAllLines(fileIn.toPath(), StandardCharsets.UTF_8); //this is for printing theinput file
            System.out.println("Input file contents:");
            for (String newLine : lines) 
            {
                System.out.println(newLine);
            }
            System.out.println("Output files:"); //this prints the outputSplit files
            for (File file : outputSplit) 
            {
                System.out.println(file.getName());
            }
            fileIn.delete(); //this will delete the temp file
        } 
        catch (IOException e) 
        {
            
        }
    }
}

