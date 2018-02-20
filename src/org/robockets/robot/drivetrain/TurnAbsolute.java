package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

import static org.robockets.robot.Robot.drivetrain;

/**
 * @author Jake Backer
 */
public class TurnAbsolute extends Command {

	private double angle;

	public TurnAbsolute(double angle) {
		requires(Robot.drivetrain);
		this.angle = angle;
	}

	protected void initialize() {
		drivetrain.setGyroSetpoint(-angle);
		drivetrain.enableGyroPID();
	}

	protected void execute() {
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
