/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IOFile;

import interfaces.SaveData;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author User
 */
public class SaveFileText implements SaveData{
    private String fName;
    public SaveFileText (String fname){
        this.fName = fname;
    }
    @Override
    public void save(Object[] o, String header) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f); // ghi dữ liệu
            PrintWriter pw = new PrintWriter(fw);// in dữ liệu
            pw.println(header);
            for (Object object : o) {
                pw.println(object);
            }
            System.out.println("Save to file text sucessfully");
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
