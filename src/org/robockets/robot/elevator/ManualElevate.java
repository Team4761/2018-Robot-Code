package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class ManualElevate extends Command {

	public ManualElevate() {
		requires(Robot.elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		double speed = -OI.operatorJoystick.getRawAxis(5);

		Robot.elevator.setElevatorSpeed(speed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
\
