package org.robockets.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class WaitForGameData extends Command {

	boolean isDone = false;

	public WaitForGameData() {

	}

	protected void initialize() {
		setTimeout(1.5);
	}

	protected void execute() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			Robot.gameData = gameData;
			isDone = true;
		}
	}

	protected boolean isFinished() {
		return isTimedOut() || isDone;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
