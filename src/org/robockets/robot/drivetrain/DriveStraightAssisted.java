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
		double position = Robot.drivetrain.getEncoderPos() + distance; // Convert relative to absolute

		Robot.drivetrain.setDistance(position);
		Robot.drivetrain.enableEncoderPID();
	}

	protected void execute() {
		System.out.println("Encoder Pos: " + Robot.drivetrain.getEncoderPos());
		double angle = Robot.drivetrain.getGyroPos();
		Robot.drivetrain.driveArcade(Robot.drivetrain.encoderPID.get(), angle*Robot.drivetrain.ENCODER_KP);
	}

	protected boolean isFinished() {
		return Robot.drivetrain.encoderPID.onTarget();
	}

	protected void end() {
		System.out.println("DRIVESTRAIGHT");
		Robot.drivetrain.disableEncoderPID();
	}

	protected void interrupted() {
		end();
	}
}
