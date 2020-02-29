/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;
import java.util.Scanner;

/**
 *
 * @author votha
 */
public class Lab2 {

    public static void main(String[] args){
        
        
        int i=1;
        String s="Hello";
        Scanner input= new Scanner(System.in);
        
        System.out.println(i+" "+ s);
        
        doSonething(i,s);
        
        if(true){
            int j=i;
            j+=1;
            String t=s;
            t+=" World ";
            
        }
        
        System.out.println(i+" "+ s);
        
        for (int j=0;j<2;j++){
            i+=1;
            s+=" World "; 
    }
        
        System.out.println(i+" "+ s);
           }
    public static void doSonething(int i, String s){
        i+=1;
        s+=" World ";
        
        System.out.println(i+" "+ s);
    }
    
}
