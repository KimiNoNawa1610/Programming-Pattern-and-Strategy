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
	private boolean[] RequestedFloor;
        
	public Elevator(int number, Building bld) {
                mBuilding = bld;
                this.RequestedFloor = new boolean[mBuilding.getFloorCount()];
		mNumber = number;
                for(int i=0;i<RequestedFloor.length;i++){
                    RequestedFloor[i]=false;
                }
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
	 */
	public void addPassenger(Passenger passenger) {
		// TODO: add the passenger's destination to the set of requested floors.
		mPassengers.add(passenger);
                RequestedFloor[passenger.getDestination()-1]=true;
                
	}
	
	public void removePassenger(Passenger passenger) {
		mPassengers.remove(passenger);
                //System.out.print("Elevator: remove passenger");
	}
	
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
	/**
	 * Schedules the elevator's next state change based on its current state.
	 */
	public void tick() {
            //System.out.println(Arrays.toString(RequestedFloor));
            List<ElevatorObserver> cache=new ArrayList<>(mObservers);
            //System.out.println("Elevator: tick() ");
            // TODO: port the logic of your state changes from Project 1, accounting for the adjustments in the spec.
            // TODO: State changes are no longer immediate; they are scheduled using scheduleStateChange().
            // Example of how to trigger a state change:
            // scheduleStateChange(ElevatorState.MOVING, 3); // switch to MOVING and call tick(), 3 seconds from now.
            switch (mCurrentState) {
                case IDLE_STATE:
                    if(!mCurrentFloor.getobserver().contains(this)){
                    this.mCurrentFloor.addObserver(this);
                    }
                    mObservers.forEach((i) -> {
                        i.elevatorWentIdle(this);
                    }); 
                    break;
                case DOORS_OPENING:
                    scheduleStateChange(ElevatorState.DOORS_OPEN,2);
                    break;
                case DOORS_OPEN:
                    int PreviousPassengerOntheFloor= this.mCurrentFloor.getWaitingPassengers().size();
                    int PreviousPassengerOntheElevator= this.mPassengers.size();
                    for(ElevatorObserver i:cache){
                        i.elevatorDoorsOpened(this);
                    }
                    int CurrentPassengerOntheFloor= this.mCurrentFloor.getWaitingPassengers().size();
                    int CurrentPassengerOntheElevator=this.mPassengers.size();
                  
                    int PassengerChangeCount=Math.abs(PreviousPassengerOntheFloor-CurrentPassengerOntheFloor)+
                            PreviousPassengerOntheElevator-CurrentPassengerOntheElevator+
                            Math.abs(PreviousPassengerOntheFloor-CurrentPassengerOntheFloor);
                    //System.out.println(PassengerChangeCount);
                    int x=PassengerChangeCount/2;
                    
                    scheduleStateChange(ElevatorState.DOORS_CLOSING,1+x);
                    
                    break;
                case DOORS_CLOSING:
                    if(null==mCurrentDirection){
                        mCurrentDirection=Direction.NOT_MOVING;
                        scheduleStateChange(ElevatorState.IDLE_STATE,2);
                    }
                    else switch (mCurrentDirection) {
                case MOVING_DOWN:
                    if(nextRequestDown(mCurrentFloor.getNumber())!=-1){
                        scheduleStateChange(ElevatorState.ACCELERATING,2);
                    }
                    else if(nextRequestUp(mCurrentFloor.getNumber())!=-1){
                        this.setCurrentDirection(Direction.MOVING_UP);
                        scheduleStateChange(ElevatorState.DOORS_OPENING,2);
                    }
                    else{
                        this.setCurrentDirection(Direction.NOT_MOVING);
                        scheduleStateChange(ElevatorState.IDLE_STATE,2);
                    }
                    break;
                case MOVING_UP:
                    if(nextRequestUp(mCurrentFloor.getNumber())!=-1){
                        scheduleStateChange(ElevatorState.ACCELERATING,2);
                    }
                    else if(nextRequestDown(mCurrentFloor.getNumber())!=-1){
                        this.setCurrentDirection(Direction.MOVING_DOWN);
                        scheduleStateChange(ElevatorState.DOORS_OPENING,2);
                    }
                    else{
                        this.setCurrentDirection(Direction.NOT_MOVING);
                        scheduleStateChange(ElevatorState.IDLE_STATE,2);
                    }
                    break;
                default:
                    mCurrentDirection=Direction.NOT_MOVING;
                    scheduleStateChange(ElevatorState.IDLE_STATE,2);
                    break;
            }
                    break;
                case ACCELERATING:
                    //System.out.println("Elevator accelerating");
                    this.mCurrentFloor.removeObserver(this);
                    scheduleStateChange(ElevatorState.MOVING,3);
                    break;
                case MOVING:
                    if(mCurrentDirection==Direction.MOVING_UP){
                       //System.out.println(Arrays.toString(RequestedFloor));
                       setCurrentFloor(mBuilding.getFloor(mCurrentFloor.getNumber()+1));
                       if(RequestedFloor[this.mCurrentFloor.getNumber()-1]==true||mCurrentFloor.directionIsPressed(mCurrentDirection)){
                           scheduleStateChange(ElevatorState.DECELERATING,2);
                       }
                       else{
                            scheduleStateChange(ElevatorState.MOVING,2);
                    }  
                       
                      
                    }
                    else if(mCurrentDirection==Direction.MOVING_DOWN){
                        //System.out.println(Arrays.toString(RequestedFloor));
                        setCurrentFloor(mBuilding.getFloor(mCurrentFloor.getNumber()-1));
                        if(RequestedFloor[this.mCurrentFloor.getNumber()-1]==true||mCurrentFloor.directionIsPressed(mCurrentDirection)){
                           scheduleStateChange(ElevatorState.DECELERATING,2);
                        }
                        
                        else{
                             scheduleStateChange(ElevatorState.MOVING,2);
                    }  
                       
                    }  
                    
                    break;
                case DECELERATING:
                    RequestedFloor[mCurrentFloor.getNumber()-1]=false;
                    //System.out.println(Arrays.toString(RequestedFloor));
                    if(!mCurrentFloor.directionIsPressed(mCurrentDirection)){
                        if(mCurrentDirection==Direction.MOVING_UP){
                            if(mCurrentFloor.directionIsPressed(Direction.MOVING_UP)||nextRequestUp(this.mCurrentFloor.getNumber())!=-1){
                                mCurrentDirection=Direction.MOVING_UP;
                            }
                            else if(mCurrentFloor.directionIsPressed(Direction.MOVING_DOWN)&& nextRequestUp(this.mCurrentFloor.getNumber())==-1){
                                mCurrentDirection=Direction.MOVING_DOWN;
                            }
                            else{
                                mCurrentDirection=Direction.NOT_MOVING;
                            }
                            
                        }
                        else if(mCurrentDirection==Direction.MOVING_DOWN){
                            //System.out.println(nextRequestDown(this.mCurrentFloor.getNumber()));
                            if(mCurrentFloor.directionIsPressed(Direction.MOVING_DOWN)||nextRequestDown(this.mCurrentFloor.getNumber())!=-1){
                                mCurrentDirection=Direction.MOVING_DOWN;
                            }
                            else if(mCurrentFloor.directionIsPressed(Direction.MOVING_UP)&& nextRequestDown(this.mCurrentFloor.getNumber())==-1){
                                mCurrentDirection=Direction.MOVING_UP;
                            }
                            else{
                                mCurrentDirection=Direction.NOT_MOVING;
                            }
                    }
                    }
            
                    
                    for(ElevatorObserver i:cache){
                        i.elevatorDecelerating(this);
                    }
                    scheduleStateChange(ElevatorState.DOORS_OPENING,3);
                    //System.out.println("1");
                    break;
                default:
                    break;
            }
        }
            
            
        
        
	
	
	/**
	 * Sends an idle elevator to the given floor.
         * @param floor
	 */
	public void dispatchTo(Floor floor) {
           
		// TODO: if we are currently idle and not on the given floor, change our direction to move towards the floor.
		// TODO: set a floor request for the given floor, and schedule a state change to ACCELERATING immediately.
		if(this.isIdle()&& mCurrentFloor.equals(floor)==false){
                    RequestedFloor[floor.getNumber()-1]=true;
                    scheduleStateChange(ElevatorState.ACCELERATING,0);
                    //System.out.println(this+"Elevator: dispatch to"+floor.getNumber());
                    //System.out.println(mCurrentFloor.getNumber());
                    if(mCurrentFloor.getNumber()>floor.getNumber()){
                        mCurrentDirection=Elevator.Direction.MOVING_DOWN;
                    }
                    else if(mCurrentFloor.getNumber()<floor.getNumber()){
                        mCurrentDirection=Elevator.Direction.MOVING_UP;
                    }
                }
                //System.out.println("Elevator dispatch to: "+floor.getNumber());
	}
	
	// Simple accessors
	public Floor getCurrentFloor() {
		return mCurrentFloor;
	}
        
        public boolean[] getRequestedFloor(){
            return RequestedFloor;
        }
	
	public Direction getCurrentDirection() {
		return mCurrentDirection;
	}
        public ElevatorState getCurrentState(){
            return mCurrentState;
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
                if(mCurrentState==ElevatorState.IDLE_STATE){
                    return true;
                }
		return false;
	}
        
        public List<Passenger> getPassenger(){
            return mPassengers;
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
	public List<ElevatorObserver> getObserver(){
            return mObservers;
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
                    this.mCurrentDirection=direction;
                }
                //System.out.println("Elevator: direction is pressed");
                //System.out.println(this.getObserver().size());
                List<ElevatorObserver> cache=new ArrayList<>(mObservers);
                for(ElevatorObserver i:cache){
                        i.elevatorDecelerating(this);
                    }
                scheduleStateChange(ElevatorState.DOORS_OPENING,0);

	}
	
	
	
	// Voodoo magic.
	@Override
	public String toString() {
		return "Elevator " + mNumber + " - " + mCurrentFloor + " - " + mCurrentState + " - " + mCurrentDirection + " - "
		 + "[" + mPassengers.stream().map(p -> Integer.toString(p.getDestination())).collect(Collectors.joining(", "))
		 + "]";
	}
	
}
