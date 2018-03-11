/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.climber.Climb;
import org.robockets.robot.cubeintake.IntakeCube;
import org.robockets.robot.cubeintake.IntakeOut;
import org.robockets.robot.drivetrain.FakeDriveAssisted;
import org.robockets.robot.drivetrain.Joyride;
import org.robockets.robot.elevator.Elevate;
import org.robockets.robot.elevator.ElevatorPosition;
import org.robockets.robot.elevator.InterruptElevator;
import org.robockets.robot.elevator.ManualElevate;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driverJoystick = new Joystick(0);
	public static Joystick operatorJoystick = new Joystick(1);

	public static Button driverA = new JoystickButton(driverJoystick, 1);
	public static Button driverB = new JoystickButton(driverJoystick, 2);
	public static Button driverY = new JoystickButton(driverJoystick, 4);
	public static Button driverSelect = new JoystickButton(driverJoystick, 7);
	public static Button driverStart = new JoystickButton(driverJoystick, 8);

	public static Button operatorA = new JoystickButton(operatorJoystick, 1);
	public static Button operatorB = new JoystickButton(operatorJoystick, 2);
	public static Button operatorX = new JoystickButton(operatorJoystick, 3);
	public static Button operatorY = new JoystickButton(operatorJoystick, 4);
	public static Button operatorSelect = new JoystickButton(operatorJoystick, 7);
	public static Button operatorLeftStick = new JoystickButton(operatorJoystick, 9);
	public static Button operatorRightStick = new JoystickButton(operatorJoystick, 10);

	public OI() {
		driverA.whileHeld(new IntakeCube(0.75, 0.4, 0.5));
		driverB.whileHeld(new IntakeOut(0.75, 0.4));
		driverY.whileHeld(new Climb(0.75));
		driverSelect.whenPressed(new Joyride());
		driverStart.whenPressed(new FakeDriveAssisted());

		operatorA.whileHeld(new IntakeCube(0.9, 0.5, 0.8));
		//operatorB.whenPressed(new Elevate(ElevatorPosition.BOTTOM, 0.5));
		//operatorX.whenPressed(new Elevate(ElevatorPosition.EXCHANGE, 0.5));
		//operatorY.whenPressed(new Elevate(ElevatorPosition.SWITCH, 0.5));
		operatorSelect.whenPressed(new InterruptElevator());
		//operatorLeftStick.whenPressed(new Elevate(ElevatorPosition.MID_SCALE, 0.75));
		//operatorRightStick.whenPressed(new Elevate(ElevatorPosition.HIGH_SCALE, 0.75));
	}
}
