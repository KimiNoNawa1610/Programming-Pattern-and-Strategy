/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import buildings.Floor;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;
import java.util.*;

/**
 *
 * @author votha
 */
public class MultipleDestinationTravel implements TravelStrategy{
    private List<Integer> destination;
    private List<Long>schedule;
    
    public MultipleDestinationTravel(List<Integer> des, List<Long> sch){
        destination=des;
        schedule=sch;
        
    }
    public void setSchedule(Long sche){
        schedule.set(0, sche);
    }
    @Override
    public int getDestination() {
        return destination.get(0);
    }

    @Override
    public void scheduleNextDestination(Passenger passenger, Floor currentFloor) {
        
        this.destination.remove(0);
        Simulation s=currentFloor.getBuilding().getSimulation();
        PassengerNextDestinationEvent ev=new PassengerNextDestinationEvent(s.currentTime()+this.schedule.get(0),
                    passenger,currentFloor);
        this.schedule.remove(0);
        if(this.destination.size()==0){
            destination.add(1);
        }
        
        s.scheduleEvent(ev);
        
    }
    
}
