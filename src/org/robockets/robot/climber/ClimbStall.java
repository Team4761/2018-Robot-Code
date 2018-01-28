package org.robockets.robot.climber;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will climb the scale until the motor stalls.
 */
public class ClimbStall extends Command {

	double speed;
	
	/*
	 * Climb until the robot climber motor stalls.
	 */
    public ClimbStall(RelativeDirection.ZAxis direction) {
    	requires(Robot.climber);
    	speed = (direction == RelativeDirection.ZAxis.UP ? 1 : -1);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.setMotor(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.climber.isStalling();
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
