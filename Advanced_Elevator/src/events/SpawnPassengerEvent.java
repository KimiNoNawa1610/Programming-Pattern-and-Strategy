package events;

import buildings.Building;
import passengers.Passenger;
import elevators.Simulation;
import java.util.*;
import passengers.PassengerFactory;



/**
 * A simulation event that adds a new random passenger on floor 1, and then schedules the next spawn event.
 */
public class SpawnPassengerEvent extends SimulationEvent {

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
		Random ram = mBuilding.getSimulation().getRandom();
		List<PassengerFactory> temp=sim.getPassengerFactories();
                int total=20;
                int tempweight=0;
                int r=ram.nextInt(19)+1;
                for(PassengerFactory pas: temp){
                    tempweight+=pas.factoryWeight();
                    if(tempweight>total){
                        mPassenger=new Passenger(pas.factoryName(),pas.shortName(),pas.createDebarkingStrategy(sim),
                        pas.createBoardingStrategy(sim),pas.createEmbarkingStrategy(sim),pas.createTravelStrategy(sim));
                        break;
                    }
                }
                mBuilding.getFloor(1).addWaitingPassenger(mPassenger);
                long nextTime=1+ sim.getRandom().nextInt(30)+sim.currentTime();
                SpawnPassengerEvent ev=new SpawnPassengerEvent(nextTime, mBuilding);
                sim.scheduleEvent(ev);
                
                
		
	}
        
        
	
}
