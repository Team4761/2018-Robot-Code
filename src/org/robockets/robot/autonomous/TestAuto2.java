package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.robockets.robot.drivetrain.DriveStraight;

/**
 * @author Jake Backer
 */
public class TestAuto2 extends CommandGroup {

	public TestAuto2() {
		addSequential(new DriveStraight(2, 0.5));
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraight(2, -0.5));
	}
}
