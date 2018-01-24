package org.robockets.robot.gearintake;

import org.robockets.commons.RelativeDirection;
import org.robockets.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class  GearIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveIntakeArm(RelativeDirection.XAxis direction,double speed){
    	if (direction == RelativeDirection.XAxis.LEFT){
    		RobotMap.leftIntake.set(speed);
    	} else {
    		RobotMap.rightIntake.set(speed);
    	}
    }
    
    public void moveBar(double speed){
    	RobotMap.barIntake.set(speed);
    }
    
}

