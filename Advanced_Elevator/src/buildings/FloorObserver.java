package buildings;

import elevators.Elevator;

public interface FloorObserver {
	/**
	 * Triggered when an elevator begins decelerating as it is approaching the given floor.
	 * @param sender the Floor being approached
	 * @param elevator the Elevator that is decelerating.
	 */
	void elevatorArriving(Floor sender, Elevator elevator);
	
	/**
	 * Triggered when a direction button has been pressed on the given floor.
         * @param sender
         * @param direction
	 */
	void directionRequested(Floor sender, Elevator.Direction direction);
}
