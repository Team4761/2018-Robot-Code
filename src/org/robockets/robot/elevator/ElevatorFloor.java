package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ElevatorFloor extends Subsystem {

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorFloorListener());
	}


	public void moveElevatorFloor(double speed) {
		RobotMap.elevatorFloorMotor.set(speed);
	}
}

