package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class DriveStraight extends Command {

	private double time;
	private double speed;

	public DriveStraight(double time, double speed) {
		requires(Robot.drivetrain);
		this.time = time;
		this.speed = speed;
	}

	public DriveStraight(double time) {
		this(time, 0.75);
	}

	protected void initialize() {
		setTimeout(time);
	}

	protected void execute() {
		Robot.drivetrain.driveArcade(speed, 0);
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
