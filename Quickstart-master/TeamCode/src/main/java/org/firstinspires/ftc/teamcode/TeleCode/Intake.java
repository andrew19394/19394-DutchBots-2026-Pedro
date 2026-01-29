package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private final DcMotor front_intake;
    private final Servo fire;

    public Intake(HardwareMap hwMap) {
        front_intake = hwMap.get(DcMotor.class, "front_intake");
        fire = hwMap.get(Servo.class, "fire_servo");
        front_intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double intakepower, double angle) {
        front_intake.setPower(intakepower);
        fire.setPosition(angle);
    }
    public void shoot(double angle) {
        fire.setPosition(angle);
    }

    public void feed(double speed) {
        front_intake.setPower(speed);
    }
}
