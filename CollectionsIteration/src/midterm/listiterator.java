/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author votha
 */
public class listiterator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("C");
        list.add("E");
        list.add("C");
        list.add("S");
        System.out.println("list enhanced for loop");
        for(String i:list){
            System.out.println(i);
        }
        System.out.println(" ");
        System.out.println("list Iterator");
        Iterator<String> listiterator=list.iterator();
        while(listiterator.hasNext()){
            System.out.println(listiterator.next());
        }
        
    }
    
}
