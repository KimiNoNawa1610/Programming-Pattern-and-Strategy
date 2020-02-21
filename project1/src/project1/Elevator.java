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
   
    public Elevator(int ele){
        number_of_elevator=ele;
        current_floor=1;
        current_state=Current_state.IDLE_STATE;
        current_direction=Current_direction.NOT_MOVING;
    }
    
    public void tick(){
        if(getCurrentState()==Current_state.IDLE_STATE){
            if(!passengers.isEmpty()){
                current_state=Current_state.ACCELERATING;
                if(!building.getFloor(getCurrentFloor()).isEmpty()){
                    current_state=Current_state.DOORS_OPENING;
            }
        }
        }
        if(getCurrentState()==Current_state.DOORS_OPENING){
           for(int i=0;i<getPassengers().size();i++){
               if(getPassengers().get(i).equals(getCurrentFloor())){
                   current_state=Current_state.UNLOADING_PASSENGERS;
                   passengers.remove(i);
                   current_direction=Current_direction.NOT_MOVING;  
               } 
           }
        }
        if(getCurrentDirection()==Current_direction.NOT_MOVING){
           if(!building.getFloor(getCurrentFloor()).isEmpty()&&getPassengers().isEmpty()){
               for(int i=0;i<building.getFloor(getCurrentFloor()).size();i++){
                   if(building.getFloor(getCurrentFloor()).get(i)>=getCurrentFloor()){
                       current_state=Current_state.LOADING_PASSENGERS;
                       passengers.add(building.getFloor(getCurrentFloor()).get(i));
               }
               }
           }
        }
        if(!building.getFloor(getCurrentFloor()).isEmpty()){
        for(int i=0;i<getPassengers().size();i++){
            for(int j=0;j<building.getFloor(getCurrentFloor()).size();j++){
                if(getPassengers().get(i)<= building.getFloor(getCurrentFloor()).get(j)){
                    current_state=Current_state.LOADING_PASSENGERS;
                    passengers.add(building.getFloor(getCurrentFloor()).get(j));
                }
            }
            }
        }
        if(getCurrentState()==Current_state.LOADING_PASSENGERS){
            current_state=Current_state.DOORS_CLOSING;
        }
        if(getCurrentState()==Current_state.UNLOADING_PASSENGERS&&building.getFloor(getCurrentFloor()).isEmpty()){
            current_state=Current_state.DOORS_CLOSING;
        }
        if(getCurrentState()==Current_state.DOORS_CLOSING){
            if(!getPassengers().isEmpty()){
                current_state=Current_state.ACCELERATING;
                for(int i=0;i<getPassengers().size();i++){
                    if(getPassengers().get(i)<getCurrentFloor()){
                        current_direction=Current_direction.UP;
                    }
                    else if(getPassengers().get(i)>getCurrentFloor()){
                        current_direction=Current_direction.DOWN;
                    }
                }
            }
            else{
                current_state=Current_state.IDLE_STATE;
            }
        }
        if(getCurrentState()==Current_state.ACCELERATING){
           current_state=Current_state.MOVING;
           for(int i=0;i<getPassengers().size();i++){
               if(getPassengers().get(i)<getCurrentFloor()){
                   current_floor=current_floor+1;
            }
               else if(getPassengers().get(i)>getCurrentFloor()){
                   current_floor=current_floor-1;
               }
               else if(getPassengers().get(i)==current_floor+1){
                   current_state=Current_state.DECELERATING;
               }
        }        
        }
        if(getCurrentState()==Current_state.DECELERATING){
            current_state=Current_state.DOORS_OPENING;
            if(getPassengers().isEmpty()){
                current_direction=Current_direction.NOT_MOVING;
            }
        }       
    }
    @Override
    public String toString(){
        return "Elevator - "+getElevator()+" - "+getCurrentState()+" - "+getCurrentDirection()+" - "+getPassengers();
    }
}

