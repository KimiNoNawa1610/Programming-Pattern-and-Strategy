/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import java.util.Objects;

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
        ArrayList<Integer> Pickup=new ArrayList<>();
        ArrayList<Integer> Drop=new ArrayList<>();
        
        int execution=1;    
        
        
        if(current_state==Current_state.UNLOADING_PASSENGERS){
            if(execution==1){
                if(building.getFloor(current_floor).isEmpty()){
                    current_state=Current_state.DOORS_CLOSING;
                    execution=0;
                }
                for(int i=0;i<passengers.size();i++){
                    if(passengers.get(i)==current_floor+1){
                        Drop.add(passengers.get(i));
                        current_state=Current_state.DOORS_CLOSING;
                        execution=0;
                    }
                }
                for(int j=0;j<Drop.size();j++){
                    passengers.remove(Drop.get(j));
                }
                Drop.clear();
                if(passengers.isEmpty()){
                    current_direction=Current_direction.NOT_MOVING;
                    if(!building.getFloor(current_floor).isEmpty()){
                        current_state=Current_state.LOADING_PASSENGERS;
                        execution=0;
                    }
                }
                if(!passengers.isEmpty()){
                    for(int i=0;i<building.getFloor(current_floor).size();i++){
                            if(current_direction==Current_direction.DOWN){
                                if(building.getFloor(current_floor).get(i)<current_floor+1){
                                    current_state=Current_state.LOADING_PASSENGERS;
                                        execution=0;
                    }
                }
                            if(current_direction==Current_direction.UP){
                                if(building.getFloor(current_floor).get(i)>current_floor+1){
                                    current_state=Current_state.LOADING_PASSENGERS;
                                        execution=0;
                    }
                }
            }
                }
                
                
        }
        }
        
        if(current_state==Current_state.DECELERATING){
            if(execution==1){
            if(passengers.isEmpty()){
                current_direction=Current_direction.NOT_MOVING;
                execution=0;
            }
            else{
                current_state=Current_state.DOORS_OPENING;
                execution=0;
                    
                }
                
            }
        }
        
        
        if(current_state==Current_state.MOVING){
            if(execution==1){
            if(current_direction==Current_direction.UP){
                current_floor+=1;
            }
            if(current_direction==Current_direction.DOWN){
                current_floor-=1;
            }
            for(int i=0;i<passengers.size();i++){
                if(passengers.get(i)==current_floor+1){
                    current_state=Current_state.DECELERATING;
                    execution=0;
                }
                for(int sameway:building.getFloor(current_floor)){
                    if(current_direction==Current_direction.DOWN){
                    if(passengers.get(i)>=sameway){
                        current_state=Current_state.DECELERATING;
                        execution=0;
                        
                    }
                    }
                    if(current_direction==Current_direction.UP){
                    if(passengers.get(i)<=sameway){
                        current_state=Current_state.DECELERATING;
                        execution=0;
                        
                    }
                    }
                }
            }
        }
        }
        
        if(current_state==Current_state.ACCELERATING){
            if(execution==1){
            current_state=Current_state.MOVING;
            execution=0;
            }
        }
        
        if(current_state==Current_state.DOORS_CLOSING){
            if(execution==1){
            if(!passengers.isEmpty()){
                current_state=Current_state.ACCELERATING;
                execution=0;
            }
            else{
                current_state=Current_state.IDLE_STATE;
                execution=0;
            }
            
            }
        }
        
        if(current_state==Current_state.LOADING_PASSENGERS){
            if(execution==1){
            if(current_direction==Current_direction.NOT_MOVING && !building.getFloor(current_floor).isEmpty()){
                passengers.add(building.getFloor(current_floor).get(0));
                building.getFloor(current_floor).remove(0);
                if(passengers.get(0)>current_floor+1){
                    current_direction=Current_direction.UP;
                    current_state=Current_state.DOORS_CLOSING;
                    execution=0;
                }
            
                else if(passengers.get(0)<current_floor+1){
                    current_direction=Current_direction.DOWN;
                    current_state=Current_state.DOORS_CLOSING;
                    execution=0;
                } 
            }
            if(!passengers.isEmpty()){
                for(int i=0;i<building.getFloor(current_floor).size();i++){
                if(current_direction==Current_direction.DOWN){
                    if(building.getFloor(current_floor).get(i)<current_floor+1){
                        passengers.add(building.getFloor(current_floor).get(i));
                        Pickup.add(building.getFloor(current_floor).get(i));
                        current_state=Current_state.DOORS_CLOSING;
                        execution=0;
                        
                    }
                }
                
                if(current_direction==Current_direction.UP){
                    if(building.getFloor(current_floor).get(i)>current_floor+1){
                        passengers.add(building.getFloor(current_floor).get(i));
                        Pickup.add(building.getFloor(current_floor).get(i));
                        current_state=Current_state.DOORS_CLOSING;
                        execution=0;
                    }
                }
            }
                
            for(int j=0;j<Pickup.size();j++){
                    building.getFloor(current_floor).remove(Pickup.get(j));
                }
            Pickup.clear();
            }
                
            
            else{
                current_state=Current_state.DOORS_CLOSING;
                execution=0;
            }
        }
        }
        
        if(current_state==Current_state.DOORS_OPENING){
            if(execution==1){
                for(int i:passengers){
                for(int j:building.getFloor(current_floor)){
                    if(i==j){
                        current_state=Current_state.LOADING_PASSENGERS;
                        execution=0;
                    }
                }
                }
            for(int i:passengers){
                if(i==getCurrentFloor()+1){
                    current_state=Current_state.UNLOADING_PASSENGERS;
                    execution=0;
                }
            }
            if(passengers.isEmpty()&& !building.getFloor(current_floor).isEmpty()){
                current_state=Current_state.LOADING_PASSENGERS;
                execution=0;
                
            }
            
            }
        }
        
        if(current_state==Current_state.IDLE_STATE){
            if(execution==1){
            if(!passengers.isEmpty()){
                current_state=Current_state.ACCELERATING;
                execution=0;
            }
            if(!building.getFloor(current_floor).isEmpty()){
                current_state=Current_state.DOORS_OPENING;
                execution=0;
            }
        }
        }
        
            
        
        }
               
    @Override
    public String toString(){
        return "Elevator "+(getElevator()+1)+" - Floor "+(getCurrentFloor()+1)+" - "+getCurrentState()+" - "
                +getCurrentDirection()+" - Passengers "+getPassengers();
    }
}

