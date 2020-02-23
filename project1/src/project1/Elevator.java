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
public class Elevator {
    
    private int current_floor;
    private ArrayList<Integer> passengers=new ArrayList();
    private Current_direction current_direction;
    private Current_state current_state;
    private int number_of_elevator;
    Building building;
    
    public enum Current_direction{
        UP,DOWN,NOT_MOVING
    }
    
    public enum Current_state{
        IDLE_STATE,DOORS_OPENING,UNLOADING_PASSENGERS,LOADING_PASSENGERS,DOORS_CLOSING,
        ACCELERATING,MOVING,DECELERATING
    }
    
    public int getCurrentFloor(){
        return current_floor;
    }
    
    public Current_state getCurrentState(){
        return current_state;
    }
    
    public ArrayList<Integer> getPassengers(){
        return passengers;
    }
    
    public Current_direction getCurrentDirection(){
        return current_direction;
    }
    
    public int getElevator(){
        return number_of_elevator;
    }
   
    public Elevator(int ele, Building build){
        number_of_elevator=ele;
        current_floor=0;
        current_state=Current_state.IDLE_STATE;
        current_direction=Current_direction.NOT_MOVING;
        building=build;
    }
    
    public void tick(){
        
        if(current_state==Current_state.LOADING_PASSENGERS){
            for( int i:building.getFloor(current_floor)){
                passengers.add(i);
                building.getFloor(current_floor).remove(i);
            }
        }
        
        if(current_state==Current_state.DOORS_OPENING){
            for(int i:passengers){
                if(i==getCurrentFloor()){
                    current_state=Current_state.UNLOADING_PASSENGERS;
                   
                }
            }
            if(passengers.isEmpty()&& !building.getFloor(current_floor).isEmpty()){
                current_state=Current_state.LOADING_PASSENGERS;
                
            }
        }
        
        if(current_state==Current_state.IDLE_STATE){
            if(!passengers.isEmpty()){
                current_state=Current_state.ACCELERATING;}
            if(!building.getFloor(current_floor).isEmpty()){
                current_state=Current_state.DOORS_OPENING;
                
            }
        }
        
        
            
        
        }
               
    @Override
    public String toString(){
        return "Elevator "+getElevator()+" - Floor "+getCurrentFloor()+" - "+getCurrentState()+" - "
                +getCurrentDirection()+" - Passengers "+getPassengers();
    }
}

