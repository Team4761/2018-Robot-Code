package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

import static org.robockets.robot.Robot.drivetrain;

/**
 * @author Jake Backer
 */
public class TurnAbsolute extends Command {

	private double angle;

	private final double TIMEOUT = 1.75;

	public TurnAbsolute(double angle) {
		requires(Robot.drivetrain);
		this.angle = angle;
	}

	protected void initialize() {
		drivetrain.setGyroSetpoint(-angle);
		drivetrain.enableGyroPID();
		setTimeout(TIMEOUT);
	}

	protected void execute() {
		drivetrain.driveArcade(0, -drivetrain.gyroPID.get());
	}

	protected boolean isFinished() {
		return drivetrain.gyroPID.onTarget() || isTimedOut();
	}

	protected void end() {
		System.out.println("Finished Turning");
		drivetrain.disableGyroPID();
	}

	protected void interrupted() {
		end();
	}
}
