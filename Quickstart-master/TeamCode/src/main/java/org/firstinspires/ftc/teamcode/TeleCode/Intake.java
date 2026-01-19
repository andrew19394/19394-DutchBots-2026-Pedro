package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private final DcMotor front_intake;
    private final CRServo feed;
    private final CRServo fire;
    private final CRServo ramp;

    public Intake(HardwareMap hwMap) {
        front_intake = hwMap.get(DcMotor.class, "front_intake");
        feed = hwMap.get(CRServo.class, "feed_servo");
        fire = hwMap.get(CRServo.class, "fire_servo");
        ramp = hwMap.get(CRServo.class, "ramp_servo");

        front_intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double intakepower, double feedpower, double Firepower, double Ramppower) {
        front_intake.setPower(intakepower);
        feed.setPower(feedpower);
        fire.setPower(Firepower);
        ramp.setPower(Ramppower);
    }
}
