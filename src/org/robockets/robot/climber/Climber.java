package org.robockets.robot.climber;

import org.robockets.robot.RobotMap;
import org.robockets.robot.pidsources.ClimberPIDSource;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Mathias Kools, Brian Shin
 * This is the climbing subsystem, specifically what allows the robot to lift itself. It could a hook.
 */
public class Climber extends Subsystem {

	final double STALLING_THRESHOLD = 70; // Stalling threshold in amperes.
	final double ENCODER_THRESHOLD = 0.5; // How many encoder distance units are allowed.
	
	final Victor climberMotor;
	final PowerDistributionPanel pdp;
	final Encoder encoder;
	
	// PID Related Variables.
	final double P = 0;
	final double I = 0;
	final double D = 0;
	final ClimberPIDSource climberPIDSource;
	public final PIDController climberPID;
	
	public Climber() {
		climberMotor = RobotMap.climberMotor;
		pdp = RobotMap.pdp;
		encoder = RobotMap.climberEncoder;
		climberPIDSource = new ClimberPIDSource(RobotMap.climberEncoder); 
		climberPID = new PIDController(P, I, D, climberPIDSource, climberMotor);
		climberPID.disable();
		climberPID.setOutputRange(-1.0, 1.0); // Output range is -1.0 to 1.0 for motor speed.
		climberPID.setAbsoluteTolerance(ENCODER_THRESHOLD);
	}
	
	/*
	 * Detect if the climbing motor is stalling.
	 * @return	whether the motor is stalling or not. true means it's stalling, false means it's not.
	 */
	public boolean isStalling() {
		return (getMaxClimberMotorCurrent() > STALLING_THRESHOLD);
	}
	
	/*
	 * Detect the amount of current going to the climber motor.
	 * @return	the amount of current going from the power distribution panel to the climber motor.
	 */
	public double getMaxClimberMotorCurrent() {
		return Math.max(pdp.getCurrent(RobotMap.climberMotorPDPChannel),
					pdp.getCurrent(RobotMap.climberMotor2PDPChannel));
	}
	
	/*
	 * Stop / brake the climbing motor(s).
	 */
	public void stop() {
		climberMotor.set(0);
	}
	
	/*
	 * Set the speed from 0 to 1 of the climber motor.
	 * @param speed		The exact speed to use with the climber motor.
	 */
	public void setMotor(double speed) {
		climberMotor.set(speed);
	}
	
	/*
	 * Get the current distance traveled by the climber encoder.
	 */
	public double getClimbingDistance() {
		return encoder.getDistance();
	}
	
	// PID methods.
	
	/*
	 * Set a climber position for the climber PID Controller to move to.
	 * @param position	The position in fee
	 */
	public void setClimberPosition(double position) {
		climberPID.setSetpoint(position);
		
	}	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

