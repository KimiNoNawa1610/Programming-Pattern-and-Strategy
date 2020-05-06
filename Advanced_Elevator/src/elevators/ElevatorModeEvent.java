/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevators;

import events.SimulationEvent;

/**
 *
 * @author votha
 */
public class ElevatorModeEvent extends SimulationEvent{
    private Elevator.ElevatorState mNewState;
    private OperationMode mNewMode;
    private Elevator mElevator;
    
    public ElevatorModeEvent(long scheduledTime, OperationMode newMode,Elevator.ElevatorState newState, Elevator elevator) {
	super(scheduledTime);
	mNewMode = newMode;
	mElevator = elevator;
        mNewState=newState;
        
	}
    

    @Override
    public void execute(Simulation sim) {
        mElevator.setOperationMode(mNewMode);
        mElevator.setState(mNewState);
        mNewMode.tick(mElevator);
    }
    
    @Override
    public String toString() {
        return super.toString() + mElevator;
                
    }
    
}
