package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.commons.RelativeDirection;

/**
 * @author Jake Backer
 */
public class MoveBothArms extends CommandGroup {

	public MoveBothArms(double speed, double time) {
		addParallel(new MoveIntakeArm(RelativeDirection.XAxis.LEFT, speed, time));
		addParallel(new MoveIntakeArm(RelativeDirection.XAxis.RIGHT, speed, time));
	}

	public MoveBothArms(double speed) {
		this(speed, 0);
	}
}
