package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class IntakeOut extends CommandGroup {

	public IntakeOut(double intakeSpeed, double barSpeed) {
		requires(Robot.cubeIntake);

		addParallel(new MoveBothArms(-intakeSpeed));
		addParallel(new MoveBar(-barSpeed));
	}
}
