package org.robockets.robot.utility;

/**
 * A helper class to assist with autonomous choosing
 */
public class AutoHelper {

	/**
	 * The starting position of the robot
	 */
	public enum RobotPosition {
		LEFT,
		RIGHT,
		MIDDLE
	}

	/**
	 * Which mechanism is prioritized
	 */
	public enum Priority {
		SWITCH,
		SCALE,
		NONE
	}
}
