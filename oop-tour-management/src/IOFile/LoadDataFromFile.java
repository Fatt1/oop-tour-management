/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IOFile;

import interfaces.LoadData;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.FileObject;

/**
 *
 * @author User
 */
public class LoadDataFromFile implements LoadData {

    @Override
    public Object[] read(String fName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object result[] = null;
        int count = 0;
        try {
            fis = new FileInputStream(fName);
            ois = new ObjectInputStream(fis);
            result = new Object[0];
            Object x;
            while ((x = ois.readObject()) != null) {
                result = Arrays.copyOf(result, count + 1);
                result[count++] = x;
            }

            fis.close();
            ois.close();
        } catch (EOFException ex) {
           
        } catch(Exception e){
            System.out.println(e);
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
            }
        }

        return result;
    }

}
