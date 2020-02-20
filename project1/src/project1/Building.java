/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import java.util.Random;
import static project1.Simulation.getRandom;

/**
 *
 * @author votha
 */
public class Building{
    
    private ArrayList<Elevator> Elevators=new ArrayList();
    private ArrayList<ArrayList<Integer>> floors=new ArrayList();
    
    
    public ArrayList<Integer> getFloor(int floorNumber){
        return floors.get(floorNumber);
    }
    
    public ArrayList<Elevator> getElevators(){
        return Elevators;
    }
    
    public String getElevatoroffloor(){
        return "x";
    }
    
    public void tick(){
        Random random=new Random();
        for(int i=1;i<floors.size();i++){
            ;
        }
    }
    @Override
    public String toString(){
        String x=" x |";
        int i=1;
        String z= i+"|";
        while(i<floors.size()){
            i++;
            for(int j=0;j<Elevators.size();j++){
                z=z+x;     
            }     
}
         return z+getFloor(i);
    }
}
        
    
