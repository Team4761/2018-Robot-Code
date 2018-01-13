package org.robockets.robot.drivetrain;

import org.robockets.robot.RobotMap;
import org.robockets.robot.pidoutput.GyroPIDOutput;
import org.robockets.robot.pidsources.EncoderPIDSource;
import org.robockets.robot.pidsources.GyroPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Brian Shin
 * Drivetrain Subsystem
 */
public class Drivetrain extends Subsystem {
	
	 private final EncoderPIDSource leftPodPIDSource;
	 private final EncoderPIDSource rightPodPIDSource;
	 private final GyroPIDSource gyroPIDSource;
	 private final PIDController leftPodPID;
	 private final PIDController rightPodPID;
	 private final PIDController gyroPID;

	public Drivetrain() {
		
		leftPodPIDSource = new EncoderPIDSource(RobotMap.leftEncoder, 1); 
		leftPodPID = new PIDController(0, 0, 0, leftPodPIDSource, RobotMap.leftDrivePodOutput);
        leftPodPID.disable();
        leftPodPID.setOutputRange(-1.0, 1.0);
        leftPodPID.setAbsoluteTolerance(0.5);
        
        rightPodPIDSource = new EncoderPIDSource(RobotMap.rightEncoder, 1);
        rightPodPID = new PIDController(0, 0, 0, rightPodPIDSource, RobotMap.rightDrivePodOutput);
        rightPodPID.disable();
        rightPodPID.setOutputRange(-1.0, 1.0);
        rightPodPID.setAbsoluteTolerance(0.5);
		
		gyroPIDSource = new GyroPIDSource();
        gyroPID = new PIDController(0, 0, 0, gyroPIDSource, new GyroPIDOutput());
        gyroPID.disable();
        gyroPID.setOutputRange(-1.0, 1.0); // Set turning speed range
        gyroPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Joyride());
    }
    
    /**
     * Basic method to control the movement of the robot 'tank' style
     * @param leftSpeed The value to use for left side motors. [-1.0..1.0]
     * @param rightSpeed The value to use for right side motors. [-1.0..1.0]
     */
    public void driveTank(double leftSpeed, double rightSpeed) {
    	RobotMap.robotDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    /**
     * Basic method to control the movement of the robot 'arcade' style
     * @param translateValue The value to use for forwards/backwards on both side motors. [-1.0..1.0]
     * @param rotateValue The value to use for the rate of rotation right/left. [-1.0..1.0]
     */
    public void driveArcade(double translateValue, double rotateValue) {
    	RobotMap.robotDrive.arcadeDrive(translateValue, rotateValue);
    }
    
    /**
     * Stop all robot drivetrain motion
     */
    public void stop() {
    	driveTank(0, 0);
    }
    
    /**
     * A basic method to set the setpoint of both of the drive pods for a given distance with PID
     * @param distance Desired distance for both pods, in inches
     */
    public void setDistance(double distance) {
    	rightPodPID.setSetpoint(distance);
    	leftPodPID.setSetpoint(distance);
    }
    
    /**
     * A basic method to set the setpoint of each of the drive pods for a given distance with PID
     * @param leftDistance Desired distance for the left pod, in inches
     * @param rightDistance Desired distance for the right pod, in inches
     */
    public void setDistance(double leftDistance, double rightDistance) {
    	leftPodPID.setSetpoint(leftDistance);
    	rightPodPID.setSetpoint(rightDistance);
    }

	/**
	 * Since the built in OnTarget for PID is terrible and broken, this is a manual one for the drive pods
	 * @return Returns if both the encoder PIDs are OnTarget, with a tolerance of <code>ABSOLUTE_TOLERANCE</code>
	 */
	public boolean encodersOnTarget() {
		final double ABSOLUTE_TOLERANCE = 1.0; // TODO: Change this to something more reasonable
		return Math.abs((leftPodPID.getSetpoint() - leftPodPIDSource.pidGet())) <= ABSOLUTE_TOLERANCE &&
				Math.abs((rightPodPID.getSetpoint() - rightPodPIDSource.pidGet())) <= ABSOLUTE_TOLERANCE;
	}

	public void enableEncoderPID() {
		leftPodPID.enable();
		rightPodPID.enable();
	}
}

