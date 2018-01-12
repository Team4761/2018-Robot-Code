package org.robockets.robot.pidoutput;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class DrivePodPIDOutput implements PIDOutput {
	
	private SpeedController c;

	/**
	 * @param c Speed controller
	 */
	public DrivePodPIDOutput(SpeedController c) {
		this.c = c;
	}
	
	@Override
	public void pidWrite(double output) {
		c.set(output);
	}

}
