package org.robockets.robot.elevator;

public enum ElevatorPosition {

	// All in inches
	BOTTOM(0.0),
	EXCHANGE(3),
	SWITCH(31),
	MID_SCALE(62),
	HIGH_SCALE(73);

	private double val;

	ElevatorPosition(double val) {
		this.val = val;
	}

	public double getValue() {
		return val;
	}
}
