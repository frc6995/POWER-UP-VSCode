/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc6995.PatriciaTheCamel.commands;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;

import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraightDistance extends PIDCommand {
  public double moveSpeed = 0;
  public double finalDistance;
  public double initHeading;
  public PIDController distController = getPIDController();
  public DriveStraightDistance(double distance) {
    super(0.5, 0.0, 0.0);
    requires(Robot.drivebase);
    finalDistance = distance;
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivebase.resetEncoder();
    initHeading = Robot.navigation.getYaw();
    distController.setSetpoint(Robot.drivebase.DistanceToEncoder(finalDistance));
    distController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.drivebase.holdAtAngle(moveSpeed, initHeading);
    Robot.drivebase.arcadeDrive(moveSpeed, 0, 0.5);
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return distController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivebase.arcadeDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.drivebase.driveLeft.getSensorCollection().getQuadraturePosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    moveSpeed = output;
  }
}
