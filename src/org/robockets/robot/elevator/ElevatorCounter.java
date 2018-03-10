package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ElevatorCounter extends Command {

	private boolean wasActive = false;

	public ElevatorCounter() {
		setRunWhenDisabled(true);
	}

	protected void initialize() {
	}

	protected void execute() {
		boolean currentState = RobotMap.elevatorEncoder.get();

		if (wasActive != currentState) {
			if (Robot.elevator.getLastDirection() == RelativeDirection.ZAxis.UP) {
				Robot.elevator.countUp();
			} else {
				Robot.elevator.countDown();
			}
			wasActive = currentState;
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
