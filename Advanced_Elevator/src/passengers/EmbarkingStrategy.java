package passengers;

import elevators.Elevator;

/**
 * An EmbarkingStrategy specifies what to do when a passenger entered an open elevator.
 */
public interface EmbarkingStrategy {
	/**
	 * Called when the passenger entered the given elevator, giving a chance to request floors, etc.
         * @param passenger
         * @param elevator
	 */
	void enteredElevator(Passenger passenger, Elevator elevator);
}
