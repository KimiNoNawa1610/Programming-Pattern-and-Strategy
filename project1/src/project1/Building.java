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
            Elevators.add(new Elevator(i, this));
        }
        for(int i=0;i<floor;i++){
            floors.add(new ArrayList<Integer>());
        }
    }
   
                
    
    public void tick(){
        for(int i=0;i<=floors.size()-1;i++){
            int pass=getRandom().nextInt(20);
            if(pass==0){
                int random=(getRandom().nextInt(floors.size())+1);
                if(random!=i+1){
                    floors.get(i).add(random);
                    System.out.println("Adding person with destination "+ random+" to floor "+(i+1));
                }
                break;
            }
            
        }
        for(Elevator y: Elevators){
            y.tick();
        }
        
        
        
        
    }
    @Override
    public String toString(){
        ArrayList<String> x=new ArrayList<>();
        ArrayList<String> y=new ArrayList<>();
        for(int z=0;z<Elevators.size();z++){
            y.add("|  |");
        }
        for(int i=floors.size()-1;i>=0;i--){
            for(int z=0;z<Elevators.size();z++){
                if(Elevators.get(z).getCurrentFloor()==i){
                    y.set(z, "| x |");
                }
                else{y.set(z,"|   |");}
            }
                if(i+1>=10){
                    x.add((i+1)+ ": "+String.join("",y)+floors.get(i)+"\n");
                }
                else{
                    x.add(" "+(i+1)+": "+String.join("",y)+floors.get(i)+"\n");
                }
        }
        
       
        for(Elevator z:Elevators){
            x.add(z.toString()+"\n");
        }
        String output= String.join("",x);
        return output;
        
    }
}

        
    
