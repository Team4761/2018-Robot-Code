package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ResetEncoders extends Command {

	public ResetEncoders() {
		setRunWhenDisabled(true);
	}

	protected void initialize() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
