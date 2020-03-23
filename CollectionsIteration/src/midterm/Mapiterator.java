/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author votha
 */
public class Mapiterator {
    public static void main(String[] args) {
        TreeMap<String, String> map=new TreeMap<>();
        map.put("Nhan","1610");
        map.put("Trinh","2512");
        map.put("Danh","1608");
        map.put("Huong","2605");
        
        System.out.println("map enhanced for loop");
        
        for(Map.Entry<String, String> entry:map.entrySet()){
            System.out.println("key: "+entry.getKey()+ " value: "+entry.getValue());
            
        }
        System.out.println(" ");
        System.out.println("map iterator");
        
        Iterator<Map.Entry<String,String>> mapiterator=map.entrySet().iterator();
        
        while(mapiterator.hasNext()){
            System.out.println(mapiterator.next());
        }
    }
}
