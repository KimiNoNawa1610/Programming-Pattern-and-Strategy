package passengers;

import elevators.Elevator;

/**
 * A DebarkingStrategy specifies rules for deciding when a Passenger will leave an Elevator that has opened
 * its doors, and what to do when the Passenger does leave.
 */

public interface DebarkingStrategy {
	/**
	 * Returns true if the given passenger should depart the given elevator, which has opened its doors on some floor --
	 * not necessarily the passenger's destination.
         * @param passenger
         * @param elevator
         * @return 
	 */
	boolean willLeaveElevator(Passenger passenger, Elevator elevator);
	
	/**
	 * Called when the passenger departed the elevator, giving a chance to schedule the next trip etc.
         * @param passenger
         * @param elevator
	 */
	void departedElevator(Passenger passenger, Elevator elevator);
}
