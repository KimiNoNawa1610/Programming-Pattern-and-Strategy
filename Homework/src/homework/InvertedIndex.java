/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author votha
 */
public class InvertedIndex {
    /**
     * The get text file input from the user
     * @return the ArrayList contains the words from the text file
     * if the text file is inappropriate, the method will output an error message
     */
    public ArrayList getInput(){
        String input="";
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter a text file you want: ");
        File file=new File(scan.next());
        try {
            Scanner filescan=new Scanner(file);
            while(filescan.hasNextLine()){
                input=input+filescan.nextLine().replaceAll("[^a-zA-Z0-9]"," ").toLowerCase();
            
        }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InvertedIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> output=new ArrayList<>(Arrays.asList(input.split(" ")));
        for(int i=0;i<output.size();i++){
            if(output.get(i).length()==0){
                output.remove(i);
            }
        }
        System.out.println(output);
        return output;
        
    }
    
    /**
     * The get user choice of word that they want to know the location of
     * @return the word that the user chose.
     */
    public String userchoice(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter the word you want to know location");
        String userchoice=scan.next();
        return userchoice;
    }
    
    /**
     * 
     * @param input
     * @param userchoice
     * @return 
     */
    public ArrayList Inverted(ArrayList input, String userchoice){
        TreeMap<String, ArrayList<Integer>> wordmap=new TreeMap<>();
        ArrayList<String> none=new ArrayList<>();
        none.add("your word does not exist");
        int index=0;
        for(Object i: input){
            if(!wordmap.containsKey(i.toString())){
                index++;
                wordmap.put(i.toString(),new ArrayList<Integer>());
                wordmap.get(i).add(index);
            }
            else if(wordmap.containsKey(i.toString())){
                index++;
                wordmap.get(i).add(index);
            }
            
        }
        if(wordmap.containsKey(userchoice)){
            System.out.print("Your word is at location(s): ");
            return wordmap.get(userchoice);
        }
        else{
            return none;
        }
    }
    
    
    
    
}
