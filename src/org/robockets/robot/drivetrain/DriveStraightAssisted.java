package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class DriveStraightAssisted extends Command {

	private double distance;

	public DriveStraightAssisted(double distance) {
		requires(Robot.drivetrain);
		this.distance = distance;
	}

	protected void initialize() {
		double leftPosition = RobotMap.leftEncoder.getDistance() + distance; // Convert relative to absolute
		double rightPosition = RobotMap.rightEncoder.getDistance() + distance;

		Robot.drivetrain.setDistance(leftPosition, rightPosition);
		Robot.drivetrain.enableEncoderPID();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Robot.drivetrain.encodersOnTarget();
	}

	protected void end() {
		Robot.drivetrain.disableEncoderPID();
	}

	protected void interrupted() {
		end();
	}
}
