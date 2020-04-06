package passengers;

import elevators.Elevator;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;

/**
 * A VisitorPassenger has a single destination and a single duration (in seconds), which is how long the Visitor
 * will "disappear" for after departing the elevator on their destination floor. After that duration, the Visitor
 * will reappear on the original destination floor, set its new destination to floor 1, then leave the building when it
 * arrives on floor 1.
 */
public class VisitorPassenger extends Passenger {
	// TODO: add fields, constructors, and accessors to implement this class.
        private int destination;
        private int schedule;
	
	public VisitorPassenger(int destinationFloor, double durationTime) {
		super();
		// TODO: change this constructor's  body.
                destination=destinationFloor;
                schedule=(int) durationTime;
                
	}
        
        public int getSchedule(){
            return schedule;
        }
	
	@Override
	public int getDestination() {
		// TODO: finish this method to return the visitor's destination, which changes to floor 1 when they
		// "reappear".
                return destination;
                
	}
	
	// TODO: implement this template method variant. A Visitor will join an elevator whose passenger count is less than its capacity.
	@Override
	protected boolean willBoardElevator(Elevator elevator) {
                if(elevator.getCapacity()>elevator.getPassengerCount()){
                    elevator.addPassenger(this);
                    return true;
                }
		return false;
	}
	
	/*
	 TODO: implement this template method variant, which is called when the passenger is leaving the elevator it
	 is on. A Visitor that is departing on floor 1 just leaves the building, printing a message to System.out.
	 A visitor that is departing on any other floor sets their new destination to floor 1, and then schedules a
	 PassengerNextDestinationEvent to occur when they are supposed to "reappear" (their duration field).
	*/
	@Override
	protected void leavingElevator(Elevator elevator) {
                elevator.removePassenger(this);
                int EndFloor=1;
		Simulation s = elevator.getBuilding().getSimulation();
		PassengerNextDestinationEvent ev = new PassengerNextDestinationEvent( (s.currentTime() + getSchedule()), this,
		elevator.getCurrentFloor());
                this.destination=EndFloor;
		s.scheduleEvent(ev);
                
	}
	
	// TODO: return "Visitor heading to floor {destination}", replacing {destination} with the floor number.
	@Override
	public String toString() {
		return "Visitor "+this.getId()+" is heading to "+this.getDestination();
	}
	
	@Override
	public void elevatorDecelerating(Elevator elevator) {
		// Don't care.
	}
}
