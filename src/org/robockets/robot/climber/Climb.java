package org.robockets.robot.climber;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will climb the scale given certain starting parameters.
 */
public class Climb extends Command {

	boolean stall = false;
	double time = 0;

	private RelativeDirection.ZAxis direction;
	
	/*
	 * Climb until the robot climber motor stalls.
	 */
    public Climb(RelativeDirection.ZAxis direction) {
    	requires(Robot.climber);
    	stall = true;
    	this.direction = direction;
    }
    
    /* 
     * Climb for a given amount of time or indefinitely.
     * @param time		How long to climb for given a certain time. If zero, climb indefinitely.
     */
    public Climb(double time, RelativeDirection.ZAxis direction) {
    	this.time = time;
    	if (time != 0) {
    		setTimeout(time);
    	}
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.setSpeed(direction == RelativeDirection.ZAxis.UP ? 1 : -1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (stall == true) {
    		return Robot.climber.isStalling();
    	} else if (time == 0) {
    		return false;
    	} else {
    		return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
