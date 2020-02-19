/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;

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
        for(int i=1;i<floors.size();i++){
            
        }
    }
    @Override
    public String toString(){
        int i=1;
        while(i<floors.size()){
            i++;
            if(getElevators().get(0).getCurrentFloor()==i){
                return i+": "+"|"+" x "+"|"+"   "+"| "+getFloor(i);
    }
            else if(getElevators().get(1).getCurrentFloor()==i){
                return i+": "+"|"+"   "+"|"+" x "+"| "+getFloor(i);
    }
            else if(getElevators().get(0).getCurrentFloor()==i&&
                    getElevators().get(1).getCurrentFloor()==i){
                return i+": "+"|"+" x "+"|"+" x "+"| "+getFloor(i);
    }    
                
}
         return i+": "+"|"+"   "+"|"+" x "+"| "+getFloor(i);
    }
}
        
    
