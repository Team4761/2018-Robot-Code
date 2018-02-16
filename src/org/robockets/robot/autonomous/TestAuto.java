package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.robot.drivetrain.DriveAngleAssisted;
import org.robockets.robot.drivetrain.DriveStraightAssisted;

/**
 * @author Jake Backer
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		addSequential(new DriveStraightAssisted(120));
		addSequential(new DriveAngleAssisted(90));
		/*addSequential(new DriveStraightAssisted(36));*/
	}
}
