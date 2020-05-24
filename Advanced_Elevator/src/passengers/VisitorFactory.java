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
public class VisitorFactory implements PassengerFactory{
    private int weight=10;
    
    public void setWeight(int num){
        weight=num;
    }
    
    @Override
    public String factoryName() {
        return "Visitor";
    }

    @Override
    public String shortName() {
        return"V";
    }

    @Override
    public int factoryWeight() {
        return weight;
    }

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new CapacityBoarding();
    }

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        Random ran=simulation.getRandom();
        int destination=2+ran.nextInt(simulation.getBuilding().getFloorCount()-1);
        long schedule=(long)(3600+ran.nextGaussian()*1200);
        return new SingleDestinationTravel(destination,schedule);
                
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new ResponsibleEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new AttentiveDebarking();
    }
    
}
