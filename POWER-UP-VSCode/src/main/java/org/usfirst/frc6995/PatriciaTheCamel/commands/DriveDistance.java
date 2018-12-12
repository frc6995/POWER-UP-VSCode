package org.usfirst.frc6995.PatriciaTheCamel.commands;


import org.usfirst.frc6995.PatriciaTheCamel.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	double distance;
	double direction;

    public DriveDistance(double driveDist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	distance = driveDist;
    	direction = Math.signum(distance); //-1 for backward, 1 for forward
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.resetEncoder();
    	System.out.println("DriveDistance init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivebase.arcadeDrive(.5, 0, 1);
    	Robot.drivebase.arcadeDrive((direction * 0.5), 0, 0);
    	System.out.println("DriveDistance driving");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.drivebase.CountsToDistance(Math.abs(Robot.drivebase.getEncoderCount())) >= Math.abs(distance));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.arcadeDrive(0, 0, 0);
    	System.out.println("DriveDistance end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
