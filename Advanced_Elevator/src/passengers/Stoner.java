/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Simulation;
import java.util.Random;

/**
 *
 * @author votha
 */
public class Stoner implements PassengerFactory{
    @Override
    public String factoryName() {
        return "Stoner";
    }

    @Override
    public String shortName() {
        return"St";
    }

    @Override
    public int factoryWeight() {
        return 1;
    }

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new CapacityBoarding();
    }
    

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        Random ran=simulation.getRandom();
        int destination=2+ran.nextInt(simulation.getBuilding().getFloorCount()-1);
        long schedule=(long)(3600+ran.nextGaussian()*120);
        return new SingleDestinationTravel(destination,schedule);
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new ClumsyEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new ConfusedDebarking();
    }
    
}
