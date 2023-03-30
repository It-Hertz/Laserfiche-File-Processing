
package splitprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitProcess 
{
    
    public static List<File> splitEntries(List<File> entries, int lines) 
    {
        List<File> outputSplit = new ArrayList<>();
        for (File entry : entries) 
	{
            if (entry.isFile()) 
            {
                try (BufferedReader reader = new BufferedReader(new FileReader(entry))) 
		{
                    String filename = entry.getName();
                    int numOfPart = 1;
                    String newLine = reader.readLine();
                    while (newLine != null) 
                    {
                        File partFile = new File(entry.getParentFile(), fileNameParts(filename, numOfPart));
                        try (FileWriter writer = new FileWriter(partFile)) 
						{
                            for (int i = 0; i < lines && newLine != null; i++) 
                            {
                                writer.write(newLine + "\n");
                                newLine = reader.readLine();
                            }
                            outputSplit.add(partFile);
                            numOfPart++;
                        }
                    }
                    
                } 
                catch (IOException e) 
		{
                }
            }
        }
        
        return outputSplit;
    }
    
    private static String fileNameParts(String filename, int numOfPart) 
	{
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) 
	{
            return filename + ".part" + numOfPart + ".txt";
        } 
	else 
	{
            return filename.substring(0, dotIndex) + ".part" + numOfPart + filename.substring(dotIndex);
        }
    }

}



