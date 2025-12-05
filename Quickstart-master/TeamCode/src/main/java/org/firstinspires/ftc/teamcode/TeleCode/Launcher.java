package org.firstinspires.ftc.teamcode.TeleCode;


import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Launcher {

    private final DcMotorEx launcher;
    private final Servo hood;

    public Launcher(HardwareMap hwMap) {
        launcher = hwMap.get(DcMotorEx.class,"launcher");
        hood = hwMap.get(Servo.class, "hood");

        MotorConfigurationType configR = launcher.getMotorType().clone();
        configR.setAchieveableMaxRPMFraction(1.0);
        launcher.setMotorType(configR);
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setHood(double angle) {
        hood.setPosition(angle);
    }

    public void shoot(double speed) {
        launcher.setPower(speed);
    }
//    public void telemetry(TelemetryManager telemetry) {
//        telemetry.addData("Launcher Current", launcher.getCurrent(CurrentUnit.MILLIAMPS));
//    }
}
