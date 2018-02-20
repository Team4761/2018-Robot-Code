package org.robockets.robot.elevator;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevate extends Command {

	private ElevatorPosition newPosition;
	private RelativeDirection.ZAxis direction;
	private double elSpeed = 0.0;

	public Elevate(ElevatorPosition newPosition, double elSpeed) {
		this.newPosition = newPosition;
		this.elSpeed = elSpeed;

	}

	public Elevate(ElevatorPosition newPosition) {
		this(newPosition, 0.75);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double tempSpeed = Math.abs(elSpeed);
		direction = newPosition.getValue() > Robot.elevator.getCurrentPosition().getValue()
				? RelativeDirection.ZAxis.UP : RelativeDirection.ZAxis.DOWN;

		if (direction == RelativeDirection.ZAxis.DOWN) {
			tempSpeed = -tempSpeed;
		}

		Robot.elevator.setElevatorSpeed(tempSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.elevator.isInPosition(newPosition, direction);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setCurrentPosition(newPosition);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
