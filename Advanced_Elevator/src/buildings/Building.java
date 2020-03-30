package buildings;

import elevators.Simulation;
import elevators.Elevator;
import elevators.ElevatorObserver;

import java.util.*;

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
        for(int z=0;z<mElevators.size();z++){
            y.add("|  |");
        }
        for(Floor i:mFloors){
            for(int z=0;z<mElevators.size();z++){
                if(i==mElevators.get(z).getCurrentFloor()){
                    y.set(z, "| x |");
                }
                else{y.set(z,"|   |");}
            }
                if(i.getNumber()+1>=10){
                    x.add((i.getNumber()+1)+ ": "+String.join("",y)+i.getWaitingPassengers()+"\n");
                }
                else{
                    x.add(" "+(i.getNumber()+1)+": "+String.join("",y)+i.getWaitingPassengers()+"\n");
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
		// TODO: if mWaitingFloors is not empty, remove the first entry from the queue and dispatch the elevator to that floor.
                int FirstEntry=mWaitingFloors.peek();
                if(mWaitingFloors.isEmpty()!=true){
                    elevator.dispatchTo(mFloors.get(FirstEntry));
                    mWaitingFloors.remove(FirstEntry);
                    
                }
	}
	
	@Override
	public void elevatorArriving(Floor sender, Elevator elevator) {
		// TODO: add the floor mWaitingFloors if it is not already in the queue.
                if(mWaitingFloors.contains(sender)!=true){
                    mWaitingFloors.add(sender.getNumber());
                }
	}
	
	@Override
	public void directionRequested(Floor floor, Elevator.Direction direction) {
		// TODO: go through each elevator. If an elevator is idle, dispatch it to the given floor.
		// TODO: if no elevators are idle, then add the floor number to the mWaitingFloors queue.
                for(Elevator ele:mElevators){
                    if(ele.isIdle()==true){
                        ele.dispatchTo(floor);
                    }
                    else{
                        mWaitingFloors.add(floor.getNumber());
                    }
                }
		
	}
}
