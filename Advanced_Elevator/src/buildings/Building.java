package buildings;

import elevators.Simulation;
import elevators.Elevator;
import elevators.ElevatorObserver;

import java.util.*;
import passengers.Passenger;

public class Building implements ElevatorObserver, FloorObserver {
	private List<Elevator> mElevators = new ArrayList<>();
	private List<Floor> mFloors = new ArrayList<>();
	private Simulation mSimulation;
	private Queue<Integer> mWaitingFloors = new ArrayDeque<>();
	
        
	public Building(int floors, int elevatorCount, Simulation sim) {
		mSimulation = sim;
		
		// Construct the floors, and observe each one.
		for (int i = 0; i < floors; i++) {
			Floor f = new Floor(i + 1, this);
			f.addObserver(this);
			mFloors.add(f);
		}
                
		
		// Construct the elevators, and observe each one.
		for (int i = 0; i < elevatorCount; i++) {
                    
			Elevator elevator = new Elevator(i + 1, this);
			elevator.addObserver(this);
			for (Floor f : mFloors) {
                                
				elevator.addObserver(f);
			}
			mElevators.add(elevator);
		}
	}
	

	// TODO: recreate your toString() here.
        @Override
	public String toString(){
        ArrayList<String> x=new ArrayList<>();
        ArrayList<String> y=new ArrayList<>();
        ArrayList<Integer> des=new ArrayList<>();
        for(int z=0;z<mElevators.size();z++){
            y.add("|  |");
        }
        for(int i=mFloors.size()-1;i>=0;i--){
            for(int z=0;z<mElevators.size();z++){
                if(mElevators.get(z).getCurrentFloor().getNumber()-1==i){
                    y.set(z, "| x |");
                }
                else{y.set(z,"|   |");}
            }
                if(i+1>=10){
                    x.add((i+1)+ ": "+String.join("",y)+mFloors.get(i).getDestinations()+"\n");
                }
                else{
                    x.add(" "+(i+1)+": "+String.join("",y)+mFloors.get(i).getDestinations()+"\n");
                }
        }
        
       
        for(Elevator z:mElevators){
            x.add(z.toString()+"\n");
        }
        String output= String.join("",x);
        return output;    
    
        }
	
	public int getFloorCount() {
		return mFloors.size();
	}
	
	public Floor getFloor(int floor) {
		return mFloors.get(floor - 1);
	}
	
	public Simulation getSimulation() {
		return mSimulation;
	}
	
        public Queue<Integer> GetWaitingFloor(){
            return mWaitingFloors;
        }
	
	@Override
	public void elevatorDecelerating(Elevator elevator) {
		// Have to implement all interface methods even if we don't use them.
                
	}
	
	@Override
	public void elevatorDoorsOpened(Elevator elevator) {
		// Don't care.
	}
	
	@Override
	public void elevatorWentIdle(Elevator elevator) {
                //System.out.println(mWaitingFloors.size());
		// TODO: if mWaitingFloors is not empty, remove the first entry from the queue and dispatch the elevator to that floor.
                if(mWaitingFloors.isEmpty()!=true){
                    int FirstEntry=mWaitingFloors.peek();
                    for(Elevator ele:mElevators){
                        if(ele.getCurrentFloor().getNumber()==FirstEntry){
                    //System.out.println("Current Waiting Floor: "+FirstEntry);
                    //System.out.println("Elevator Current Floor:"+ elevator.getCurrentFloor().getNumber());
                            ele.dispatchTo(mFloors.get(FirstEntry-1));
                            mWaitingFloors.remove(FirstEntry);}
                        else{
                            elevator.dispatchTo(mFloors.get(FirstEntry-1));
                            mWaitingFloors.remove(FirstEntry);}
                    //System.out.println(elevator.getObserver().size());
                    //System.out.println("Buidling: Elevator went idle");
                    
                }
                }
	}
	
	@Override
	public void elevatorArriving(Floor sender, Elevator elevator) {
		// TODO: add the floor mWaitingFloors if it is not already in the queue.
                //System.out.println("Buidling: Elevator arriving");
                //System.out.println(elevator.getObserver().size());
                if(sender.getWaitingPassengers().isEmpty()==false){
                    if(mWaitingFloors.contains(sender)!=true){
                        mWaitingFloors.add(sender.getNumber());
                    }
                }
                //System.out.println("Buidling: Elevator arriving");
                //System.out.println("Elevator direction: "+elevator.getCurrentDirection());
                //System.out.println(elevator.getObserver().size());

	}
	
	@Override
	public void directionRequested(Floor floor, Elevator.Direction direction) {
		// TODO: go through each elevator. If an elevator is idle, dispatch it to the given floor.
		// TODO: if no elevators are idle, then add the floor number to the mWaitingFloors queue.
                for(Elevator ele:mElevators){
                    if(ele.isIdle()==true){
                        ele.dispatchTo(floor);
                        //ele.setCurrentDirection(direction);
                    }
                    
                    else{
                        mWaitingFloors.add(floor.getNumber());
                    }  
                }
                
		
	}
}
