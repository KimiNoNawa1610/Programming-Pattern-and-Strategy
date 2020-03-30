/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author votha
 */
public class Simulation {
    private static Random mRandom;
    
    public static Random getRandom(){
        return mRandom;
}

public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter the seed number: ");
        int seed=scan.nextInt();
        mRandom=new Random(seed);
        
        
        System.out.println("Please enter the number of floor: ");
        int numfloor=scan.nextInt();
                
        
        System.out.println("Please enter the number of elevator: ");
        int numele=scan.nextInt();
        
        Building building1= new Building(numele,numfloor);
        System.out.println(building1);
        scan.nextLine();
        System.out.println("Simulate how many steps ?");
        int stick=scan.nextInt();
        int i=0;
        boolean run=true;
        while(run){
            i++;
            System.out.println("Step: "+i);
            building1.tick();
            System.out.println(building1);
            if(i==stick){
                run=false;
                System.out.println("Simulate how many steps ?");
                System.out.println(building1);
            }
           
            
        }
        
        
    }
    
}
