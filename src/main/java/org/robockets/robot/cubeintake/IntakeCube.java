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

	public IntakeCube(double intakeSpeed, double barSpeed, double floorSpeed) {
		requires(Robot.cubeIntake);
		requires(Robot.elevatorFloor);

		//addSequential(new Elevate(ElevatorPosition.BOTTOM, intakeSpeed));
		addParallel(new MoveBothArms(intakeSpeed));
		addParallel(new MoveBar(barSpeed));
		addParallel(new MoveElevatorFloor(floorSpeed));
	}

	public IntakeCube(double intakeSpeed, double barSpeed, double floorSpeed, double time) {
		requires(Robot.cubeIntake);
		requires(Robot.elevatorFloor);

		//addSequential(new Elevate(ElevatorPosition.BOTTOM, intakeSpeed));
		addParallel(new MoveBothArms(intakeSpeed, time));
		addParallel(new MoveBar(barSpeed, time));
		addParallel(new MoveElevatorFloor(floorSpeed, time));
	}

}
