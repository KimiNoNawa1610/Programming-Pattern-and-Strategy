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
                    x.add((i+1)+ ": "+String.join("",y)+mFloors.get(i).getUpDownIcon()+mFloors.get(i).getShortPassenger()
                            +" Destination Request: "+mFloors.get(i).getDestinations()+"\n");
                }
                else{
                    x.add(" "+(i+1)+": "+String.join("",y)+mFloors.get(i).getUpDownIcon()+mFloors.get(i).getShortPassenger()
                            +" Destination Request: "+mFloors.get(i).getDestinations()+"\n");
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
                if(mWaitingFloors.isEmpty()!=true){
                    int FirstEntry=mWaitingFloors.peek();
                    elevator.getRequestedFloor()[FirstEntry-1]=true;
                    if(elevator.getCurrentFloor().getNumber()>FirstEntry){
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_DOWN);
                    }
                    else if(elevator.getCurrentFloor().getNumber()<FirstEntry){
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_UP);
                    }
                    elevator.getOperation().dispatchToFloor(elevator, this.getFloor(FirstEntry),this.getFloor(FirstEntry).getFloorDirection());
                }
        }
	
	
	@Override
	public void elevatorArriving(Floor sender, Elevator elevator) {
                if(sender.getWaitingPassengers().isEmpty()==false){
                    if(mWaitingFloors.contains(sender)!=true){
                        mWaitingFloors.add(sender.getNumber());
                    }
                }
                
	}
	
	@Override
	public void directionRequested(Floor floor, Elevator.Direction direction) {
		// TODO: go through each elevator. If an elevator is idle, dispatch it to the given floor.
		// TODO: if no elevators are idle, then add the floor number to the mWaitingFloors queue.
                for(Elevator ele:mElevators){
                    if(ele.getOperation().canBeDispatchedToFloor(ele, floor)==true){
                        ele.getOperation().dispatchToFloor(ele, floor, direction);
                        break;
                    }
                    else{
                        mWaitingFloors.add(floor.getNumber());
                    }  
                }
                
		
	}
}
