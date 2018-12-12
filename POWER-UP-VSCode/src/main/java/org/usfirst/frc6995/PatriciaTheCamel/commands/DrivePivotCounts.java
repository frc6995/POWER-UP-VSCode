package org.usfirst.frc6995.PatriciaTheCamel.commands;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePivotCounts extends Command {
	double destEncoderDist;
	double currEncoderDist = 0;
	double startEncoderLeft;
	double startEncoderRight;
	double timeout;
	boolean directionL;
    public DrivePivotCounts(double driveCounts, double timeout) {
        //Positive #s brake right and fwd left, neg #s brake left and forward right.
    	destEncoderDist = driveCounts;
    	this.timeout = timeout;
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startEncoderLeft = Robot.drivebase.driveLeft.getSensorCollection().getQuadraturePosition();
    	startEncoderRight = Robot.drivebase.driveRight.getSensorCollection().getQuadraturePosition();
    	if (destEncoderDist > 0) {
			directionL = false;
		}
    	else if (destEncoderDist < 0) {
    		directionL = true;
    	}
    	else {
    		end();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double currEncoderLeft = Robot.drivebase.driveLeft.getSensorCollection().getQuadraturePosition();
    	final double currEncoderRight = Robot.drivebase.driveRight.getSensorCollection().getQuadraturePosition();
    	if (directionL) { //then track the right encoder and fwd the right motor
    		currEncoderDist = (currEncoderRight - startEncoderRight);
    		Robot.drivebase.driveLeft.set(0);
    		Robot.drivebase.driveRight.set(0.5);
    	}
    	else if (!directionL) { //then destEncoder is pos, so track left enc
    		currEncoderDist = (currEncoderLeft - startEncoderLeft);
    		Robot.drivebase.driveRight.set(0);
    		Robot.drivebase.driveLeft.set(0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((destEncoderDist == 0) || ((destEncoderDist-currEncoderDist >= 0) && directionL) || ((destEncoderDist - currEncoderDist <= 0) && !directionL) || (timeout <= 0));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.driveRight.set(0);
    	Robot.drivebase.driveLeft.set(0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public void setCounts(double counts) {
    	destEncoderDist = counts;
    }
    
    public void setDuration(double timeout) {
    	this.timeout = timeout;
    }
}
