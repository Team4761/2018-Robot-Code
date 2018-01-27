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
		requires(drivetrain);
	}

	protected void initialize() {
		System.out.println("GyroPID: " + Robot.drivetrain.gyroPID.getP() + Robot.drivetrain.gyroPID.getI() + Robot.drivetrain.gyroPID.getD());
		System.out.println("Setpoint: " + Robot.drivetrain.gyroPID.getSetpoint());
		drivetrain.setGyroSetpoint(SmartDashboard.getNumber("Gyro Setpoint", 0));
		drivetrain.enableGyroPID();
	}

	protected void execute() {
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
