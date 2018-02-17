package org.robockets.robot.cubeintake;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntakeArm extends Command {

	private RelativeDirection.XAxis arm;
	
	private double speed;
	private double time;

	public MoveIntakeArm(RelativeDirection.XAxis arm, double speed, double time) {
		this.arm = arm;

		this.speed = speed;
		this.time = time;
	}

    public MoveIntakeArm(RelativeDirection.XAxis arm, double speed) {
        this(arm, speed, 0);
    }


    // Called just before this Command runs the first time
    protected void initialize() {
		if (time != 0) {
			setTimeout(time);
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntake.moveIntakeArm(arm, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (time != 0) {
			return isTimedOut();
		}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.moveIntakeArm(arm, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
