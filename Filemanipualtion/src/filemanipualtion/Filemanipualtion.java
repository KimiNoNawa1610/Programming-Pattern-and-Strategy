/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanipualtion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 *
 * @author votha
 */
public class Filemanipualtion {
    public static void main(String[] args) throws IOException{
        String source="C:\\Users\\votha\\Desktop\\RandomText.txt";
        String sink="C:\\Users\\votha\\Desktop\\RandomCopy.txt";
        BufferedWriter wr;
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            wr = new BufferedWriter(new FileWriter(sink));
            String line=br.readLine();
            while(line != null){
                wr.write(line);
                wr.newLine();
                line=br.readLine();
                
            }
        }
        wr.close();
            
    }
   
}
    
    

