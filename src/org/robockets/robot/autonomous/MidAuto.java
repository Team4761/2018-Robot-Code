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
	private void dropCubeInSameSideSwitch(boolean teamSwitchLeft) {
		// Deposit cube in switch
		driveStraight(168); // the switch starts 140 inches away, ends 196 inches away. We will go halfway.
		double anotherSwitchAngle = (teamSwitchLeft ? -90 : 90); // The same as before. TODO: Move to higher level.
		turnAngle(anotherSwitchAngle);
		driveStraight(20);
		dropCube();
	}

	// Small bit of repeated code that could be put into a method for auto line.
	private void autoLine() {
		//Drive to auto line
		driveStraight(120); // 120 inches to auto line. Should probably do more.
	}

	// Small bit of repeated code for going to the same side scale and dropping a cube.
	private void dropCubeInSameSideScale(boolean teamSwitchLeft) {
		// Deposit cube in scale.
		driveStraight(299.65); // Drive straight the distance to the scale.
		driveStraight(34); // Drive an extra 34 inches to get to the center. TODO: do we want this?
		// dropCube(); It's unclear if we want to drop from the middle of the scale or the side.
		double scaleAngle = (teamSwitchLeft ? 90 : -90); // Angles switched @https://github.com/Team4761/2018-Robot-Code/commit/64457c0306894c1615783da3df1c77317552accf
		turnAngle(scaleAngle);
		driveStraight(20);
		dropCube();
	}

	// Code that is so ugly it should be tucked away here.
	private void dropCubeInOppositeSideScaleSShape(boolean teamSwitchLeft) {
		driveStraight(228.735); // scale starts around 261.47 inches away. switch ends 196 inches away.
		double sAngle = (teamSwitchLeft ? -90 : 90); // turn CCW if on the left, CW on the right.
		turnAngle(sAngle);
		driveStraight(264.0); // 264 inches is the length of drive station area without slanted corner.
		// Now go to the scale.
		turnAngle(-sAngle); // If we turned CW before, we want a CCW turn now to go forward.
		driveStraight(34 + 32.735); // Go to about halfway across the scale and center offset from scale/switch.
		turnAngle(sAngle); // turn towards scale, CCW if on the left, CW if on the right.
		driveStraight(30);
		// Deposit cube in scale
		dropCube();
	}

	private void dropCubeMiddleToSwitch(boolean teamSwitchLeft) {
		driveStraight(78); // Clear both the exchange rotation w/ switch box (@ 98 in).
		double smallAngle = (teamSwitchLeft ? -64 : 64); // Turn CW (-64) if the switch is left, turn CCW otherwise 
		turnAngle(smallAngle);
		// Drive distance determined by hypotenuse of triangle formed by half of switch length (87.5) and
		// switch box (42 inches). The angle was determined with the arctan of these dimensions.
		driveStraight(97);
		turnAngle(-smallAngle); // turn back.
		driveStraight(48); // drive the remaining 28 + (98-78) inches to go halfway across the switch or so.
		double switchAngle = (teamSwitchLeft ? -90 : 90);
		turnAngle(switchAngle); // turn towards the switch.
		driveStraight(52); // Drive the remaining 42 inches to the switch to dump. 52 to overdo it.
	}
}
