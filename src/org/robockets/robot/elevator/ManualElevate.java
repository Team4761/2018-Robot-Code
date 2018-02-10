package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class ManualElevate extends Command {

	private RelativeDirection.ZAxis direction;
	private double speed;

	public ManualElevate(RelativeDirection.ZAxis direction, double speed) {
		requires(Robot.elevator);
		this.direction = direction;
		this.speed = speed;
	}

	public ManualElevate(RelativeDirection.ZAxis direction) {
		this(direction, 0.75);
	}

	protected void initialize() {
	}

	protected void execute() {
		double temp = Math.abs(speed);

		Robot.elevator.setElevatorSpeed(direction == RelativeDirection.ZAxis.UP ? temp : -temp);
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
