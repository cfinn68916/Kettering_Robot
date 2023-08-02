package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Timer;

public class TimeDrive extends CommandBase {
  Drive train;
  double t, th, rotation;
  Timer timer=new Timer();

  public TimeDrive(Drive drive, double time, double throttle, double rotation) {
    t = time;
    th = throttle;
    train = drive;
    this.rotation=rotation;
    timer.start();
  }

  public void execute() {
    train.humanDrive(th, rotation);
  }

  public void end() {
    train.humanDrive(0, 0);
    timer.stop();
  }
  public boolean isFinished(){
    return timer.get()>t;
  }
}
