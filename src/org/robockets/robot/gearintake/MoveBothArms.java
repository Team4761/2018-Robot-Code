package org.robockets.robot.gearintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.commons.RelativeDirection;

/**
 * @author Jake Backer
 */
public class MoveBothArms extends CommandGroup {

	public MoveBothArms(double speed) {
		addParallel(new MoveIntakeArm(RelativeDirection.XAxis.LEFT, speed));
		addParallel(new MoveIntakeArm(RelativeDirection.XAxis.RIGHT, speed));
	}
}
