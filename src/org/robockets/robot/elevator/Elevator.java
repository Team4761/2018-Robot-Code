package org.robockets.robot.elevator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	private static ElevatorPosition position = ElevatorPosition.BOTTOM;


	private final double TICKS_PER_REVOLUTION = 4096;
	private final double TICKS_PER_INCH = TICKS_PER_REVOLUTION / 8;

	public Elevator() {
		RobotMap.elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ManualElevate());
	}

	public void setElevatorSpeed(double elSpeed) {
		RobotMap.elevatorMotor.set(elSpeed);
		//RobotMap.climberMotorRight.set(elSpeed);
	}

	public ElevatorPosition getCurrentPosition() {
		return position;
	}

	public void setCurrentPosition(ElevatorPosition position) {
		this.position = position;
	}

	public boolean isInPosition(ElevatorPosition position, RelativeDirection.ZAxis direction) {
		boolean isPressed = false;

		double encoderPos = position.getValue();

		if (direction == RelativeDirection.ZAxis.UP) {
			isPressed = getEncoderPos() > encoderPos;
		} else {
			isPressed = getEncoderPos() < encoderPos;
		}

		return isPressed;
	}

	public double getRawEncoderPos() {
		return RobotMap.counter.get();
	}

	public double getEncoderPos() {
		return getRawEncoderPos() / TICKS_PER_INCH;
	}
}

