package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;


/**
 * @author Jake Backer
 */
public class RotateCube extends Command {

	private final int ITERATIONS = 25;
	private RelativeDirection.XAxis direction;
	private double speed;
	private int currentIt = 0;

	public RotateCube(RelativeDirection.XAxis direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		double temp = direction == RelativeDirection.XAxis.LEFT ? -speed : speed;

		//if (currentIt == ITERATIONS) {
			Robot.elevatorFloor.moveElevatorFloor(RelativeDirection.XAxis.LEFT, temp);
			Robot.elevatorFloor.moveElevatorFloor(RelativeDirection.XAxis.RIGHT, -temp);
		//	currentIt = 0;
		//} else {
		//	currentIt++;
		//}
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
