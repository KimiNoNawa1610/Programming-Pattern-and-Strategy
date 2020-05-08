/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import elevators.Simulation;

/**
 *
 * @author votha
 */
public class StandardOutLogger extends Logger{

    public StandardOutLogger(Simulation simulation) {
        super(simulation);
    }
    
    @Override
    public void logString(String message) {
        System.out.println(message);
    }
    
}
