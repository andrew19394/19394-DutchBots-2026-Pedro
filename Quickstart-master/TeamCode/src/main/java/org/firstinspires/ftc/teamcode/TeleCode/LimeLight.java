package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class LimeLight {

    private Limelight3A limelight;
    private IMU imu;

    public LimeLight(HardwareMap hwMap) {
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);

        imu = hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        limelight.start(); // Ensure the limelight starts capturing
    }

    // Switches the pipline so the auto aim works on red
    public void switchRed() {
        limelight.pipelineSwitch(1);
    }

    // Switches the pipline so the auto aim works on blue
    public void switchBlue() {
        limelight.pipelineSwitch(0);
    }

     // Checks if the Limelight currently sees a valid target.

    public boolean hasTarget() {
        LLResult result = limelight.getLatestResult();
        return (result != null && result.isValid());
    }


     //Gets the horizontal offset (tx) from the target.

    public double getTx() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            return result.getTx();
        }
        return 0;
    }

    // Pass the telemetry object from your MainOpmode into here
    public void limetelemetry(Telemetry telemetry) {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            telemetry.addData("Limelight Target", "LOCKED");
            telemetry.addData("TX", llResult.getTx());
            telemetry.addData("TY", llResult.getTy());
        } else {
            telemetry.addData("Limelight Target", "LOST");
        }
    }
}