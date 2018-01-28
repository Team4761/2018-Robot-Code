package org.robockets.robot.climber;

import org.robockets.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will climb the scale given certain starting parameters.
 */
public class ClimbDistance extends Command {

	double speed;
	boolean PID;
	double distance;
	
    /* 
     * Climb for a given amount of time or indefinitely.
     * @param distance	How far to climb with the climber system.
     * @param usePID	Do you use the PID controller to move a certain distance.
     */
    public ClimbDistance(double setPoint, boolean usePID) {
    	requires(Robot.climber);
    	this.PID = usePID;
    	this.distance = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.setMotor(speed);
    	if (this.PID == true) {
    		Robot.climber.climberPID.enable();
    		Robot.climber.setClimberPosition(distance);
    	} else {
    		Robot.climber.climberPID.disable();
    		if (Robot.climber.getClimbingDistance() > distance) {
    			Robot.climber.setMotor(1);
    		} else {
    			Robot.climber.setMotor(-1);
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (PID) {
    		return Robot.climber.climberPID.onTarget();
    	} else {
    		// If we are not using PID it's okay to overshoot.
    		if (Robot.climber.climberMotor.getSpeed() > 0)
    			return Robot.climber.getClimbingDistance() >= distance;
    		else
    			return Robot.climber.getClimbingDistance() <= distance; // We want it to be less if moving down.
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
