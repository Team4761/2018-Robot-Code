package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.robockets.robot.commands.DropCube;
import org.robockets.robot.drivetrain.DriveAngleAssisted;
import org.robockets.robot.drivetrain.DriveStraightAssisted;
import org.robockets.robot.utility.AutoHelper;

/**
 * @author Mathias Kools, Jake Backer
 */
public class MidAuto extends CommandGroup {

	// TODO: I use driveStraight sometimes to RAM, these should be changed to timed drives.

	public MidAuto(AutoHelper.RobotPosition robotPosition, AutoHelper.Priority priority) {
		// Get preliminary data.
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		boolean teamSwitchLeft = gameData.charAt(0) == 'L'; // true if switch is on the left from our driver perspective.
		boolean scaleLeft = gameData.charAt(1) == 'L';
		boolean scaleSameSide = (scaleLeft == true) == (robotPosition == AutoHelper.RobotPosition.LEFT);
		boolean teamSwitchSameSide = (teamSwitchLeft == true) == (robotPosition == AutoHelper.RobotPosition.LEFT);
		// true == true on left and false == false on right.

		// Start logic.
		switch (robotPosition) {
			case MIDDLE: // If in position 2 (middle)
				// Drive to our switch position
				dropCubeMiddleToSwitch(teamSwitchLeft);
				break;
			case LEFT: // Else if in position 1 (left) or 3 (right)
			case RIGHT: // These two enums are just here for readability & changeability.
			default:
				switch (priority) {
					case SCALE: //If priority is scale.
						if (scaleSameSide) { // If scale is on our side
							// Deposit cube in scale
							dropCubeInSameSideScale(teamSwitchLeft);
						} else { // scale is on other side
							if (teamSwitchSameSide == false) { // If switch is on other side
								// Drive in S shape to other side
								dropCubeInOppositeSideScaleSShape(teamSwitchLeft);
							} else { // If switch is on our side
								// We can just drop in switch or still drive in s
							}
						}
						break;
					case SWITCH: // Else If priority is switch
						if (teamSwitchSameSide) { // If switch is on our side
							// Deposit cube in switch.
							dropCubeInSameSideSwitch(teamSwitchLeft);
						} else { // Else
							// If scale is on our side
							if (teamSwitchSameSide) {
								// Deposit cube in scale
								dropCubeInSameSideScale(teamSwitchLeft);
							} else { // Else
								// Drive to auto line.
								autoLine();
							}
						}
						break;
					default: // Else
						if (scaleSameSide) { // If scale is on our side
							// Deposit cube in scale
							dropCubeInSameSideScale(teamSwitchLeft);
						} else if (teamSwitchSameSide) { // Else if switch is on our side
							// Deposit cube in switch
							dropCubeInSameSideSwitch(teamSwitchLeft);
						} else { // Else
							// Drive to auto line.
							autoLine();
						}
				}
		}
	}

	// Small helper method to make typing less of a pain.
	private void driveStraight(double distance) {
		addSequential(new DriveStraightAssisted(distance));
	}

	// Small helper method to make typing less of a pain.
	private void turnAngle(double angle) {
		addSequential(new DriveAngleAssisted(angle));
	}

	// Small helper method for dropping cubes.
	private void dropCube() {
		addSequential(new DropCube());
	}

	// Small bit of repeated code that could be put into a method.
	// Starting position has the corner of robot right where slant of corner of field starts.
	private void dropCubeInSameSideSwitch(boolean teamSwitchLeft) {
		// Deposit cube in switch
		driveStraight(148.5);
		 // 90deg CW if on the left.
		turnAngle(teamSwitchLeft ? 90 : -90);
		// align edge of robot to edge of switch.
		driveStraight(16.56); 
		dropCube();
	}

	// Small bit of repeated code that could be put into a method for auto line.
	private void autoLine() {
		//Drive to auto line
		driveStraight(120); // 120 inches to auto line.
	}

	// Small bit of repeated code for going to the same side scale and dropping a cube.
	// Starting position the same as dropCubeInSameSideSwitch.
	private void dropCubeInSameSideScale(boolean teamSwitchLeft) {
		// Deposit cube in scale.
		 // Drive straight the distance to the scale.
		driveStraight(305.15);
		// 90deg CW if on the left.
		turnAngle(teamSwitchLeft ? 90 : -90);
		driveStraight(2.88);
		dropCube();
	}

	// Code that is so ugly it should be tucked away here.
	private void dropCubeInOppositeSideScaleSShape(boolean teamSwitchLeft) {
		driveStraight(209.235);
		double angle = teamSwitchLeft ? 90 : -90;
		turnAngle(angle); // turn 90deg CW when on the left.
		driveStraight(244.5);
		turnAngle(-angle); // 90deg CCW when starting from the left.
		driveStraight(98.915);
		turnAngle(-angle);
		driveStraight(2.88);
		dropCube();
	}

	// The starting position for this middle auto is 1 ft from the right side
	// of the exchange to the side of the robot.
	private void dropCubeMiddleToSwitch(boolean teamSwitchLeft) {
		driveStraight(50); // 50 of all the numbers was chosen arbitrarily.
		if (teamSwitchLeft == false) {
			// If on the right. Move to a new starting position.
			turnAngle(-90); // Turn a right angle CCW.
			driveStraight(39);
			turnAngle(90); // Reset to face straight again.
		}
		double smallAngle = (teamSwitchLeft ? 41.7 : -41.7); // CW first on the left.
		turnAngle(smallAngle);
		driveStraight(52.34);
		turnAngle(-smallAngle);
		driveStraight(10);
		dropCube();
	}
}
