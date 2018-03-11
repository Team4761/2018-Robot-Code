package org.robockets.robot.elevator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	private ElevatorPosition position = ElevatorPosition.BOTTOM;

	private final double TICKS_PER_INCH = 1.1428571428571428571428571428571;

	private int elevatorTicks = 0;

	private RelativeDirection.ZAxis lastDirection = RelativeDirection.ZAxis.UP;

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

		if (elSpeed < 0.1 && elSpeed > 0.1) {
			lastDirection = RelativeDirection.ZAxis.DOWN;
		} else if (elSpeed > 0) {
			lastDirection = RelativeDirection.ZAxis.UP;
		} else if (elSpeed < 0) {
			lastDirection = RelativeDirection.ZAxis.DOWN;
		}

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
		boolean isInPosition = false;

		double encoderPos = position.getValue();

		System.out.println("New Encoder Pos: " + encoderPos);
		System.out.println("Encoder Pos: " + getEncoderPos());

		if (direction == RelativeDirection.ZAxis.UP) {
			isInPosition = getEncoderPos() > encoderPos;
		} else {
			isInPosition = getEncoderPos() < encoderPos;
		}

		System.out.println("Is in position: " + isInPosition);

		return isInPosition;
	}

	public double getRawEncoderPos() {
		return elevatorTicks;
	}

	public RelativeDirection.ZAxis getLastDirection() {
		return lastDirection;
	}

	public void countUp() {
		elevatorTicks++;
	}

	public void countDown() {
		elevatorTicks--;
	}

	public void resetCounter() {
		elevatorTicks = 0;
	}

	public double getEncoderPos() {
		return getRawEncoderPos() / TICKS_PER_INCH;
	}
}

