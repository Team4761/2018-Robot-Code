package org.robockets.robot.pidsources;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class ClimberPIDSource implements PIDSource {
	private Encoder encoder;

	public ClimberPIDSource(Encoder encoder) {
		this.encoder = encoder;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	}

	@Override
	public double pidGet() {
		return encoder.getRate();
	}
}
