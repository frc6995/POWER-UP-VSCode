package org.usfirst.frc6995.PatriciaTheCamel.commands;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BasicAuto extends CommandGroup {
    public BasicAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//added variable as input so we can contol distance through smart dashboard
        //addSequential(new DriveDistance(Robot.autoDistance));
        addSequential(new DrivePivotDegrees(5000, 90));
    	System.out.println("drive Command added to group" );
    }
}
