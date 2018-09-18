package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.robot.Robot;

import static org.robockets.robot.Robot.drivetrain;

/**
 * @author Jake Backer
 */
public class StartGyroPID extends Command {

	public StartGyroPID() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		drivetrain.setGyroPID(SmartDashboard.getNumber("Gyro P", 0),
				SmartDashboard.getNumber("Gyro I", 0),
				SmartDashboard.getNumber("Gyro D", 0));
		drivetrain.setGyroSetpoint(SmartDashboard.getNumber("Gyro Setpoint", 0));
		drivetrain.enableGyroPID();
	}

	protected void execute() {
		drivetrain.driveArcade(0, drivetrain.gyroPID.get());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.disableGyroPID();
	}

	protected void interrupted() {
		end();
	}
}
