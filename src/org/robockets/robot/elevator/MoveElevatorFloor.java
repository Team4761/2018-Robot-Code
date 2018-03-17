package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class MoveElevatorFloor extends Command {

	private double speed;
	private double time;

	public MoveElevatorFloor(double speed, double time) {
		this.speed = speed;
		this.time = time;
	}

	public MoveElevatorFloor(double speed) {
		this(speed, 0);
	}

	protected void initialize() {
		if (time != 0) {
			setTimeout(time);
		}
	}

	protected void execute() {
		System.out.println("Moving Floor");
		Robot.elevatorFloor.moveElevatorFloor(speed);
	}

	protected boolean isFinished() {
		if (time != 0) {
			return isTimedOut();
		}
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
