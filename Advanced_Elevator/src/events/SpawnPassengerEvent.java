package events;

import buildings.Building;
import passengers.Passenger;
import elevators.Simulation;
import java.util.Random;
import passengers.VisitorPassenger;

/**
 * A simulation event that adds a new random passenger on floor 1, and then schedules the next spawn event.
 */
public class SpawnPassengerEvent extends SimulationEvent {
	private static long SPAWN_MEAN_DURATION = 10_800;
	private static long SPAWN_STDEV_DURATION = 3_600;

	// After executing, will reference the Passenger object that was spawned.
	private Passenger mPassenger;
	private Building mBuilding;
	
	public SpawnPassengerEvent(long scheduledTime, Building building) {
		super(scheduledTime);
		mBuilding = building;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Adding " + mPassenger + " to floor 1.";
	}
	
	@Override
	public void execute(Simulation sim) {
		Random r = mBuilding.getSimulation().getRandom();
		
		// 75% of all passengers are normal Visitors.
		if (r.nextInt(4) <= 2) {
			mPassenger = getVisitor();
		}
		else {
			mPassenger = getWorker();
		}
		mBuilding.getFloor(1).addWaitingPassenger(mPassenger);

		/*
		 TODO: schedule the new SpawnPassengerEvent with the simulation. Construct a new SpawnPassengerEvent
		 with a scheduled time that is X seconds in the future, where X is a uniform random integer from
		 1 to 30 inclusive.
		*/
                int max=30;
                int min=1;
                int ScheduledTime=r.nextInt((max-min)+1)+min;
                SpawnPassengerEvent SPE=new SpawnPassengerEvent(ScheduledTime,this.mBuilding);
                sim.scheduleEvent(SPE);
	}
	
	
	private Passenger getVisitor() {
		/*
		 TODO: construct a VisitorPassenger and return it.
		 The visitor should have a random destination floor that is not floor 1 (generate a random int from 2 to N).
		 The visitor's visit duration should follow a NORMAL (GAUSSIAN) DISTRIBUTION with a mean of 1 hour
		 and a standard deviation of 20 minutes.
		 */
                
		Random r = mBuilding.getSimulation().getRandom();
		// Look up the documentation for the .nextGaussian() method of the Random class.
                int BaseFloor=2;
                int TopFloor=mBuilding.getFloorCount()-1;
                int RandomFloor=r.nextInt((TopFloor-BaseFloor)+1)+BaseFloor;
                int VisitTime=60;
                int StandardDeviation=20;
                double val=r.nextGaussian()*StandardDeviation+VisitTime;
		VisitorPassenger newVisitor=new VisitorPassenger(RandomFloor,val);
		return newVisitor;
	}
	
	private Passenger getWorker() {
		/*
		TODO: construct and return a WorkerPassenger. A Worker requires a list of destinations and a list of durations.
		To generate the list of destinations, first generate a random number from 2 to 5 inclusive. Call this "X",
		how many floors the worker will visit before returning to floor 1.
		X times, generate an integer from 2 to N (number of floors) that is NOT THE SAME as the previously-generated floor.
		Add those X integers to a list.
		To generate the list of durations, generate X integers using a NORMAL DISTRIBUTION with a mean of 10 minutes
		and a standard deviation of 3 minutes.
		 */
		Random r = mBuilding.getSimulation().getRandom();
		
		return null;
	}
}
