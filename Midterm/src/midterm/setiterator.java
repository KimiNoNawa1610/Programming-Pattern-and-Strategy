/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author votha
 */
public class setiterator {
     public static void main(String[] args) {
         HashSet<String> string=new HashSet<>();
         string.add("C");
         string.add("E");
         string.add("A");
         string.add("S");
         
         System.out.println("set enhanced for loop");
         
         for(String i:string){
             System.out.println(i);
         }
         
         System.out.println("set iterator");
         
         Iterator<String> setiterator= string.iterator();
         while(setiterator.hasNext()){
             System.out.println(setiterator.next());
         }
         
     }
}
