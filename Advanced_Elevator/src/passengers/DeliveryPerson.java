/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Simulation;
import java.util.*;

/**
 *
 * @author votha
 */
public class DeliveryPerson implements PassengerFactory{
    private List<Integer> destination;
    private List<Long> schedule;
    
    public void setDestination(List<Integer> des){
        destination=des;
    
}
    public void setSchedule(List<Long> sche){
        schedule=sche;
    }

    @Override
    public String factoryName() {
        return"DeliveryPerson";
    }

    @Override
    public String shortName() {
        return"DP";
    }

    @Override
    public int factoryWeight() {
        return 2;
    }
    

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new ThresholdBoarding(5);
    }

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        return new MultipleDestinationTravel(destination,schedule);
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new ResponsibleEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new DistractedDebarking();
    }
    
}
