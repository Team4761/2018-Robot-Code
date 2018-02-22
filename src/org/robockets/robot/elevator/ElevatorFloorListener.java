package org.robockets.robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class ElevatorFloorListener extends Command {

	public ElevatorFloorListener() {
		requires(Robot.elevatorFloor);
	}

	protected void initialize() {
	}

	protected void execute() {
		double speed = -OI.operatorJoystick.getRawAxis(1);

		Robot.elevatorFloor.moveElevatorFloor(speed);
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
