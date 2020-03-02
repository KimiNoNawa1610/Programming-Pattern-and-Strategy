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
    public ArrayList getInput(){
        String input="";
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter a text you want: ");
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
        
        return output;
        
    }
    
    public String userchoice(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter the word you want to know location");
        String userchoice=scan.next();
        return userchoice;
    }
    
    public ArrayList Inverted(ArrayList input, String userchoice){
        TreeMap<String, ArrayList<Integer>> wordmap=new TreeMap<>();
        ArrayList<String> none=new ArrayList<>();
        none.add("your word does not exist");
        int index=0;
        for(Object i: input){
            if(i.toString().length()!=0&&!wordmap.containsKey(i.toString())){
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
            return wordmap.get(userchoice);
        }
        else{
            return none;
        }
    }
    
    
    
    
}
