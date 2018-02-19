package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ElevatorFloor extends Subsystem {

	private final double[][] INVALID_RANGE = {{18.0, 29.5}, {43.0, 46.0}}; // Ranges that the elevator cannot go to (inches)

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorFloorListener());
	}


	public void moveElevatorFloor(double speed) {
		if (canMove()) {
		RobotMap.elevatorFloorMotor.set(-speed);
		RobotMap.elevatorFloorMotor2.set(-speed);
		}
	}

	public boolean canMove() {
		double encoderPos = Robot.elevator.getRawEncoderPos();

		for (double[] range : INVALID_RANGE) {
			if (encoderPos <= range[1] && encoderPos >= range[0]) {
				return false;
			}
		}

		return true;
	}
}

