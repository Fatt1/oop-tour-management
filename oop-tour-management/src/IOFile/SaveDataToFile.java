/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IOFile;

import interfaces.SaveData;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author User
 */
public class SaveDataToFile implements SaveData{

    @Override
    public void save(String fName, Object[] o) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fName);
            out = new ObjectOutputStream(out);
            for (int i = 0; i < o.length; i++) {
                out.writeObject(o[i]);
            }
            fos.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Save error");
        }
    }
    
}
