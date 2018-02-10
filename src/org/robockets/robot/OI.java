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
import org.robockets.commons.RelativeDirection;
import org.robockets.robot.elevator.ManualElevate;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driverJoystick = new Joystick(0);
	public static Joystick operatorJoystick = new Joystick(1);

	public static Button operatorSelect = new JoystickButton(operatorJoystick, 7);
	public static Button operatorStart = new JoystickButton(operatorJoystick, 8);

	public OI() {
		operatorSelect.whileHeld(new ManualElevate(RelativeDirection.ZAxis.DOWN));
		operatorStart.whileHeld(new ManualElevate(RelativeDirection.ZAxis.UP));
	}
}
