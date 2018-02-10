package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class IntakeListener extends Command {

	public IntakeListener() {
		requires(Robot.cubeIntake);
	}

	protected void initialize() {
	}

	protected void execute() {
		double leftIntake = -OI.operatorJoystick.getRawAxis(2);
		double rightIntake = -OI.operatorJoystick.getRawAxis(3);

		boolean isLeftInverted = OI.operatorJoystick.getRawButton(5);
		boolean isRightInverted = OI.operatorJoystick.getRawButton(6);

		Robot.cubeIntake.moveIntakeArm(RelativeDirection.XAxis.LEFT, isLeftInverted ? -leftIntake : leftIntake);
		Robot.cubeIntake.moveIntakeArm(RelativeDirection.XAxis.RIGHT, isRightInverted ? -rightIntake : rightIntake);
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
