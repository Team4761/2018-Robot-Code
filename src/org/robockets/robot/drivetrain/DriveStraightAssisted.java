package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class DriveStraightAssisted extends Command {

	private double distance;
	private double speed;

	public DriveStraightAssisted(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;
	}

	public DriveStraightAssisted(double distance) {
		this(distance, 0.75);
	}

	protected void initialize() {
		Robot.drivetrain.setDistance(distance);
		Robot.drivetrain.enableEncoderPID();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Robot.drivetrain.encodersOnTarget();
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
