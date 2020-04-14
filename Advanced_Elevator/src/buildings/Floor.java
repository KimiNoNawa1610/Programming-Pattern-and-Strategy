package buildings;

import elevators.ElevatorObserver;
import passengers.Passenger;
import elevators.Elevator;

import java.util.*;

public class Floor implements ElevatorObserver {
	private Building mBuilding;
	private List<Passenger> mPassengers = new ArrayList<>();
	private ArrayList<FloorObserver> mObservers = new ArrayList<>();
	private int mNumber;
	
	// TODO: declare a field(s) to help keep track of which direction buttons are currently pressed.
	// You can assume that every floor has both up and down buttons, even the ground and top floors.
        
        EnumMap<Elevator.Direction,Boolean> PassengerDirection= new EnumMap<Elevator.Direction,Boolean>(Elevator.Direction.class);
        
	public Floor(int number, Building building) {
		mNumber = number;
		mBuilding = building;
                PassengerDirection.put(Elevator.Direction.MOVING_UP, Boolean.FALSE);
                PassengerDirection.put(Elevator.Direction.MOVING_DOWN, Boolean.FALSE);
                
	}
	
	
	/**
	 * Sets a flag that the given direction has been requested by a passenger on this floor. If the direction
	 * had NOT already been requested, then all observers of the floor are notified that directionRequested.
	 * @param direction
	 */
	public void requestDirection(Elevator.Direction direction) {
		// TODO: implement this method as described in the comment.
                
                if(directionIsPressed(direction)==false){
                    for(FloorObserver n: mObservers){
                        n.directionRequested(this, direction);
                    }
                    PassengerDirection.put(direction, Boolean.TRUE);
                    
                }
                
                
                
                
	}
	
	/**
	 * Returns true if the given direction button has been pressed.
         * @param direction
         * @return 
	 */
	public boolean directionIsPressed(Elevator.Direction direction) {
		// TODO: complete this method.
                
                if(PassengerDirection.get(direction)==true){
                    return true;
                }
		return false;
	}
	
	/**
	 * Clears the given direction button so it is no longer pressed.
         * @param direction
	 */
	public void clearDirection(Elevator.Direction direction) {
		// TODO: complete this method.
                PassengerDirection.remove(direction);
	}
	
	/**
	 * Adds a given Passenger as a waiting passenger on this floor, and presses the passenger's direction button.
         * @param p
	 */
	public void addWaitingPassenger(Passenger p) {
		mPassengers.add(p);
		addObserver(p);
                System.out.println(p);
		p.setState(Passenger.PassengerState.WAITING_ON_FLOOR);
                
		// TODO: call requestDirection with the appropriate direction for this passenger's destination.
                int destination=p.getDestination();
                if(this.getNumber()<destination){
                    requestDirection(Elevator.Direction.MOVING_UP);
                }
                else if(this.getNumber()<destination){
                    requestDirection(Elevator.Direction.MOVING_DOWN);
                }
                
                
                
	}

	/**
	 * Removes the given Passenger from the floor's waiting passengers.
         * @param p
	 */
	public void removeWaitingPassenger(Passenger p) {
                System.out.println("true");
		mPassengers.remove(p);
                System.out.println(this.getWaitingPassengers());
	}
	
	
	// Simple accessors.
	public int getNumber() {
		return mNumber;
	}
	
	public List<Passenger> getWaitingPassengers() {
		return mPassengers;
	}
	
	@Override
	public String toString() {
		return "Floor " + mNumber;
	}
        
	
	// Observer methods.
	public void removeObserver(FloorObserver observer) {
		mObservers.remove(observer);
	}
	
	public void addObserver(FloorObserver observer) {
		mObservers.add(observer);
	}
        
        public ArrayList<FloorObserver> getobserver(){
            return mObservers;
        }
	
	// Observer methods.
	@Override
	public void elevatorDecelerating(Elevator elevator) {
		// TODO: if the elevator is arriving at THIS FLOOR, alert all the floor's observers that elevatorArriving.
		// TODO:    then clear the elevator's current direction from this floor's requested direction buttons.
                if(elevator.getCurrentState()==Elevator.ElevatorState.DECELERATING){
                    for(FloorObserver i:mObservers){
                        i.elevatorArriving(this, elevator);
                    }
                    clearDirection(elevator.getCurrentDirection());
                }
		
	}
	
	@Override
	public void elevatorDoorsOpened(Elevator elevator) {
		// Not needed.
	}
	
	@Override
	public void elevatorWentIdle(Elevator elevator) {
		// Not needed.
	}
}
