package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Jake Backer
 */
public class IntakeCube extends CommandGroup {

	public IntakeCube(double speed) {
		addParallel(new MoveBothArms(speed));
		addParallel(new MoveBar(speed));
	}
}
