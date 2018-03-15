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
		double position = Robot.drivetrain.getEncoderPos() + distance; // Convert relative to absolute TODO: This may need to change

		Robot.drivetrain.setDistance(position);
		Robot.drivetrain.enableEncoderPID();
	}

	protected void execute() {
		double angle = Robot.drivetrain.getGyroPos();
		double translate = -Robot.drivetrain.encoderPID.get();
		double rotate = (angle-Robot.drivetrain.gyroPID.getSetpoint())*Robot.drivetrain.ENCODER_KP;

		Robot.drivetrain.driveArcade(translate, rotate);
	}

	protected boolean isFinished() {
		return Robot.drivetrain.encoderPID.onTarget();
	}

	protected void end() {
		Robot.drivetrain.disableEncoderPID();
	}

	protected void interrupted() {
		end();
	}
}
