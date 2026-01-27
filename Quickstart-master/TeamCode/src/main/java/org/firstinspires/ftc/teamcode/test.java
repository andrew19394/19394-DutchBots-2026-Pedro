package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp
public class test extends OpMode {
    private DcMotorEx frontLeftMotor;
    private DcMotorEx backLeftMotor;
    private DcMotorEx frontRightMotor;
    private DcMotorEx backRightMotor;

    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "front_left_motor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "back_left_motor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "front_right_motor");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "back_right_motor");
    }
    public void loop() {
        if (gamepad1.dpad_up) {
            frontLeftMotor.setPower(1);
        } else if (gamepad1.dpad_right) {
            backLeftMotor.setPower(1);
        } else if (gamepad1.dpad_down) {
            frontRightMotor.setPower(1);
        } else if (gamepad1.dpad_left) {
            backRightMotor.setPower(1);
        }else {
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
        }
    }
}
