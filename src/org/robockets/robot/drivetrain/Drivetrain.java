package org.robockets.robot.drivetrain;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;
import org.robockets.robot.pidoutput.GyroPIDOutput;
import org.robockets.robot.pidsources.DummyPIDOutput;
import org.robockets.robot.pidsources.EncoderPIDSource;
import org.robockets.robot.pidsources.GyroPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Brian Shin
 * Drivetrain Subsystem
 */
public class Drivetrain extends Subsystem {

	private final EncoderPIDSource encoderPIDSource;
	private final GyroPIDSource gyroPIDSource;

	public final PIDController encoderPID;
	public final PIDController gyroPID;

	public final double ENCODER_KP = 0.03;

	public Drivetrain() {

		encoderPIDSource = new EncoderPIDSource(RobotMap.leftEncoder, RobotMap.rightEncoder);
		encoderPID = new PIDController(0.05, 0.00001, 0.00001, encoderPIDSource,
				new DummyPIDOutput()); // This might need to change
		encoderPID.disable();
		encoderPID.setOutputRange(-0.5, 0.5);
		encoderPID.setAbsoluteTolerance(2);

		gyroPIDSource = new GyroPIDSource();
		gyroPID = new PIDController(0.05, 0.0004, 0.15, gyroPIDSource, new DummyPIDOutput());
		gyroPID.disable();
		gyroPID.setOutputRange(-0.75, 0.75); // Set turning speed range
		gyroPID.setAbsoluteTolerance(3);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new Joyride());
	}

	/**
	 * Basic method to control the movement of the robot 'tank' style
	 *
	 * @param leftSpeed  The value to use for left side motors. [-1.0..1.0]
	 * @param rightSpeed The value to use for right side motors. [-1.0..1.0]
	 */
	public void driveTank(double leftSpeed, double rightSpeed) {
		RobotMap.robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

	/**
	 * Basic method to control the movement of the robot 'arcade' style
	 *
	 * @param translateValue The value to use for forwards/backwards on both side motors. [-1.0..1.0]
	 * @param rotateValue    The value to use for the rate of rotation right/left. [-1.0..1.0]
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

	public void setGyroPID(double p, double i, double d) {
		gyroPID.setPID(p, i, d);
	}

	public void setEncoderPID(double p, double i, double d) {
		encoderPID.setPID(p, i, d);
	}

	/**
	 * A basic method to set the setpoint of both of the drive pods for a given distance with PID
	 *
	 * @param distance Desired distance for both pods, in inches
	 */
	public void setDistance(double distance) {
		encoderPID.setSetpoint(distance);
	}

	public void enableEncoderPID() {
		encoderPID.enable();
	}

	public void setGyroSetpoint(double angle) {
		gyroPID.setSetpoint(angle);
	}

	public void enableGyroPID() {
		gyroPID.enable();
	}

	public void disableGyroPID() {
		gyroPID.disable();
	}

	public void disableEncoderPID() {
		encoderPID.disable();
	}

	public double getGyroPos() {
		return RobotMap.gyro.getAngle();
	}

	public double getEncoderPos() {
		return (RobotMap.leftEncoder.get() + RobotMap.rightEncoder.get()) / 2.0;
	}
}

