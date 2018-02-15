package org.robockets.robot.climber;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will climb the scale given certain starting parameters.
 */
public class Climb extends Command {
	
	/*
	 * Climb until the robot climber motor stalls.
	 */
    public Climb(){
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSide = OI.driverJoystick.getRawAxis(2);
    	double rightSide = OI.driverJoystick.getRawAxis(3);

    	leftSide *= OI.driverJoystick.getRawButton(5) ? -1 : 1;
    	rightSide *= OI.driverJoystick.getRawButton(6) ? -1 : 1;

    	if (!Robot.climber.isStalling()) {
			Robot.climber.setSpeed(leftSide, rightSide);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
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
