package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class MoveElevatorFloor extends Command {

	private double speed;

	public MoveElevatorFloor(double speed) {
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.elevatorFloor.moveElevatorFloor(speed);
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