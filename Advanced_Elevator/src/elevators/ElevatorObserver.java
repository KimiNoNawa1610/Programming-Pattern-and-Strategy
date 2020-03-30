package elevators;

public interface ElevatorObserver {
	/**
	 * Triggered when an elevator enters the DECELERATING state.
         * @param sender
	 */
	void elevatorDecelerating(Elevator sender);
	
	/**
	 * Triggered when an elevator enters the DOORS_OPEN state.
         * @param sender
	 */
	void elevatorDoorsOpened(Elevator sender);
	
	/**
	 * Triggered when an elevator enters the IDLE_STATE state.
         * @param sender
	 */
	void elevatorWentIdle(Elevator sender);
}
