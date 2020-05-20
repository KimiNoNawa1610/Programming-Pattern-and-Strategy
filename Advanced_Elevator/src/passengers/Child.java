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
public class Child implements PassengerFactory{
    private int weight=3;
    
    public void setWeight(int num){
        weight=num;
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
        return weight;
    }

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new AwkwardBoarding(4);
    }

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        Random ran=simulation.getRandom();
        return new SingleDestinationTravel(2+ran.nextInt(simulation.getBuilding().getFloorCount()-1),
                (long)(7200+ran.nextGaussian()*1800));
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
