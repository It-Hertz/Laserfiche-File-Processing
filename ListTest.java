/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package list;

/**
 *
 * @author kurot
 */
import java.io.File;

public class ListTest {

    public static void main(String[] args) {

        File file2 = new File("C:\\Users\\kurot\\Downloads\\ExportedFolderContents\\Names");
        File[] a = file2.listFiles();


        List entry = new List();
        entry.ListOf(a, 20);
    }
}
