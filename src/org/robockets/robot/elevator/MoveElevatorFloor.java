package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class MoveElevatorFloor extends Command {

	private RelativeDirection.Malone direction;
	private double speed;

	public MoveElevatorFloor(RelativeDirection.Malone direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}

	public MoveElevatorFloor(RelativeDirection.Malone direction) {
		this(direction, 0.75);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.elevator.moveElevatorFloor(direction, speed);
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
