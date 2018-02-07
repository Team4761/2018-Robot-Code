package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.elevator.MoveElevatorFloor;

/**
 * @author Jake Backer
 */
public class IntakeCube extends CommandGroup {

	public IntakeCube(double speed) {
		addParallel(new MoveBothArms(speed));
		addParallel(new MoveBar(speed));
		addParallel(new MoveElevatorFloor(RelativeDirection.Malone.IN, 1));
	}
}
