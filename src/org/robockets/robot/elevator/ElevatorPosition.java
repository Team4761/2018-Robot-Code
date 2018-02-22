package org.robockets.robot.elevator;

public enum ElevatorPosition {

	// All in inches
	BOTTOM(0.0),
	EXCHANGE(1.75),
	SWITCH(24),
	MID_SCALE(73),
	HIGH_SCALE(85);

	private double val;

	ElevatorPosition(double val) {
		this.val = val;
	}

	public double getValue() {
		return val;
	}
}
