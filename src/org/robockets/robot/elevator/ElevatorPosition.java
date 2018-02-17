package org.robockets.robot.elevator;

public enum ElevatorPosition {

	// All in inches
	BOTTOM(0.0),
	EXCHANGE(1.1),
	SWITCH(1.5),
	MID_SCALE(3),
	HIGH_SCALE(3.5);

	private double val;

	ElevatorPosition(double val) {
		this.val = val;
	}

	public double getValue() {
		return val;
	}
}
