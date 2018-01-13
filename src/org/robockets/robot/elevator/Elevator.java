package org.robockets.robot.elevator;

import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setElevatorSpeed(double elSpeed){
    	RobotMap.elevatorMotor.set(elSpeed);
    }
    public void stop(){
    	RobotMap.elevatorMotor.set(0);

    }
}

