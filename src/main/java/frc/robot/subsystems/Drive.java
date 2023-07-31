package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  CANSparkMax frontLeft = new CANSparkMax(Constants.IO.FRONT_LEFT_ID, MotorType.kBrushless);
  CANSparkMax rearLeft = new CANSparkMax(Constants.IO.BACK_LEFT_ID, MotorType.kBrushless);
  CANSparkMax frontRight = new CANSparkMax(Constants.IO.FRONT_RIGHT_ID, MotorType.kBrushless);
  CANSparkMax rearRight = new CANSparkMax(Constants.IO.BACK_RIGHT_ID, MotorType.kBrushless);

  RelativeEncoder leftEncoder = frontLeft.getEncoder();
  RelativeEncoder rightEncoder = frontRight.getEncoder();

  MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);
  MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);

  DifferentialDrive drive = new DifferentialDrive(left, right);
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  DifferentialDriveOdometry odometry;

  public Drive() {
    right.setInverted(true);
    odometry = new DifferentialDriveOdometry(getGyroYaw(), getLeftEncoder(), getRightEncoder());
  }

  public void periodic() {
    odometry.update(getGyroYaw(), getLeftEncoder(), getRightEncoder());
  }

  public Rotation2d getGyroYaw() {
    return gyro.getRotation2d();
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void humanDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot, false);
  }

  public double getLeftEncoder() {
    return leftEncoder.getPosition() * Constants.Drivetrain.EFFECTIVE_WHEEL_DIAMETER;
  }

  public double getRightEncoder() {
    return rightEncoder.getPosition() * Constants.Drivetrain.EFFECTIVE_WHEEL_DIAMETER;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoder(), getRightEncoder());
  }

  public void setVolts(double leftV, double rightV){
    left.setVoltage(leftV);
    right.setVoltage(rightV); 
  }

}
