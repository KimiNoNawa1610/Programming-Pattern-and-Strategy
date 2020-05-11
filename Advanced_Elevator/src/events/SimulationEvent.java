package events;

import elevators.Simulation;
import java.util.Queue;

/**
 * Represents an event that occurs in the simulation, which acts to update the state of the simulation in some way.
 * Derived types code the "update" logic by overriding the execute method.
 */
public abstract class SimulationEvent implements Comparable<SimulationEvent> {
	// The time at which this event is scheduled to be executed.
	private long mScheduledTime;
        private static int order=0; 
        private int priority=0;
	/**
	 * Sets the scheduled time of the event.
	 */
	public SimulationEvent(long scheduledTime) {
		mScheduledTime = scheduledTime;
                priority=order++;
	}       
	
	/**
	 * Gets the time at which this event should be executed. The start of the simulation is at time 0. Each unit of time
	 * is equal to 1 second.
	 */
	public long getScheduledTime() {
		return mScheduledTime;
	}
	
	/**
	 *
	 * @param sim
	 */
	public abstract void execute(Simulation sim);
	
	/**
	 * Used for sorting a priority queue, with the smallest scheduled time coming out first.
	 */
	@Override
	public int compareTo(SimulationEvent o) {
            if(this.mScheduledTime==o.mScheduledTime){
                return Integer.compare(order, o.order);
            }
		return Long.compare(mScheduledTime, o.mScheduledTime);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
