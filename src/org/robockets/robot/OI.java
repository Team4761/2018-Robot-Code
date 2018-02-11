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
import org.robockets.robot.cubeintake.IntakeCube;
import org.robockets.robot.elevator.InterruptElevator;
import org.robockets.robot.elevator.ManualElevate;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driverJoystick = new Joystick(0);
	public static Joystick operatorJoystick = new Joystick(1);

	public static Button operatorA = new JoystickButton(operatorJoystick, 1);
	public static Button operatorSelect = new JoystickButton(operatorJoystick, 7);

	public OI() {
		operatorA.whileHeld(new IntakeCube(1));
		operatorSelect.whenPressed(new InterruptElevator());
	}
}
