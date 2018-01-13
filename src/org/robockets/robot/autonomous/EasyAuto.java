package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.robot.drivetrain.DriveStraightAssisted;

/**
 * @author Jake Backer
 */
public class EasyAuto extends CommandGroup {

	public EasyAuto() {
		addSequential(new DriveStraightAssisted(120, 0.75)); // 12ft TODO: Change the speed
	}
}
