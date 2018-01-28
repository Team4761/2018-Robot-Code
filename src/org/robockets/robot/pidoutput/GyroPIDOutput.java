package org.robockets.robot.pidoutput;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.robockets.robot.Robot;

public class GyroPIDOutput implements PIDOutput {

	private DifferentialDrive dd;

	public GyroPIDOutput(DifferentialDrive dd) {
		this.dd = dd;
	}
    
    @Override
    public void pidWrite(double output) {
    	dd.arcadeDrive(0, -output);
    }
}
