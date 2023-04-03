/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package list;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author kurot
 */
public class List {
    File [] entries;
    
    public List(File f){
        entries = f.listFiles();
    }

    void ListOf(File f, int Max) {
        this.entries = f.listFiles();
        //an arraylist of files are created to save the entries that are directories
        ArrayList<File> isDirectory = new ArrayList();
        //this for loop checks if it is a directory and adds it to the list
        for (int j = 0; j < f.length; j++) {
            if (f[j].isDirectory() == true) {
                isDirectory.add(f[j]);
            }
        }
        //this for loop is to print the max amount of entries 

        for (File entry : isDirectory) {
            for (int i = 0; i < Max; i++) {
                System.out.println(entry + "\n");
            }
        }

        //it is to print the max amount of entries 
    }
    
}

