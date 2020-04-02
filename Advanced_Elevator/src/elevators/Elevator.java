package elevators;

import buildings.Building;
import buildings.Floor;
import buildings.FloorObserver;
import events.ElevatorStateEvent;
import passengers.Passenger;

import java.util.*;
import java.util.stream.Collectors;

public class Elevator implements FloorObserver {
	
	public enum ElevatorState {
		IDLE_STATE,
		DOORS_OPENING,
		DOORS_CLOSING,
		DOORS_OPEN,
		ACCELERATING,
		DECELERATING,
		MOVING
	}
	
	public enum Direction {
		NOT_MOVING,
		MOVING_UP,
		MOVING_DOWN
	}
	
	
	private int mNumber;
	private Building mBuilding;

	private ElevatorState mCurrentState = ElevatorState.IDLE_STATE;
	private Direction mCurrentDirection = Direction.NOT_MOVING;
	private Floor mCurrentFloor;
	private List<Passenger> mPassengers = new ArrayList<>();
	
	private List<ElevatorObserver> mObservers = new ArrayList<>();
	
	// TODO: declare a field to keep track of which floors have been requested by passengers.
	private int nextRequestUp(int FromFloor){
            int smallestmax=-1;
            for (Passenger i: this.mPassengers){
                if(i.getDestination()>FromFloor){
                    smallestmax=i.getDestination();
                }
            }
            return smallestmax;
        }
        
        private int nextRequestDown(int FromFloor){
            int biggestmin=-1;
            for (Passenger i: this.mPassengers){
                if(i.getDestination()<FromFloor){
                     biggestmin=i.getDestination();
                }
                
            }
            return biggestmin;
        }
        
	private int RequestedFloor;
        
	public Elevator(int number, Building bld) {
		mNumber = number;
		mBuilding = bld;
		mCurrentFloor = bld.getFloor(1);
		
		scheduleStateChange(ElevatorState.IDLE_STATE, 0);
	}
	
	/**
	 * Helper method to schedule a state change in a given number of seconds from now.
	 */
	private void scheduleStateChange(ElevatorState state, long timeFromNow) {
		Simulation sim = mBuilding.getSimulation();
		sim.scheduleEvent(new ElevatorStateEvent(sim.currentTime() + timeFromNow, state, this));
	}
	
	/**
	 * Adds the given passenger to the elevator's list of passengers, and requests the passenger's destination floor.
         * @param passenger
	 */
	public void addPassenger(Passenger passenger) {
		// TODO: add the passenger's destination to the set of requested floors.
                RequestedFloor=passenger.getDestination();
		mPassengers.add(passenger);
	}
	
	public void removePassenger(Passenger passenger) {
		mPassengers.remove(passenger);
	}
        
        
	
	
	/**
	 * Schedules the elevator's next state change based on its current state.
	 */
	public void tick() {
		// TODO: port the logic of your state changes from Project 1, accounting for the adjustments in the spec.
		// TODO: State changes are no longer immediate; they are scheduled using scheduleStateChange().
		
		// Example of how to trigger a state change:
		// scheduleStateChange(ElevatorState.MOVING, 3); // switch to MOVING and call tick(), 3 seconds from now.
                
	}
	
	
	/**
	 * Sends an idle elevator to the given floor.
         * @param floor
	 */
	public void dispatchTo(Floor floor) {
		// TODO: if we are currently idle and not on the given floor, change our direction to move towards the floor.
		// TODO: set a floor request for the given floor, and schedule a state change to ACCELERATING immediately.
                if(this.isIdle()==true&& this.getCurrentFloor().getNumber()!=floor.getNumber()){
                    if(this.getCurrentFloor().getNumber()<floor.getNumber()){
                        this.mCurrentDirection=Direction.MOVING_UP;
                    }
                    else{
                        this.mCurrentDirection=Direction.MOVING_DOWN;
                    }
                    int Accelerationtime=2;
                    scheduleStateChange(ElevatorState.ACCELERATING,Accelerationtime);
                }
		
	}
	
	// Simple accessors
	public Floor getCurrentFloor() {
		return mCurrentFloor;
	}
	
	public Direction getCurrentDirection() {
		return mCurrentDirection;
	}
	
	public Building getBuilding() {
		return mBuilding;
	}
	
	/**
	 * Returns true if this elevator is in the idle state.
	 * @return
	 */
	public boolean isIdle() {
		// TODO: complete this method.
                if(mPassengers.isEmpty()==true){
                    return true;
                }
		return false;
	}
	
	// All elevators have a capacity of 10, for now.
	public int getCapacity() {
		return 10;
	}
	
	public int getPassengerCount() {
		return mPassengers.size();
	}
	
	// Simple mutators
	public void setState(ElevatorState newState) {
		mCurrentState = newState;
	}
	
	public void setCurrentDirection(Direction direction) {
		mCurrentDirection = direction;
	}
	
	public void setCurrentFloor(Floor floor) {
		mCurrentFloor = floor;
	}
	
	// Observers
	public void addObserver(ElevatorObserver observer) {
		mObservers.add(observer);
	}
	
	public void removeObserver(ElevatorObserver observer) {
		mObservers.remove(observer);
	}
	
	
	// FloorObserver methods
	@Override
	public void elevatorArriving(Floor floor, Elevator elevator) {
		// Not used.
	}
	
	/**
	 * Triggered when our current floor receives a direction request.
	 */
	@Override
	public void directionRequested(Floor sender, Direction direction) {
		// TODO: if we are currently idle, change direction to match the request. Then alert all our observers that we are decelerating,
		// TODO: then schedule an immediate state change to DOORS_OPENING.
                
                if(this.isIdle()){
                    int DoorOppeningTime=2;
                    this.mCurrentDirection=direction;
                    for(ElevatorObserver n: mObservers){
                        n.elevatorDecelerating(this);
                    }
                    this.scheduleStateChange(ElevatorState.DOORS_OPENING, DoorOppeningTime);
                }
	}
	
	
	
	
	// Voodoo magic.
	@Override
	public String toString() {
		return "Elevator " + mNumber + " - " + mCurrentFloor + " - " + mCurrentState + " - " + mCurrentDirection + " - "
		 + "[" + mPassengers.stream().map(p -> Integer.toString(p.getDestination())).collect(Collectors.joining(", "))
		 + "]";
	}
	
}
