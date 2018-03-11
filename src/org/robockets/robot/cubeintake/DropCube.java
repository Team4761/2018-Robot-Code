package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.robot.elevator.MoveElevatorFloor;

/**
 * Command stub to simulate the presence of a DropCube commandgroup.
 *
 * @author Mathias Kools
 */
public class DropCube extends CommandGroup {
	public DropCube() {
		addParallel(new MoveElevatorFloor(-0.75, 1));
	}
}
