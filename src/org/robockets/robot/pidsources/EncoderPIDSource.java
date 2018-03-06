package org.robockets.robot.pidsources;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {
	private Encoder encoder1;
	private Encoder encoder2;

	/**
	 * Encoder PID Source
	 */
	public EncoderPIDSource(Encoder encoder1, Encoder encoder2) {
		this.encoder1 = encoder1;
		this.encoder2 = encoder2;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return (encoder1.get() + encoder2.get())/2.0;
	}
}
