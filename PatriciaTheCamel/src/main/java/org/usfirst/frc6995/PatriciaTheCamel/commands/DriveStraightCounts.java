package org.usfirst.frc6995.PatriciaTheCamel.commands;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;
import org.usfirst.frc6995.PatriciaTheCamel.subsystems.Drivebase;

import com.ctre.phoenix.schedulers.ConcurrentScheduler;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCounts extends Command {
	double destEncoderDist;
	double currEncoderAvg = 0;
	double startEncoderLeft;
	double startEncoderRight;
	double timeout;
	boolean directionF;
    public DriveStraightCounts(double driveCounts, double timeoutSecs) { //distance in encoder counts
    	requires(Robot.drivebase);
    	destEncoderDist = driveCounts;
    	timeout = timeoutSecs;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startEncoderLeft = Robot.drivebase.driveLeft.getSensorCollection().getQuadraturePosition();
    	startEncoderRight = Robot.drivebase.driveRight.getSensorCollection().getQuadraturePosition();
    	if (destEncoderDist > 0) {
			directionF = false;
		}
    	else if (destEncoderDist < 0) {
    		directionF = true;
    	}
    	else {
    		end();
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double currEncoderLeft = Robot.drivebase.driveLeft.getSensorCollection().getQuadraturePosition();
    	final double currEncoderRight = Robot.drivebase.driveRight.getSensorCollection().getQuadraturePosition();
    	currEncoderAvg = ((currEncoderLeft - startEncoderLeft) + (currEncoderRight - startEncoderRight)) / 2;
    	if (directionF) { //then go forward
    		Robot.drivebase.driveLeft.set(0.5);
    		Robot.drivebase.driveRight.set(0.5);
    	}
    	else if (!directionF) { //then go backward
    		Robot.drivebase.driveRight.set(-0.5);
    		Robot.drivebase.driveLeft.set(-0.5);
    	}
    	timeout -= 0.02;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((destEncoderDist == 0) || ((destEncoderDist-currEncoderAvg >= 0) && !directionF) || ((destEncoderDist - currEncoderAvg <= 0) && directionF) || (timeout <= 0.0));
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.driveLeft.set(0);
    	Robot.drivebase.driveRight.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public void setDistance(double distance) {
    	this.destEncoderDist = distance;
    }
    
    public void setDuration(double timeout) {
    	this.timeout = timeout;
    }
    
    public double DistanceToEncoder(double distance) { //6pi inches per 4096 counts.
    	double counts = distance * (4096/(6*Math.PI));
    	return counts;
    }
}
