package org.robockets.robot.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.robot.Robot;

/**
 * @author jake
 */
public class Climb extends Command {

    private double speed;

    public Climb(double speed) {
        this.speed = speed;
    }

    protected void initialize() {
    }

    protected void execute() {
        if (Robot.climber.isStalling()) {
            Robot.climber.setSpeed(speed);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.climber.stop();
    }

    protected void interrupted() {
        end();
    }
}
