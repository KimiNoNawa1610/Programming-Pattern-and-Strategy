package events;

import elevators.Simulation;
import elevators.Elevator;
import elevators.OperationMode;

/**
 * A simulation event that sets an elevator's state and calls its tick() method.
 */
public class ElevatorStateEvent extends SimulationEvent {
	private Elevator.ElevatorState mNewState;
	private Elevator mElevator;
        private OperationMode mMode;
	
	public ElevatorStateEvent(long scheduledTime, Elevator.ElevatorState newState, Elevator elevator) {
		super(scheduledTime);
		mNewState = newState;
		mElevator = elevator;
	}
	@Override
	public void execute(Simulation sim) {
                //System.out.println("Elevator State Event execute");
		mElevator.setState(mNewState);
		mElevator.tick();
	}
	
	@Override
	public String toString() {
		return super.toString() + mElevator;
	}
}
