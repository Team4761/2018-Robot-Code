package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.robockets.robot.Robot.drivetrain;

import org.robockets.robot.Robot;

/**
 * @author Mathias Kools!
 * Theoretical command to assist in constructing Mid Auto program.
 */
public class DriveAngleAssisted extends Command {

	private double angle;

	public DriveAngleAssisted(double angle) {
		this.angle = angle;
		requires(drivetrain);
	}

	protected void initialize() {
		double abs = drivetrain.getGyroPos() - angle;
		drivetrain.setGyroSetpoint(abs);
		drivetrain.enableGyroPID();
	}

	protected void execute() {
		// Stub.
	}

	protected boolean isFinished() {
		return drivetrain.gyroPID.onTarget();
	}

	protected void end() {
		drivetrain.disableGyroPID();
	}

	protected void interrupted() {
		end();
	}
}
