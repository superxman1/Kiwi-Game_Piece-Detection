// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  TalonSRX motor;
  PhotonCamera camera;
  public ExampleSubsystem() {
    motor = new TalonSRX(5);
    camera = new PhotonCamera("HD_Webcam_C525");
    
    //List<PhotonTrackedTarget> target = result.getTargets();
   
  }
  public void moveTurret(){
    var result = camera.getLatestResult();
    boolean hasTargets = result.hasTargets();
    if(hasTargets == true){
      PhotonTrackedTarget target = result.getBestTarget();
      double yaw = target.getYaw();
      if(yaw > 10){
      motor.set(ControlMode.PercentOutput, -.1);
      }
      else if(yaw < -10){
        motor.set(ControlMode.PercentOutput, .1);
      } else {
        motor.set(ControlMode.PercentOutput, 0);
      }
    }
  }

  public void togglePipeline(){
    camera.setPipelineIndex(camera.getPipelineIndex() == 0 ? 1 : 0);
  }

  @Override
  public void periodic() {
   moveTurret();
    

    
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
