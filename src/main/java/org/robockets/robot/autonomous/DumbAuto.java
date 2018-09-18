package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.robot.drivetrain.DriveStraight;

/**
 * @author Jake Backer
 */
public class DumbAuto extends CommandGroup {

	public DumbAuto() {
		addSequential(new DriveStraight(15, .5)); // TODO: Change this to a correct number
	}
}
