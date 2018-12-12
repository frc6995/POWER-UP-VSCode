package org.usfirst.frc6995.PatriciaTheCamel.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public double motorSpeedOut = 0.75;
	public double motorSpeedIn = 0.5;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

