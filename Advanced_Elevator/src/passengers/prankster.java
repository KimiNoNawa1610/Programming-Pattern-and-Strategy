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
public class prankster implements PassengerFactory{
    private int weight=1;
    
    public void setWeight(int num){
        weight=num;
    }

    @Override
    public String factoryName() {
        return "Prankster";
    }

    @Override
    public String shortName() {
        return "Pr";
    }

    @Override
    public int factoryWeight() {
        return weight;
    }

    @Override
    public BoardingStrategy createBoardingStrategy(Simulation simulation) {
        return new PranksterBoarding();
    }

    @Override
    public TravelStrategy createTravelStrategy(Simulation simulation) {
        return new PranksterTravel();
    }

    @Override
    public EmbarkingStrategy createEmbarkingStrategy(Simulation simulation) {
        return new PrankEmbarking();
    }

    @Override
    public DebarkingStrategy createDebarkingStrategy(Simulation simulation) {
        return new PrankDebarking();
    }
    
}
