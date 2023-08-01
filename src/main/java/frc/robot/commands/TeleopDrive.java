package frc.robot.commands;

import frc.robot.subsystems.Drive;
import java.util.function.DoubleSupplier;

public class TeleopDrive {
  Drive train;
  DoubleSupplier x;
  DoubleSupplier y;
  DoubleSupplier z;

  public TeleopDrive(Drive drive, DoubleSupplier right, DoubleSupplier rotation) {}
}
