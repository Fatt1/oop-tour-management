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
   private String fName;
    public SaveDataToFile(String fName) {
        this.fName = fName;
    }
        
    @Override
    public void save(Object[] o, String header) {
        if(o.length == 0){
            System.out.println("Empty List!!");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            
            for (int i = 0; i < o.length; i++) {
                out.writeObject(o[i]);
            }
            fos.close();
            out.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
