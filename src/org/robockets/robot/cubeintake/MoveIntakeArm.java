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

    public MoveIntakeArm(RelativeDirection.XAxis arm, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.arm = arm;
    	
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntake.moveIntakeArm(arm, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
