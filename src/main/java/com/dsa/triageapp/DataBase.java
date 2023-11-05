package com.dsa.triageapp;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class DataBase {
    private final File file;
    private FileWriter fWrite = null;
    private FileReader fRead = null;
    private Scanner scan = null;
    private final ArrayList<String[]> arr = new ArrayList<>();

    public DataBase(String filename){
        file = new File("src/main/resources/com/dsa/database/"+filename+".txt");
    }

    public void errorMessage(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error!");
        error.setHeaderText(null);
        error.setContentText(msg);
    }

    public void storeToFile(String records){
        try{
            fWrite = new FileWriter(file);
            fWrite.write(records);
            fWrite.flush();
        } catch(Exception e){
            errorMessage("Error 101: storeToFile\n" + e.getMessage());
        }
    }

    public void addToFile(String records){
        try{
            fWrite = new FileWriter(file, true);
            fWrite.write(records);
            fWrite.flush();
        } catch(Exception e){
            errorMessage("Error 102: addToFile\n" + e.getMessage());
        }
    }

    public String getFileText(){
        try{
            fRead = new FileReader(file);
            scan = new Scanner(fRead);

            String hold = "";
            while (scan.hasNext()){
                hold += scan.nextLine() +"\n";
            }
            return hold;
        } catch(Exception e){
            errorMessage("Error 103: getFileText\n" + e.getMessage());
        }
        return null;
    }

    public ArrayList<String[]> getFile(){
        try{
            fRead = new FileReader(file);
            scan = new Scanner(fRead);

            String[] data;
            while (scan.hasNext()){
                data = scan.nextLine().split("#");
                arr.add(data);
            }
            return arr;
        } catch(Exception e){
            errorMessage("Error 104: getFile\n" + e.getMessage());
        }
        return null;
    }
}
