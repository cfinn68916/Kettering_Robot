package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Timer;

public class TimeDrive extends CommandBase {
  Drive train;
  double t, th;
  Timer timer=new Timer();

  public TimeDrive(Drive drive, double time, double throttle) {
    t = time;
    th = throttle;
    train = drive;
    timer.start();
  }

  public void execute() {
    train.humanDrive(th, 0);
  }

  public void end() {
    train.humanDrive(0, 0);
    timer.stop();
  }
  public boolean isFinished(){
    return timer.get()>t;
  }
}
