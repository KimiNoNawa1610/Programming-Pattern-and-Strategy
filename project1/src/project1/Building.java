/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
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
    public Building(int numele,int floor){
        for(int i=0;i<numele;i++){
            Elevators.add(new Elevator(i));
        }
        for(int i=0;i<floor;i++){
            floors.add(new ArrayList<Integer>());
        }
    }
   
                
    
    public void tick(){
        for(int i=1;i<floors.size();i++){
            int pass=getRandom().nextInt(19);
            if(pass==0){
                ArrayList<Integer> floorlist=new ArrayList<>();
                floorlist.add(getRandom().nextInt(floors.size()));
                floors.add(i, floorlist);
                break;
            }
        }
    }
    @Override
    public String toString(){
        String x=" x |";
        int i=1;
        String z= i+"|";
        while(i<floors.size()-1){
            i++;
            for(int j=0;j<Elevators.size();j++){
                z=z+x;     
            }    
            
}
         return z+getFloor(i);
    }
}

        
    
