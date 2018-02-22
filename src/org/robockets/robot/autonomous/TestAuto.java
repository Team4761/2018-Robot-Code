package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.robockets.robot.drivetrain.DriveAngleAssisted;
import org.robockets.robot.drivetrain.DriveStraightAssisted;

/**
 * @author Jake Backer
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		addSequential(new DriveStraightAssisted(36));
		addSequential(new WaitCommand(0.2));
		addSequential(new DriveStraightAssisted(-36));
	}
}
