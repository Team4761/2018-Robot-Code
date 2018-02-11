package org.robockets.robot.elevator;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	private ElevatorPosition position = ElevatorPosition.BOTTOM;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ManualElevate());
    }
    public void setElevatorSpeed(double elSpeed){
    	RobotMap.elevatorMotor.set(elSpeed);
    }

    public ElevatorPosition getCurrentPosition() {
    	return position;
	}

	public void setCurrentPosition(ElevatorPosition position) {
    	this.position = position;
	}

	public boolean isInPosition(ElevatorPosition position, RelativeDirection.ZAxis direction) {
    	boolean isPressed = false;

    	double encoderPos = position.getValue();

    	if (direction == RelativeDirection.ZAxis.UP) {
    		isPressed = RobotMap.elevatorEncoder.getDistance() > encoderPos;
	    } else {
			isPressed = RobotMap.elevatorEncoder.getDistance() < encoderPos;
	    }

		return isPressed;
	}

    public void stop(){
    	RobotMap.elevatorMotor.set(0);

    }
}

