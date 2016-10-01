/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorysize;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;

/**
 * This class calculates the size of each file and folder in a given directory.
 * It then sorts the list of files/folders in descending order and displays them
 * appropriately
 * 
 * @author Mike Crinite
 * @since 03.16.16
 * @version 0.1 
 */
public class DirectorySize {
    private static ArrayList<Directory> list;
    File f;
    String fPath;
    
    /**
     * Runs all the methods necessary to generate a list of strings to display in 
     * the GUI
     * 
     * @return Formatted String containing relevant information 
     */
    public static String run() {
        // Create JFileChooser 
        File parent = jFileChoose();             // Select directory using filechooser
        DirectorySize d = getSize(parent);       // Runs the function to get d size
        d.sortList();                            // Sort d
        return d.allToString();                  // Return all of d
    }
    
    /**
     * Runs all the methods necessary to generate a list of strings with no GUI
     * 
     * @return Formatted String containing relevant information 
     */
    public static String runNoGui(){
        File parent = new File(commandLineFileChoose());
        DirectorySize d = getSize(parent);
        d.sortList();
        return d.allToString();        
    }
    
    /**
     * Default constructor
     */
    public DirectorySize(){
        list = new ArrayList<>();
    }
    
    /**
     * Adds a directory as a Directory object
     * 
     * @param f The file to add
     * @throws NullPointerException If f is null 
     */
    public static void addD(File f) throws NullPointerException{
        if(f==null){
            throw new NullPointerException();
        }else{
            Directory d = new Directory(f.getName(), folderSize(f)); // folderSize(f));
            list.add(d);
        }
    }
    
    /**
     * Returns the ArrayList
     * 
     * @return The ArrayList field of the object 
     */
    public ArrayList<Directory> getList(){
        return list;
    }
    
    /**
     * Prints all relevant information
     */
    public static void printAll(){
        list.stream().forEach((d) -> {
            System.out.println(d.toString());
        });
    }
    
    /**
     * Converts the information to a string to return to the object
     * 
     * @return The formatted String of the ArrayList
     */
    public static String allToString(){
        String s = "";
        for(Directory d : list){
            s = s + d.toString() + "\n";
        }
                
        return s;
    }
    
    /**
     * Sorts the ArrayList according to the Comparable field.
     * Also replaces the original list with its sorted self.
     * 
     * @return A sorted list in the original list's place 
     */
    public static ArrayList<Directory> sortList(){        
        Collections.sort(list);
        
        return list;
    }
    
    /**
     * Parses the directory for it's length (size in bytes)
     * 
     * @param directory The directory being parsed
     * @return The length (size in bytes) of the directory
     */
    public static long folderSize(File directory){
        long length = 0;
        if(directory.isDirectory()){
            
            for(File file : directory.listFiles()) {
                if(file.isFile()){
                    length += file.length();
                }else{
                    length +=folderSize(file);
                }
            }
        }else{
            length += directory.length();
        }
        return length;
    }
    
    /**
     * A method to use in lieu of a GUI to obtain the target directory.
     * The program will prompt for a file path.
     * 
     * @return The path of the file given via user input 
     */
    public static String commandLineFileChoose(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file directory");
        String path = scanner.nextLine();
        scanner.close();
        
        return path;
    }
    
    /**
     * Creates a jFileChooser so that the user can select the directory via GUI
     * 
     * @return The selected directory as a File object.
     */
    public static File jFileChoose(){
        File parent = new java.io.File(".");
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){
            System.out.println("Path: " + chooser.getCurrentDirectory());
            parent = chooser.getSelectedFile();
        }else{
            System.out.println("No Selection");
            System.exit(0);
        }
        return parent;
    }
    
    /**
     * Utilizes the addD method in order to add each subdirectory to the 
     * ArrayList<Directory> which is stored in the DirectorySize object.
     * 
     * @param parent The directory whose subdirectories are to be sorted
     * @return An ArrayList of Directory objects
     */
    public static DirectorySize getSize(File parent){
        DirectorySize d = new DirectorySize();
        for(File f : parent.listFiles()){
            try{
                d.addD(f);
            }catch(Exception e){
//                System.out.println("Error with file/folder: " + f.getAbsolutePath());
            }
        }
        return d;
    }
    
}
