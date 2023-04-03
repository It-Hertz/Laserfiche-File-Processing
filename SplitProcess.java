
package splitprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; //file reader and writer require exception handling
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitProcess 
{
    
    private final File file;

    public SplitProcess(File file) {
        this.file = file;
    }
    
   public List<File> splitEntries(int lines) 
   {
        List<File> outputSplit = new ArrayList<>(); //creates a list for output files
        if (file.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file)))  //file is read
		{
                    String filename = file.getName(); //current input file
                    int numOfPart = 1; //output file number
                    String newLine = reader.readLine();//current line read from input
                    while (newLine != null) //while theres another line
                    {
                        File partFile = new File(file.getParentFile(), fileNameParts(filename, numOfPart)); //creates output file
                        try (FileWriter writer = new FileWriter(partFile)) //writes current line to output file
			{
                            for (int i = 0; i < lines && newLine != null; i++) 
                            {
                                writer.write(newLine + "\n");
                                newLine = reader.readLine(); //current line read from input
                            }
                            outputSplit.add(partFile); //holds output files
                            numOfPart++; //counts number of files output
                        }
                    }
                    
                } 
                catch (IOException e) 
		{
                    e.printStackTrace();
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



