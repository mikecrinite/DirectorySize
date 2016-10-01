/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorysize;

/**
 * Stores name and size of a given directory for use in the main class
 * 
 * @author Mike Crinite
 * 03.15.2016
 * @version 0.1
 */
public class Directory implements Comparable {
    private String name;
    private float size;
    
    /**
     * Default constructor. Sets name to "Unnamed folder" and size to 0
     */
    public Directory(){
        setName("");
        setSize(0);
    }
    
    /**
     * Class constructor. Sets name and size to the correct folder name and size
     * 
     * @param nameIn The name of the folder to simulate
     * @param sizeIn The size (in bytes, for now) of the folder to simulate
     */
    public Directory(String nameIn, float sizeIn){
        setName(nameIn);
        setSize(sizeIn);
    }
    
    /**
     * Sets name, for use in this class only
     * 
     * @param nameIn The name of the folder to simulate
     */
    private void setName(String nameIn){
        if(nameIn==null
                ||nameIn.equals("")
                ||nameIn.equals("\\s+")){
            name = "Unnamed folder";
        }else{
            name = nameIn;
        }
    }
    /**
     * Sets size, for use in this class only
     * 
     * @param sizeIn The size of the folder to simulate 
     */
    private void setSize(float sizeIn){
        if(sizeIn>=0){
            size = sizeIn;
        }
    }
    /**
     * Provides the user with the folder name
     * 
     * @return The folder/object's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Provides the user with the folder size
     * 
     * @return The folder's size 
     */
    public float getSize(){
        return size;
    }
    
    /**
     * Determines whether this folder is equal to the target folder
     * 
     * @param d Folder to compare to
     * @return True if equal, false if not.
     */
    public boolean equals(Directory d){
        return (d.getSize()==size && d.getName().equals(name));
    }
    
    /**
     * Overrides compareTo method by comparing folder sizes
     * 
     * @param o Object to compare to.
     * @return -1 if object is larger than o, 0 if the sizes are the same, 1 if object is smaller than o
     */
    @Override
    public int compareTo(Object o){
        if(o instanceof Directory){
            Directory d = (Directory) o;
            if(d.getSize() > size){
                return 1;
            }else if(d.getSize() == size){
                return 0;
            }else if(d.getSize() < size){
                return -1;
            }else{
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    public String displaySize(){
        float displaySize = size;
        String unit = "B";
        if(size > 1000000000){
            displaySize = size / 1024 / 1024/ 1024;
            unit = "GB";
        }else if(size > 1000000){
            displaySize = size / 1024 / 1024;
            unit = "MB";
        }else if(size > 1000){
            displaySize = size / 1024;
            unit = "KB";
        }
        return String.format("%.1f", displaySize) + " " + unit;
    }
    
    /**
     * Neatly formats object data into a readable String
     * 
     * @return String containing object data
     */
    @Override
    public String toString(){
        return name + ": " + displaySize(); 
    }
}
