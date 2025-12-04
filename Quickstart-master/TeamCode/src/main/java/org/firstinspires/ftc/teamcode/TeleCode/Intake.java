package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor front_intake;

    public Intake(HardwareMap hwMap) {
        front_intake = hwMap.get(DcMotor.class, "front_intake");
//        back_intake = hwMap.get(DcMotor.class, "back_intake");

//        back_intake.setDirection(DcMotorSimple.Direction.REVERSE);

        front_intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        back_intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double Fpower) {
        front_intake.setPower(Fpower);
//        back_intake.setPower(Bpower);
    }
}
