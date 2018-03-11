package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class TimedElevator extends Command {

	private double time;
	private double speed;

	public TimedElevator(double time, double speed) {
		this.time = time;
		this.speed = speed;
	}

	protected void initialize() {
		setTimeout(time);
	}

	protected void execute() {
		System.out.println("ELEVATING");
		Robot.elevator.setElevatorSpeed(speed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
