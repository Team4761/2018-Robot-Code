package org.robockets.robot.cubeintake;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author Jake Backer
 */
public class MoveBar extends Command {

	private double speed;
	private double time;

	public MoveBar(double speed, double time) {
		this.speed = speed;
		this.time = time;
	}

	public MoveBar(double speed) {
		this(speed, 0);
	}

	protected void initialize() {
		if (time != 0) {
			setTimeout(time);
		}
	}

	protected void execute() {
		Robot.cubeIntake.moveBar(speed);
	}

	protected boolean isFinished() {
		if (time != 0) {
			return isTimedOut();
		}
		return false;
	}

	protected void end() {
		Robot.cubeIntake.moveBar(0);
	}

	protected void interrupted() {
		end();
	}
}
