package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

import static org.robockets.robot.Robot.drivetrain;

/**
 * @author Jake Backer
 */
public class StartEncoderPID extends Command {

	public StartEncoderPID() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		drivetrain.setEncoderPID(RelativeDirection.XAxis.LEFT, SmartDashboard.getNumber("Left Encoder P", 0),
				SmartDashboard.getNumber("Left Encoder I", 0),
				SmartDashboard.getNumber("Left Encoder D", 0));
		drivetrain.setEncoderPID(RelativeDirection.XAxis.RIGHT, SmartDashboard.getNumber("Right Encoder P", 0),
				SmartDashboard.getNumber("Right Encoder I", 0),
				SmartDashboard.getNumber("Right Encoder D", 0));
		drivetrain.setDistance(SmartDashboard.getNumber("Left Encoder Setpoint", 0),
				SmartDashboard.getNumber("Right Encoder Setpoint", 0));
		drivetrain.enableEncoderPID();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		System.out.println("End");
		drivetrain.disableEncoderPID();
	}

	protected void interrupted() {
		end();
	}
}
