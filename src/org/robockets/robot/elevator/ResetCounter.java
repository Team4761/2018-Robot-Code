package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ResetCounter extends Command {

	public ResetCounter() {
		setRunWhenDisabled(true);
	}

	protected void initialize() {
		setTimeout(1);
		Robot.elevator.resetCounter();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
