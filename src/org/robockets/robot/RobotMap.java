/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.robockets.robot.pidoutput.DrivePodPIDOutput;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Victor leftDrivepodSpeedController = new Victor(0);
	public static Victor rightDrivepodSpeedController = new Victor(1);

	public static TalonSRX elevatorMotor = new TalonSRX(2); // This is the bane of my existence
	public static Victor elevatorFloorMotor = new Victor(6); // Dominik is trash

	public static Victor rightIntake = new Victor(8); // Dominik is more trash
	public static Victor leftIntake = new Victor(4); // STROUT!!!!
	public static Victor barIntake = new Victor(5);

	public static Victor climberMotorLeft = new Victor(7);
	public static Victor climberMotorRight = new Victor(3);

	public static DifferentialDrive robotDrive = new DifferentialDrive(leftDrivepodSpeedController, rightDrivepodSpeedController);
	
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	public static Encoder leftEncoder = new Encoder(0, 1);
	public static Encoder rightEncoder = new Encoder(2, 3);

	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0); // Zero is the CAN id of the powerdistribution panel.
	public static int climberMotorPDPChannel = 1; // Dummy value.
}
