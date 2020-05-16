package elevators;

import buildings.Building;
import buildings.Floor;
import java.util.ArrayList;
import java.util.List;
import passengers.Passenger;
/**
 * An ActiveMode elevator is handling at least one floor request.
 */
public class ActiveMode implements OperationMode {
	
	// TODO: implement this class.
	// An active elevator cannot be dispatched, and will ignore direction requests from its current floor. (Only idle
	//    mode elevators observe floors, so an ActiveMode elevator will never observe directionRequested.)
	// The bulk of your Project 2 tick() logic goes here, except that you will never be in IDLE_STATE when active.
	// If you used to schedule a transition to IDLE_STATE, you should instead schedule an operation change to
	//    IdleMode in IDLE_STATE.
	// Otherwise your code should be almost identical, except you are no longer in the Elevator class, so you need
	//    to use accessors and mutators instead of directly addressing the fields of Elevator.
	
    @Override
    public String toString() {
	return "Active";
    }

    @Override
    public boolean canBeDispatchedToFloor(Elevator elevator, Floor floor) {
        return false;
    }

    @Override
    public void dispatchToFloor(Elevator elevator, Floor targetFloor, Elevator.Direction targetDirection) {
        //ignore since canBeDispatchedtoFloor is false
    }

    @Override
    public void directionRequested(Elevator elevator, Floor floor, Elevator.Direction direction) {
        //ignore
    }

    @Override
    public void tick(Elevator elevator) {
        List<ElevatorObserver> cache=new ArrayList<>(elevator.getObserver());
        Elevator.Direction tempDirection=elevator.getCurrentDirection();
        Building tempBuilding=elevator.getBuilding();
        List<Passenger> temppas=elevator.getPassenger();
        switch (elevator.getCurrentState()) {
        case DOORS_OPENING:
                    
            elevator.scheduleStateChange(Elevator.ElevatorState.DOORS_OPEN,2);
            
                    
            break;
                
        case DOORS_OPEN:
                    
            int PreviousPassengerOntheFloor= elevator.getCurrentFloor().getWaitingPassengers().size();
                    
            int PreviousPassengerOntheElevator= temppas.size();
                    
            for(ElevatorObserver i:cache){
                        
                i.elevatorDoorsOpened(elevator);
                    
            }
                    
            int CurrentPassengerOntheFloor= elevator.getCurrentFloor().getWaitingPassengers().size();
                    
            int CurrentPassengerOntheElevator=temppas.size();
                  
                    
            int PassengerChangeCount=Math.abs(PreviousPassengerOntheFloor-CurrentPassengerOntheFloor)+
                            
                    PreviousPassengerOntheElevator-CurrentPassengerOntheElevator+
                            
                    Math.abs(PreviousPassengerOntheFloor-CurrentPassengerOntheFloor);
                    //System.out.println(PassengerChangeCount);
                    
            int x=PassengerChangeCount/2;
                    
                    
            elevator.scheduleStateChange(Elevator.ElevatorState.DOORS_CLOSING,1+x);
                    
                    
            break;
                
        case DOORS_CLOSING:
                    
                    
            if(null==elevator.getCurrentDirection()){
                        
                elevator.setCurrentDirection(Elevator.Direction.NOT_MOVING);
                        
                elevator.scheduleModeChange(new IdleMode(),Elevator.ElevatorState.IDLE_STATE,2);
                    
            }
                    
            else switch (tempDirection) {
                
                case MOVING_DOWN:
                    if(elevator.nextRequestDown(elevator.getCurrentFloor().getNumber())!=-1){
                        elevator.scheduleStateChange(Elevator.ElevatorState.ACCELERATING,2);
                    }
                    else if(elevator.nextRequestUp(elevator.getCurrentFloor().getNumber())!=-1){
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_UP);
                        elevator.scheduleStateChange(Elevator.ElevatorState.DOORS_OPENING,2);
                    }
                    else{
                        elevator.setCurrentDirection(Elevator.Direction.NOT_MOVING);
                        elevator.scheduleModeChange(new IdleMode(),Elevator.ElevatorState.IDLE_STATE,2);
                    }
                    break;
                case MOVING_UP:
                    if(elevator.nextRequestUp(elevator.getCurrentFloor().getNumber())!=-1){
                        elevator.scheduleStateChange(Elevator.ElevatorState.ACCELERATING,2);
                    }
                    else if(elevator.nextRequestDown(elevator.getCurrentFloor().getNumber())!=-1){
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_DOWN);
                        elevator.scheduleStateChange(Elevator.ElevatorState.DOORS_OPENING,2);
                    }
                    else{
                        elevator.setCurrentDirection(Elevator.Direction.NOT_MOVING);
                        elevator.scheduleModeChange(new IdleMode(),Elevator.ElevatorState.IDLE_STATE,2);
                    }
                    break;
                default:
                    tempDirection=Elevator.Direction.NOT_MOVING;
                    elevator.scheduleModeChange(new IdleMode(),Elevator.ElevatorState.IDLE_STATE,2);
                    break;
            
            }
                    break;
                
        case ACCELERATING:
                    
            elevator.getCurrentFloor().removeObserver(elevator);
                    
            elevator.scheduleStateChange(Elevator.ElevatorState.MOVING,3);
                    
            break;
                
        case MOVING:
                    
            if(tempDirection==Elevator.Direction.MOVING_UP){
                elevator.setCurrentFloor(tempBuilding.getFloor(elevator.getCurrentFloor().getNumber()+1));
                if(elevator.getRequestedFloor()[tempBuilding.getFloor(elevator.getCurrentFloor().getNumber()).getNumber()-1]==true||
                        elevator.getCurrentFloor().directionIsPressed(tempDirection)){
                    elevator.scheduleStateChange(Elevator.ElevatorState.DECELERATING,2);
                }
                       
                else{
                    elevator.scheduleStateChange(Elevator.ElevatorState.MOVING,2);
                }    
                
            }
                    
            else if(tempDirection==Elevator.Direction.MOVING_DOWN){
                
                elevator.setCurrentFloor(tempBuilding.getFloor(elevator.getCurrentFloor().getNumber()-1));
                                                                          
                if(elevator.getRequestedFloor()[elevator.getCurrentFloor().getNumber()-1]==true||
                        elevator.getCurrentFloor().directionIsPressed(tempDirection)){   
                    elevator.scheduleStateChange(Elevator.ElevatorState.DECELERATING,2);    
                }          
                else{
                    elevator.scheduleStateChange(Elevator.ElevatorState.MOVING,2); 
                }    
                    
                
      
            }  
            break;
                
        case DECELERATING:
                    
            elevator.getRequestedFloor()[elevator.getCurrentFloor().getNumber()-1]=false;
                    
            if(!elevator.getCurrentFloor().directionIsPressed(tempDirection)){
                        
                if(tempDirection==Elevator.Direction.MOVING_UP){
                    
                    if(elevator.getCurrentFloor().directionIsPressed(Elevator.Direction.MOVING_UP)||
                            elevator.nextRequestUp(elevator.getCurrentFloor().getNumber())!=-1){

                        elevator.setCurrentDirection(Elevator.Direction.MOVING_UP);
                    }   
                    else if(elevator.getCurrentFloor().directionIsPressed(Elevator.Direction.MOVING_DOWN)&& 
                            elevator.nextRequestUp(elevator.getCurrentFloor().getNumber())==-1){
 
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_DOWN);     
                    }    
                    else{       
                        elevator.setCurrentDirection(Elevator.Direction.NOT_MOVING);     
                    } 
                }     
                else if(tempDirection==Elevator.Direction.MOVING_DOWN){    
                    if(elevator.getCurrentFloor().directionIsPressed(Elevator.Direction.MOVING_DOWN)||
                            elevator.nextRequestDown(elevator.getCurrentFloor().getNumber())!=-1){       
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_DOWN);   
                    }     
                    else if(elevator.getCurrentFloor().directionIsPressed(Elevator.Direction.MOVING_UP)&& 
                            elevator.nextRequestDown(elevator.getCurrentFloor().getNumber())==-1){       
                        elevator.setCurrentDirection(Elevator.Direction.MOVING_UP);   
                    }
                            
                    else{
                                
                        elevator.setCurrentDirection(Elevator.Direction.NOT_MOVING);
                            
                    }
                    
                }
                    
            }
             
            for(ElevatorObserver i:cache){  
                i.elevatorDecelerating(elevator);    
            }
                    
            elevator.scheduleStateChange(Elevator.ElevatorState.DOORS_OPENING,3);
                    
                    
            break;
                
        default:
                    
            break;
            
        }    
    }
}
