package elevators;

import buildings.Building;
import events.SimulationEvent;
import events.SpawnPassengerEvent;
import java.util.*;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import passengers.PassengerFactory;

public class Simulation {
	private Random mRandom;
	private PriorityQueue<SimulationEvent> mEvents = new PriorityQueue<>();
	private long mCurrentTime;
	private List<PassengerFactory> passengers;
        
	/**
	 * Seeds the Simulation with a given random number generator.
         * @param random
	 */
	public Simulation(Random random) {
		mRandom = random;
	}
        
        public List<PassengerFactory> getPassengerFactories(){
            return passengers;
        }
        
	public void setPassenger(List<PassengerFactory> pas){
            pas=passengers;
        }
        
        public void addPassenger(PassengerFactory pas){
            passengers.add(pas);
        }
	/**
	 * Gets the current time of the simulation.
         * @return 
	 */
	public long currentTime() {
		return mCurrentTime;
	}
	
	/**
	 * Access the Random object for the simulation.
         * @return 
	 */
	public Random getRandom() {
		return mRandom;
	}
	
	/**
	 * Adds the given event to a priority queue sorted on the scheduled time of execution.
         * @param ev
	 */
	public void scheduleEvent(SimulationEvent ev) {
		mEvents.add(ev);
	}
	
	public void startSimulation(Scanner input) {
                System.out.println("Please enter your building floors number");
                int floorcount=input.nextInt();
                System.out.println("Please enter your elevator number");
                int ele=input.nextInt();
		Building b = new Building(floorcount, ele, this);
		SpawnPassengerEvent ev = new SpawnPassengerEvent(0, b);
		scheduleEvent(ev);
		
		long nextSimLength = -1;
		
		// Set this boolean to true to make the simulation run at "real time".
		boolean simulateRealTime = false;
		// Change the scale below to less than 1 to speed up the "real time".
		double realTimeScale = 1.0;
		
		// TODO: the simulation currently stops at 200s. Instead, ask the user how long they want to simulate.
                System.out.println("Please Enter your simulation time.");
		nextSimLength = input.nextInt();
		while(nextSimLength!=-1){
		long nextStopTime = mCurrentTime + nextSimLength;
		// If the next event in the queue occurs after the requested sim time, then just fast forward to the requested sim time.
		if (mEvents.peek().getScheduledTime() >= nextStopTime) {
			mCurrentTime = nextStopTime;
		}
		
		// As long as there are events that happen between "now" and the requested sim time, process those events and
		// advance the current time along the way.
		while (!mEvents.isEmpty() && mEvents.peek().getScheduledTime() <= nextStopTime) {
			SimulationEvent nextEvent = mEvents.poll();
			
			long diffTime = nextEvent.getScheduledTime() - mCurrentTime;
			if (simulateRealTime && diffTime > 0) {
				try {
					Thread.sleep((long)(realTimeScale * diffTime * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			mCurrentTime += diffTime;
			nextEvent.execute(this);
                        //System.out.println("Simulation execute");
			System.out.println(nextEvent);
                        
		}
		
		// TODO: print the Building after simulating the requested time.
		System.out.println(b.toString());
		
		/*
		 TODO: the simulation stops after one round of simulation. Write a loop that continues to ask the user
		 how many seconds to simulate, simulates that many seconds, and stops only if they choose -1 seconds.
		*/
                System.out.println("Please Enter your simulation time. Enter -1 to exist");
		nextSimLength = input.nextInt();
                
                }
                
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// TODO: ask the user for a seed value and change the line below.
                System.out.println("Please enter your seed number.");
                int seed=s.nextInt();
		Simulation sim = new Simulation(new Random(seed));
                sim.startSimulation(s);
                
	}
}
