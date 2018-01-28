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
		drivetrain.setGyroPID(SmartDashboard.getNumber("Gyro P", 0),
				SmartDashboard.getNumber("Gyro I", 0),
				SmartDashboard.getNumber("Gyro D", 0));
		System.out.println(drivetrain.gyroPID.getP());
		System.out.println(drivetrain.gyroPID.getI());
		System.out.println(drivetrain.gyroPID.getD());
		System.out.println("-------");
		System.out.println("GyroPID: " + Robot.drivetrain.gyroPID.getP() + Robot.drivetrain.gyroPID.getI() + Robot.drivetrain.gyroPID.getD());
		System.out.println("Setpoint: " + Robot.drivetrain.gyroPID.getSetpoint());
		System.out.println("-------");
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
