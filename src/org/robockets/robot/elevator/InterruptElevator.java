package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class InterruptElevator extends Command {

	public InterruptElevator() {
		requires(Robot.elevator);
	}

	protected void initialize() {
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
