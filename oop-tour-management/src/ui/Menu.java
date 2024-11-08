/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.*;
import util.MyUtil;

/**
 *
 * @author User
 */
public class Menu {
    private String tile;
    private List<String> optionList = new ArrayList();

    public Menu(String tile) {
        this.tile = tile;
    }
    
    public void printMenu(){
        if(optionList.isEmpty()){
            System.out.println("Menu is empty!!");
            return;
        }
        System.out.println("===========" + tile + "===========");
        for (String option : optionList) {
            System.out.println(option);
        }
    }
    
    public void addNewOption(String newOption){
     
        optionList.add(newOption);
    }
    public int getMaxChoice(){
        return optionList.size();
    }
    public int getChoice (){
        int choice;
        int maxChoice = optionList.size();
        String inputMsg = "Choose [1..." + maxChoice + "]: ";
        String errorMsg = "Please choose [1..." + maxChoice + "]";
        choice = MyUtil.getAnInteger(inputMsg, errorMsg, 1, maxChoice);
        return choice;
    }
}
