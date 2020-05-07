/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Simulation;

/**
 *
 * @author votha
 */
public class Child implements PassengerFactory{
    private int destination;
    private int schedule;
    
    public void setDestination(int des){
        destination=des;
    
}
    public void setSchedule(int sche){
        schedule=sche;
    }

    @Override
    public String factoryName() {
        return"Child";
    }

    @Override
    public String shortName() {
        return"C";
    }

    @Override
    public int factoryWeight() {
        return 3;
    }

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new AwkwardBoarding();
    }

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        return new SingleDestinationTravel(destination,schedule);
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new ClumsyEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new DistractedDebarking();
    }
    
}
