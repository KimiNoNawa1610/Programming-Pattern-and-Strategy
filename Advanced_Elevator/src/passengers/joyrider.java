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
public class joyrider implements PassengerFactory{
    private int weight=2;
    
    public void setWeight(int num){
        weight=num;
    }

    @Override
    public String factoryName() {
        return "Joyrider";
    }

    @Override
    public String shortName() {
       return "Jd";
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
        int numFloor=simulation.getBuilding().getFloorCount();
        int sumweighted=0;
        for(int i=2;i<numFloor;i++){
            sumweighted+=i;
        }
        int random=ran.nextInt(sumweighted);
        int destination=0;
        int temp=0;
        for(int i=2;i<numFloor;i++){
            temp+=i;
            if(temp>random){
                destination=i;
                break;
            }
        }
        long schedule=(long)(3600+ran.nextGaussian()*1200);
        return new SingleDestinationTravel(destination,schedule);
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new JoyriderEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new AttentiveDebarking();
    }
    
}
