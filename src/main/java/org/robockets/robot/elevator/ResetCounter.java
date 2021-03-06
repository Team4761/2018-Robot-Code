package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.RobotMap;

/**
 * @author Jake Backer
 */
public class ResetCounter extends Command {

	public ResetCounter() {
		setRunWhenDisabled(true);
	}

	protected void initialize() {
		RobotMap.counter.reset();
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
