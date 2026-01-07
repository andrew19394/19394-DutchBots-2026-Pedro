package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleCode.LimeLight;
import org.firstinspires.ftc.teamcode.TeleCode.RobotDrive;

@TeleOp
public class MainOpmode extends OpMode {
    private RobotDrive drive;
    private LimeLight limelight;
    double forward, strafe, rotate ;

    @Override
    public void init() {
        drive = new RobotDrive(hardwareMap);
        limelight = new LimeLight(hardwareMap);
    }



    @Override
    public void loop() {
        //Drive code that uses the RobotDrive subclass
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.drive(forward,strafe,rotate);

        //LimeLight code that uses the LimeLight subclass
        if (gamepad1.dpad_up) {
            limelight.findBlueTag();
        }
        else if (gamepad1.dpad_down) {
            limelight.findRedTag();
        }

        if (

        }


    }
}
