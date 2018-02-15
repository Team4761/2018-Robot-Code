package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ElevatorFloor extends Subsystem {

	public final double[][] INVALID_RANGE = {{0.0, 0.0}, {0.0, 0.0}}; // Ranges that the elevator cannot go to (inches)

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorFloorListener());
	}


	public void moveElevatorFloor(double speed) {
		if (canMove()) {
			RobotMap.elevatorFloorMotor.set(speed);
		}
	}

	public boolean canMove() {
		double encoderPos = Robot.elevator.getEncoderPos();

		for (double[] range : INVALID_RANGE) {
			if (encoderPos <= range[1] && encoderPos >= range[0]) {
				return false;
			}
		}

		return true;
	}
}

