package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class FakeDriveAssisted extends Command {

	double previousLeft = 0;
	double previousRight = 0;

	final double VELOCITY = 0.25;

	public FakeDriveAssisted() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double leftValue = OI.driverJoystick.getRawAxis(1);
		double rightValue = -OI.driverJoystick.getRawAxis(5);

		double leftDiff = leftValue - previousLeft;
		double rightDiff = rightValue - previousRight;

		double newLeftValue = (leftDiff*VELOCITY)+previousLeft;
		double newRightValue = (rightDiff*VELOCITY)+previousRight;

		previousLeft = newLeftValue;
		previousRight = newRightValue;

		Robot.drivetrain.driveArcade(newLeftValue , newRightValue);
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
