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
    private final DcMotorEx launcher2;
    private final DcMotorEx turret;
    private final Servo hood;

    public Launcher(HardwareMap hwMap) {
        launcher = hwMap.get(DcMotorEx.class,"launcher");
        launcher2 = hwMap.get(DcMotorEx.class, "launcher2");
        turret = hwMap.get(DcMotorEx.class, "turret");
        hood = hwMap.get(Servo.class, "hood");

        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        MotorConfigurationType configR = launcher.getMotorType().clone();
        configR.setAchieveableMaxRPMFraction(1.0);
        launcher.setMotorType(configR);
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        MotorConfigurationType configT = launcher2.getMotorType().clone();
        configT.setAchieveableMaxRPMFraction(1.0);
        launcher2.setMotorType(configR);
        launcher2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setHood(double angle) {
        hood.setPosition(angle);
    }

    public void shoot(double speed, double thing) {
        launcher.setPower(speed);
        launcher2.setPower(thing);
    }

    public void moveturret(double Tpower) {
        turret.setPower(Tpower);
    }

    private final double Kp = -0.015;
    private final double min_command = -0.02;

    public void autoTurret(double tx) {
        double turretPower = 0;

        // Only move if we are more than 2 degree off-center (Deadband)
        if (Math.abs(tx) > 1.5) {
            // Calculate proportional power
            turretPower = tx * Kp;

            // Add/Subtract minimum power to overcome mechanical friction
            if (tx > 0) {
                turretPower += min_command;
            } else {
                turretPower -= min_command;
            }
        }

        // Clamp the power between -1 and 1 just in case
        if (turretPower > 1.0) turretPower = 1.0;
        if (turretPower < -1.0) turretPower = -1.0;

        moveturret(turretPower);
    }
}
