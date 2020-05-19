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
        protected int priority;
	/**
	 * Sets the scheduled time of the event.
	 */
	public SimulationEvent(long scheduledTime) {
		mScheduledTime = scheduledTime;
                
	}
        
        public void setPriority(int num){
            priority=num;
        }
        
        public int getPriority(){
            return priority;
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
         * @param other
	 */
	@Override
	public int compareTo(SimulationEvent other) {
            if(this.mScheduledTime==other.mScheduledTime){
                return Integer.compare(priority, other.priority);
            }
            else{
		return Long.compare(mScheduledTime, other.mScheduledTime);
            }
	}
	
	@Override
	public String toString() {
		return "";
	}
}
