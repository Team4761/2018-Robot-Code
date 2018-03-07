package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.robockets.robot.drivetrain.DriveAngleAssisted;
import org.robockets.robot.drivetrain.DriveStraightAssisted;
import org.robockets.robot.drivetrain.TurnAbsolute;

/**
 * @author Jake Backer
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		addSequential(new DriveStraightAssisted(120));
		addSequential(new TurnAbsolute(90));
		addSequential(new DriveStraightAssisted(36)); // BE CAREFUL WITH THIS
	}
}
