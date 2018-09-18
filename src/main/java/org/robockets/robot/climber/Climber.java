package org.robockets.robot.climber;

import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Mathias Kools
 * This is the climbing subsystem, specifically what allows the robot to lift itself. It could a hook.
 */
public class Climber extends Subsystem {

	final double STALLING_THRESHOLD = 70; // Stalling threshold in amperes.

	public Climber() {
	}

	/*
	 * Detect if the climbing motor is stalling.
	 * @return	whether the motor is stalling or not. true means it's stalling, false means it's not.
	 */
	public boolean isStalling() {
		return (getClimberMotorCurrent() > STALLING_THRESHOLD);
	}

	/*
	 * Detect the amount of current going to the climber motor.
	 * @return	the amount of current going from the power distribution panel to the climber motor.
	 */
	public double getClimberMotorCurrent() {
		return RobotMap.pdp.getCurrent(RobotMap.climberMotorPDPChannel);
	}

	/*
	 * Stop / brake the climbing motor(s).
	 */
	public void stop() {
		RobotMap.climberMotor.set(0);
	}

	/*
	 * Set the speed from 0 to 1 of the climber motor.
	 * @param speed		The exact speed to use with the climber motor.
	 */
	public void setSpeed(double speed) {
		RobotMap.climberMotor.set(speed);
	}

	// SmartDashboard related commands.

	/*
	 * Update different display values on the SmartDashboard / Shuffleboard.
	 */
	public void periodicSmartDashboard() {
		SmartDashboard.putBoolean("Climber stalling", isStalling());
		SmartDashboard.putNumber("Climber motor current", getClimberMotorCurrent());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}

