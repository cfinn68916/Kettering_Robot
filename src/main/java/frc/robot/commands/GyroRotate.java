package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class GyroRotate extends CommandBase {
    Drive drive;
    double dest;
    public GyroRotate(Drive ds, double add){
        drive = ds;
        dest=drive.getGyroYaw()+add;
    }

    public void execute(){
        drive.humanDrive(0, Math.abs(dest-drive.getGyroYaw())<5?1:-1);
    }

    public boolean isFinished(){
        return Math.abs(dest-drive.getGyroYaw())<5;
    }

}
