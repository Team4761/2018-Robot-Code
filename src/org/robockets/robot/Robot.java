/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.robot.autonomous.AutoChooser;
import org.robockets.robot.drivetrain.Drivetrain;
import org.robockets.robot.drivetrain.Joyride;
import org.robockets.robot.drivetrain.StartEncoderPID;
import org.robockets.robot.utility.AutoHelper;
import org.robockets.robot.drivetrain.StartGyroPID;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	
	public static Drivetrain drivetrain;

	public static Command joyride;

	Command m_autonomousCommand;
	SendableChooser<AutoHelper.AutoType> autoChooser = new SendableChooser<>();
	SendableChooser<AutoHelper.RobotPosition> positionChooser = new SendableChooser<>();
	SendableChooser<AutoHelper.Priority> priorityChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.gyro.calibrate();
		RobotMap.gyro.reset();

		drivetrain = new Drivetrain();
		RobotMap.leftEncoder.setDistancePerPulse(1/39.92);
		RobotMap.rightEncoder.setDistancePerPulse(1/39.07);
		RobotMap.leftDrivepodSpeedController.setInverted(true);
		RobotMap.rightDrivepodSpeedController.setInverted(true);
		RobotMap.leftEncoder.setReverseDirection(true);

		/*SmartDashboard.putNumber("Gyro P", drivetrain.gyroPID.getP());
		SmartDashboard.putNumber("Gyro I", drivetrain.gyroPID.getI());
		SmartDashboard.putNumber("Gyro D", drivetrain.gyroPID.getD());
		SmartDashboard.putNumber("Gyro Setpoint", 0);
		SmartDashboard.putData(new StartGyroPID());*/

		SmartDashboard.putNumber("Left Encoder P", drivetrain.leftPodPID.getP());
		SmartDashboard.putNumber("Left Encoder I", drivetrain.leftPodPID.getI());
		SmartDashboard.putNumber("Left Encoder D", drivetrain.leftPodPID.getD());
		SmartDashboard.putNumber("Left Encoder Setpoint", 0);

		SmartDashboard.putNumber("Right Encoder P", drivetrain.rightPodPID.getP());
		SmartDashboard.putNumber("Right Encoder I", drivetrain.rightPodPID.getI());
		SmartDashboard.putNumber("Right Encoder D", drivetrain.rightPodPID.getD());
		SmartDashboard.putNumber("Right Encoder Setpoint", 0);
		SmartDashboard.putData(new StartEncoderPID());

		joyride = new Joyride();

		autoChooser.addObject("Test Auto", AutoHelper.AutoType.TEST);
		autoChooser.addDefault("Dumb Auto", AutoHelper.AutoType.DUMB);
		autoChooser.addObject("Min Auto", AutoHelper.AutoType.MIN);

		SmartDashboard.putData("Auto mode", autoChooser);

		positionChooser.addDefault("Left", AutoHelper.RobotPosition.LEFT);
		positionChooser.addObject("Right", AutoHelper.RobotPosition.RIGHT);
		positionChooser.addObject("Middle", AutoHelper.RobotPosition.MIDDLE);

		SmartDashboard.putData("Robot Starting Position", positionChooser);

		priorityChooser.addObject("Switch", AutoHelper.Priority.SWITCH);
		priorityChooser.addObject("Scale", AutoHelper.Priority.SCALE);
		priorityChooser.addDefault("None", AutoHelper.Priority.NONE);

		SmartDashboard.putData("Autonomous Priority", priorityChooser);

		m_oi = new OI();

		SmartDashboard.putNumber("Drivetrain Scalar", 1);
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Gyro Value", RobotMap.gyro.getAngle());
		SmartDashboard.putNumber("Left Encoder Value", RobotMap.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder Value", RobotMap.rightEncoder.get());

		SmartDashboard.putNumber("Left Distance", RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", RobotMap.rightEncoder.getDistance());

		SmartDashboard.putNumber("Left PID Output", drivetrain.leftPodPID.get());
		SmartDashboard.putNumber("Right PID Output", drivetrain.rightPodPID.get());
		/*SmartDashboard.putNumber("PDP0: ", RobotMap.pdp.getCurrent(0));
		SmartDashboard.putNumber("PDP1: ", RobotMap.pdp.getCurrent(1));
		SmartDashboard.putNumber("PDP14: ", RobotMap.pdp.getCurrent(14));
		SmartDashboard.putNumber("PDP15: ", RobotMap.pdp.getCurrent(15));*/
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = new AutoChooser(
				autoChooser.getSelected(),
				positionChooser.getSelected(),
				priorityChooser.getSelected());

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		joyride.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		LiveWindow.addActuator("Drivetrain", "RobotDrive", RobotMap.robotDrive);
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
