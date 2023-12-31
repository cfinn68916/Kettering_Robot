package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  PWMVictorSPX frontLeft = new PWMVictorSPX(Constants.IO.FRONT_LEFT_ID);
  PWMVictorSPX rearLeft = new PWMVictorSPX(Constants.IO.BACK_LEFT_ID);
  PWMVictorSPX frontRight = new PWMVictorSPX(Constants.IO.FRONT_RIGHT_ID);
  PWMVictorSPX rearRight = new PWMVictorSPX(Constants.IO.BACK_RIGHT_ID);

  MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);
  MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);
  private final Joystick m_Joystick = new Joystick(0);

  public void stop(){
    frontLeft.stopMotor();
    frontRight.stopMotor();
    rearLeft.stopMotor();
    rearRight.stopMotor();
    
  }


  public void arcadeDrive(double forwardSpeed, double turnSpeed) {
    double left = forwardSpeed + turnSpeed;
    double right = forwardSpeed - turnSpeed;

    rearRight.set(right);
    frontRight.set(right);
    rearLeft.set(left);
    frontLeft.set(left);
  }

  public void setVolts(double leftV, double rightV) {
    left.setVoltage(leftV);
    right.setVoltage(rightV);
  }

  DifferentialDrive drive = new DifferentialDrive(left, right);
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  public Drive() {
    right.setInverted(true);
  }

  public void periodic() {
    arcadeDrive(m_Joystick.getRawAxis(4), -m_Joystick.getRawAxis(1));
  }

  public Rotation2d getGyroYaw() {
    return gyro.getRotation2d();
  }

  public void humanDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot, false);
  }
}
