/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author User
 */
public interface IManager<T> {
    public void add();
    public void update();
    public void remove();
    public void printListAscendingById();
    public void searchById();
    public int searchById(String id);
    public T searchObjectById(String id);
    public void readData(LoadData loadData);
    public void saveToData(SaveData saveData);
}
