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
        
        Building building= new Building(numele,numfloor);
        System.out.println(building);
        
    }
    
}
