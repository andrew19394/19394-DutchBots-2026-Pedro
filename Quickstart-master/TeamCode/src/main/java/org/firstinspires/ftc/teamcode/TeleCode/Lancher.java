package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Lancher {

    private DcMotor lancher;
    private Servo hood;

    public Lancher(HardwareMap hwMap) {
        lancher = hwMap.get(DcMotor.class,"lancher");
        hood = hwMap.get(Servo.class, "hood");

        lancher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setHood(double angle) {
        hood.setPosition(angle);
    }

    public void shoot(double speed) {
        lancher.setPower(speed);
    }
}
