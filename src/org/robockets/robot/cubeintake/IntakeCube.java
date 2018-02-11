package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;
import org.robockets.robot.elevator.Elevate;
import org.robockets.robot.elevator.ElevatorPosition;
import org.robockets.robot.elevator.MoveElevatorFloor;

/**
 * @author Jake Backer
 */
public class IntakeCube extends CommandGroup {

	public IntakeCube(double speed) {
		requires(Robot.cubeIntake);
		requires(Robot.elevator);

		addSequential(new Elevate(ElevatorPosition.BOTTOM, speed));
		addParallel(new MoveBothArms(speed));
		addParallel(new MoveBar(speed));
		addParallel(new MoveElevatorFloor(speed));
	}
}
