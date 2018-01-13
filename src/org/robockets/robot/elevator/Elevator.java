package org.robockets.robot.elevator;

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

	public boolean isSwitchPressed(ElevatorPosition position) {
    	boolean isPressed = false;
    	switch (position) {
			case TOP:
				isPressed = RobotMap.topLimitSwitch.get();
				break;
			case MIDDLE:
				isPressed = RobotMap.middleLimitSwitch.get();
				break;
			case BOTTOM:
				isPressed = RobotMap.bottomLimitSwitch.get();
				break;
		}

		return isPressed;
	}

    public void stop(){
    	RobotMap.elevatorMotor.set(0);

    }
}

