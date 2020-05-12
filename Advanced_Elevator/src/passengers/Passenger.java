package passengers;

import buildings.Floor;
import buildings.FloorObserver;
import elevators.Elevator;
import elevators.ElevatorObserver;

/**
 * A passenger that is either waiting on a floor or riding an elevator.
 */
public class Passenger implements FloorObserver, ElevatorObserver{
	// An enum for determining whether a Passenger is on a floor, an elevator, or busy (visiting a room in the building).
	public enum PassengerState {
		WAITING_ON_FLOOR,
		ON_ELEVATOR,
		BUSY
	}
        
	private String PassengerName;
        private String PassengerShortName;
        private DebarkingStrategy Debark;
        private BoardingStrategy Boarding;
        private EmbarkingStrategy Embark;
        private TravelStrategy Travel;
        
	// A cute trick for assigning unique IDs to each object that is created. (See the constructor.)
	private static int mNextId;
        
	protected static int nextPassengerId() {
		return ++mNextId;
	}
	
	private final int mIdentifier;
	private PassengerState mCurrentState;
	
	public Passenger(String Name,String shortn,DebarkingStrategy debarking, BoardingStrategy boarding,
                EmbarkingStrategy embarking, TravelStrategy travelling) {
            PassengerName=Name; 
            mIdentifier = nextPassengerId();
            mCurrentState = PassengerState.WAITING_ON_FLOOR;
            PassengerShortName=shortn;
            Debark=debarking;
            Boarding=boarding;
            Embark=embarking;
            Travel=travelling;
         
	}
	
	public void setState(PassengerState state) {
		mCurrentState = state;
	}
        public PassengerState getState(){
            return mCurrentState;
        }
	
        public TravelStrategy getTravel(){
            return Travel;
        }
        
        public BoardingStrategy getBoarding(){
            return Boarding;
        }
	/**
	 * Gets the passenger's unique identifier.
         * @return 
	 */
	public int getId() {
		return mIdentifier;
	}
        
        public String getName(){
            return PassengerName;
        }
        
        public String getShortName(){
            return PassengerShortName;
        }
	
	
	/**
	 * Handles an elevator arriving at the passenger's current floor.
         * @param floor
	 */
	@Override
	public void elevatorArriving(Floor floor, Elevator elevator) {
		// This is a sanity check. A Passenger should never be observing a Floor they are not waiting on.
		if (floor.getWaitingPassengers().contains(this) && mCurrentState == PassengerState.WAITING_ON_FLOOR) {
			Elevator.Direction elevatorDirection = elevator.getCurrentDirection();
                        floor.clearDirection(elevatorDirection);
                        if(elevatorDirection!=null){
                            switch (elevatorDirection) {
                        
                                case NOT_MOVING:
                                    elevator.addObserver(this);
                                    break;
                        
                                case MOVING_UP:
                                    if(this.getTravel().getDestination()>elevator.getCurrentFloor().getNumber()){
                                
                                        elevator.addObserver(this);
                            
                                    }
                            
                                    break;
                        
                                case MOVING_DOWN:
                                    if(this.getTravel().getDestination()<elevator.getCurrentFloor().getNumber()){
                                
                                        elevator.addObserver(this);
                            
                                    }
                            
                                    break;
                        
                                default:
                            
                                    break;
                    
                            }
		
                        }
                        
                }
                    // TODO: check if the elevator is either NOT_MOVING, or is going in the direction that this passenger wants.
                    // If so, this passenger becomes an observer of the elevator.
                
                    
		// This else should not happen if your code is correct. Do not remove this branch; it reveals errors in your code.
		else {
			throw new RuntimeException("Passenger " + toString() + " is observing Floor " + floor.getNumber() + " but they are " +
			 "not waiting on that floor.");
		}
	}
        @Override
        public void elevatorDecelerating(Elevator sender) {
            //ignore
        }
        
        public EmbarkingStrategy getEmbarking(){
            return Embark;
        }
	
	/**
	 * Handles an observed elevator opening its doors. Depart the elevator if we are on it; otherwise, enter the elevator.
         * @param elevator
	 */
	@Override
	public void elevatorDoorsOpened(Elevator elevator) {
		// The elevator is arriving at our destination. Remove ourselves from the elevator, and stop observing it.
		// Does NOT handle any "next" destination...

		if (mCurrentState == PassengerState.ON_ELEVATOR) {
                    if(this.Debark.willLeaveElevator(this, elevator)==true){
                        elevator.removeObserver(this);
                        elevator.removePassenger(this);
                        this.Debark.departedElevator(this, elevator);
                    }
			
		}
		// The elevator has arrived on the floor we are waiting on. If the elevator has room for us, remove ourselves
		// from the floor, and enter the elevator.
		else if (mCurrentState == PassengerState.WAITING_ON_FLOOR) {
                    if(this.Boarding.willBoardElevator(this, elevator)==true){
                        if(!elevator.getPassenger().contains(this)){
                            elevator.addPassenger(this);
                            mCurrentState = PassengerState.ON_ELEVATOR;
                        }
                        this.Embark.enteredElevator(this, elevator);
                        elevator.getCurrentFloor().removeWaitingPassenger(this);
                        elevator.getCurrentFloor().removeObserver(this);
                    }
                    else{
                        elevator.removeObserver(this);
                        if(elevator.getCurrentFloor().getNumber()<this.getTravel().getDestination()){
                            elevator.getCurrentFloor().requestDirection(Elevator.Direction.MOVING_UP);
                        }
                        else if(elevator.getCurrentFloor().getNumber()>this.getTravel().getDestination()){
                            elevator.getCurrentFloor().requestDirection(Elevator.Direction.MOVING_DOWN);
                
                        }
                    
                    }
}
        }
	// This will be overridden by derived types.
	@Override
	public String toString() {
		return this.getName()+" "+this.getId()+" [ ->"+Integer.toString(getTravel().getDestination())+"] ";
	}
	
	@Override
	public void directionRequested(Floor sender, Elevator.Direction direction) {
		// Don't care.
	}
	
	@Override
	public void elevatorWentIdle(Elevator elevator) {
		// Don't care about this.
	}
	
	// The next two methods allow Passengers to be used in data structures, using their id for equality. Don't change 'em.
	@Override
	public int hashCode() {
		return Integer.hashCode(mIdentifier);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Passenger passenger = (Passenger)o;
		return mIdentifier == passenger.mIdentifier;
	}
	
}