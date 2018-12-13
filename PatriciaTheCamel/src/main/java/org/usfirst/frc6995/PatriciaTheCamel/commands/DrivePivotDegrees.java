/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc6995.PatriciaTheCamel.commands;

import org.usfirst.frc6995.PatriciaTheCamel.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Add your docs here.
 */
public class DrivePivotDegrees extends TimedCommand {
  /**
   * Add your docs here.
   */

  public double finalAngle;
  public double turnAngle;
  public DrivePivotDegrees(double timeout, double angle) {
    super(timeout);
    turnAngle = angle;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivebase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finalAngle = Robot.navigation.getYaw() + turnAngle;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivebase.holdAtAngle(0, finalAngle);
  }

  // Called once after timeout
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
