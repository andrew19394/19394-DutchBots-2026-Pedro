package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor front_intake;
    private CRServo feed;
    private CRServo fire;

    public Intake(HardwareMap hwMap) {
        front_intake = hwMap.get(DcMotor.class, "front_intake");
        feed = hwMap.get(CRServo.class, "feed_servo");
        fire = hwMap.get(CRServo.class, "feed_servo");

        front_intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double Ipower, double Spower, double Fpower) {
        front_intake.setPower(Ipower);
        feed.setPower(Spower);
        fire.setPower(Fpower);
    }
}
