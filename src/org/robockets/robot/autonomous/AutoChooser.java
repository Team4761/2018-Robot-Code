package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Jake Backer
 */
public class AutoChooser extends CommandGroup {

	public AutoChooser(AutoHelper.AutoType auto, AutoHelper.RobotPosition startingPosition, AutoHelper.Priority priority) {
		Command autoCommand = new DumbAuto();
		switch (auto) {
			case TEST:
				autoCommand = new TestAuto();
				break;
			case DUMB:
				autoCommand = new DumbAuto();
				break;
			case MIN:
				autoCommand = new MinAuto();
				break;
			case MID:
				autoCommand = new MidAuto(startingPosition, priority);
				break;
			case MAX:
				// autoCommand = new MaxAuto(SOMETHING_HERE);
				break;
		}

		addSequential(autoCommand);
	}
}
