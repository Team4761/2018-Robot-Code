package org.robockets.robot.pidsources;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {
	private Encoder encoder;

	/**
	 * Encoder PID Source
	 *
	 * @param encoder The encoder that you wish to read values from.
	 * @param factor  A multiplier to manipulate the encoder output.
	 */
	public EncoderPIDSource(Encoder encoder) {
		this.encoder = encoder;
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
		return this.encoder.getDistance();
	}
}
