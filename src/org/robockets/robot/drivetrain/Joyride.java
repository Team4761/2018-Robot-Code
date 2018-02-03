package org.robockets.robot.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.robot.OI;
import org.robockets.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Brian Shin
 * User-input driving command for teloperated mode.
 */
public class Joyride extends Command {
	
	private double translate;
    private double rotate;

    public Joyride() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		translate = -OI.joystick.getRawAxis(1);
		rotate = -OI.joystick.getRawAxis(4);

		double scalar = SmartDashboard.getNumber("Drivetrain Scalar", 1);

		translate *= scalar;
		rotate *= scalar;
		
		Robot.drivetrain.driveArcade(translate, rotate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
