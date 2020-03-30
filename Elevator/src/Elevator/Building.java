/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import java.util.Random;

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
    
    public ArrayList<ArrayList<Integer>> getFloors(){
        return floors;
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
        Random rand=Simulation.getRandom();
        for(int i=0;i<floors.size();i++){
            int floorNum=i+1;
            int randInt=rand.nextInt(20);
            boolean check =false;
            if(randInt==0){
                int randPassenger;
                while(true){
                    randPassenger=rand.nextInt(floors.size())+1;
                    if(randPassenger!=floorNum){
                        floors.get(i).add(randPassenger);
                        System.out.println("Adding person with destination "+randPassenger+" to floor "+floorNum);
                        
                        check= true;
                        break;
                    }
                }
            }
            if(check==true){
                break;
            }
            
        }
        for(Elevator elevator:Elevators){
                            elevator.tick();
                            
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

        
    
