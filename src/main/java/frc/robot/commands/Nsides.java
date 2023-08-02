package frc.robot.commands;

import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.*;

public class Nsides extends SequentialCommandGroup{
    int sides;
    public Nsides(Drive drive, int sides){
        for (int i = 0; i < sides; i++) {
            addCommands(
                new TimeDrive(drive,0.5,0.5,0),
                new GyroRotate(drive, 360/sides)
            );
        }
    }
}
