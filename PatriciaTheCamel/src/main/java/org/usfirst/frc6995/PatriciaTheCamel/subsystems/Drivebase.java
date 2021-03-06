// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc6995.PatriciaTheCamel.subsystems;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;
import org.usfirst.frc6995.PatriciaTheCamel.RobotMap;
import org.usfirst.frc6995.PatriciaTheCamel.commands.*;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivebase extends PIDSubsystem {

    public final WPI_TalonSRX driveLeft = RobotMap.drivebaseDriveLeft;
    public final WPI_TalonSRX driveRight = RobotMap.drivebaseDriveRight;
    public double rotateRate = 0.01;
    public Drivebase() {
        super("DriveController", 0.5, 0.0, 0.0);
        setInputRange(-180.0, 180.0);
        setPercentTolerance(1.4);
    }
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveCom());


        // Set the default command for a subsystem here.Pat
    }
    
    public void arcadeDrive(double moveSpeed, double rotSpeed, double throt) {
    	
    	
    	//multiplying the given speeds from the controller by the throttle
    	double move = moveSpeed*throt;
    	double rot = rotSpeed*throt;
    	
    	//setting the motor speeds
    	driveLeft.set(-move + rot);
    	driveRight.set(-move - rot);
    }

    public void holdAtAngle(double moveSpeed, double angle) {
        setSetpoint(angle);
        arcadeDrive(moveSpeed, rotateRate, 0.2);
    }
    
    public double DeadZoneCvt(double RawAxisVal, double deadzone) {
		double ConvertedAxisValue;
		
		if (RawAxisVal > deadzone) {
			ConvertedAxisValue = RawAxisVal/0.95 -deadzone;
		}
		
		else if (RawAxisVal < -deadzone) {
			ConvertedAxisValue = RawAxisVal/0.95 +deadzone;
		}
		else {
			ConvertedAxisValue=0;
		}
		return ConvertedAxisValue;

	}
    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
    	
    public double getEncoderCount() {
    	return driveLeft.getSensorCollection().getQuadraturePosition();
    }
    
    public void resetEncoder() {
    	driveLeft.getSensorCollection().setQuadraturePosition(0, 500);
    }
    
    public double DistanceToEncoder(double distance) { //6pi inches per 4096 counts.
    	double counts = distance * (4096/(6*Math.PI));
    	return counts;
    }
    
    public double CountsToDistance(double counts) {
    	double distance = counts /(4096/(6*Math.PI));
    	return distance;
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    protected double returnPIDInput() {
        return -Robot.navigation.getYaw();
    }

    @Override
    protected void usePIDOutput(double output) {
        rotateRate = -output;
        SmartDashboard.putNumber("DrivePivot rotate rate", output);
    }

}

