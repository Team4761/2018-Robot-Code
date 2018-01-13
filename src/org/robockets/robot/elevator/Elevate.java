package org.robockets.robot.elevator;

import org.robockets.robot.Robot;
import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevate extends Command {
	boolean isUp = false;
	double elSpeed = 0.0;
    public Elevate(boolean isUp, double elSpeed) {
    	this.isUp = isUp;
    	this.elSpeed = elSpeed; 
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setElevatorSpeed(elSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
