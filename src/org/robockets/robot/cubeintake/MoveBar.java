package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class MoveBar extends Command {

	private double speed;

	public MoveBar(double speed) {
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.cubeIntake.moveBar(speed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.cubeIntake.moveBar(0);
	}

	protected void interrupted() {
		end();
	}
}
