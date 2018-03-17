/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.robockets.robot.drivetrain.Drivetrain;
import org.robockets.robot.cubeintake.CubeIntake;
import org.robockets.robot.drivetrain.ResetEncoders;
import org.robockets.robot.drivetrain.StartGyroPID;
import org.robockets.robot.elevator.Elevator;
import org.robockets.robot.elevator.ElevatorCounter;
import org.robockets.robot.elevator.ElevatorFloor;
import org.robockets.robot.climber.Climber;
import org.robockets.robot.autonomous.AutoChooser;
import org.robockets.robot.drivetrain.StartEncoderPID;
import org.robockets.robot.autonomous.AutoHelper;
import org.robockets.robot.elevator.ResetCounter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static final Climber climber = new Climber();

	private static OI m_oi;

	public static Drivetrain drivetrain;

	public static CubeIntake cubeIntake;


	// These are not used because they are run automatically as default commands
	/*public static Command joyride;
	public static Command climberListener;
	public static Command intakeListener;
	public static Command elevatorFloorListener;
	public static Command manualElevate;*/
	public static Command elevatorCounter;

	public static Elevator elevator;

	public static ElevatorFloor elevatorFloor;

	public static String gameData = "";

	private Command m_autonomousCommand;
	private SendableChooser<AutoHelper.AutoType> autoChooser = new SendableChooser<>();
	private SendableChooser<AutoHelper.RobotPosition> positionChooser = new SendableChooser<>();
	private SendableChooser<AutoHelper.Priority> priorityChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.gyro.calibrate();
		RobotMap.gyro.reset();
		RobotMap.elevatorMotor.getSensorCollection().setPulseWidthPosition(0, 0);

		RobotMap.leftEncoder.setDistancePerPulse(1.0 / 39.92);
		RobotMap.rightEncoder.setDistancePerPulse(1.0 / 39.92);

		drivetrain = new Drivetrain();

		cubeIntake = new CubeIntake();
		elevator = new Elevator();
		elevatorFloor = new ElevatorFloor();
		elevatorCounter = new ElevatorCounter();

		RobotMap.elevatorMotor.setInverted(true);
		//RobotMap.elevatorFloorMotor.setInverted(true);
		RobotMap.elevatorFloorMotor2.setInverted(true);
		RobotMap.rightEncoder.setReverseDirection(true);

		RobotMap.leftDrivepodSpeedController.setInverted(true);
		RobotMap.rightDrivepodSpeedController.setInverted(true);

		/*SmartDashboard.putNumber("Gyro P", drivetrain.gyroPID.getP());
		SmartDashboard.putNumber("Gyro I", drivetrain.gyroPID.getI());
		SmartDashboard.putNumber("Gyro D", drivetrain.gyroPID.getD());
		SmartDashboard.putNumber("Gyro Setpoint", 0);
		SmartDashboard.putData(new StartGyroPID());*/

		SmartDashboard.putNumber("Encoder P", drivetrain.encoderPID.getP());
		SmartDashboard.putNumber("Encoder I", drivetrain.encoderPID.getI());
		SmartDashboard.putNumber("Encoder D", drivetrain.encoderPID.getD());
		SmartDashboard.putNumber("Encoder Setpoint", 0);
		SmartDashboard.putData(new StartEncoderPID());

		autoChooser.addObject("Test Auto", AutoHelper.AutoType.TEST);
		autoChooser.addObject("Dumb Auto", AutoHelper.AutoType.DUMB);
		autoChooser.addObject("Min Auto", AutoHelper.AutoType.MIN);
		autoChooser.addDefault("Mid Auto", AutoHelper.AutoType.MID);

		SmartDashboard.putData("Auto mode", autoChooser);

		positionChooser.addDefault("Left", AutoHelper.RobotPosition.LEFT);
		positionChooser.addObject("Right", AutoHelper.RobotPosition.RIGHT);
		positionChooser.addObject("Middle", AutoHelper.RobotPosition.MIDDLE);

		SmartDashboard.putData("Robot Starting Position", positionChooser);

		priorityChooser.addObject("Switch", AutoHelper.Priority.SWITCH);
		priorityChooser.addObject("Scale", AutoHelper.Priority.SCALE);
		priorityChooser.addDefault("None", AutoHelper.Priority.NONE);

		SmartDashboard.putData("Autonomous Priority", priorityChooser);

		SmartDashboard.putNumber("Drivetrain Scalar", 1);
		SmartDashboard.putData("Reset Counter", new ResetCounter());

		SmartDashboard.putData("Reset Encoders", new ResetEncoders());

		elevatorCounter.start();

		m_oi = new OI();
	}

	@Override
	public void robotPeriodic() {
		//SmartDashboard.putNumber("Gyro Value", RobotMap.gyro.getAngle());
		SmartDashboard.putData(RobotMap.gyro);
		SmartDashboard.putNumber("Left Encoder Value", RobotMap.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder Value", RobotMap.rightEncoder.get());

		SmartDashboard.putNumber("Left Distance", RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", RobotMap.rightEncoder.getDistance());

		SmartDashboard.putNumber("Encoder PID Output", drivetrain.encoderPID.get());
		/*SmartDashboard.putNumber("PDP0: ", RobotMap.pdp.getCurrent(0));
		SmartDashboard.putNumber("PDP1: ", RobotMap.pdp.getCurrent(1));
		SmartDashboard.putNumber("PDP14: ", RobotMap.pdp.getCurrent(14));
		SmartDashboard.putNumber("PDP15: ", RobotMap.pdp.getCurrent(15));*/

		SmartDashboard.putNumber("Raw Elevator Encoder", elevator.getRawEncoderPos());
		SmartDashboard.putNumber("Elevator Encoder Position", elevator.getEncoderPos());
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
	 * <p>
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		String gameData = "";
		while (gameData.length() == 0) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}

		m_autonomousCommand = new AutoChooser(
				autoChooser.getSelected(),
				positionChooser.getSelected(),
				priorityChooser.getSelected(), gameData);


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
