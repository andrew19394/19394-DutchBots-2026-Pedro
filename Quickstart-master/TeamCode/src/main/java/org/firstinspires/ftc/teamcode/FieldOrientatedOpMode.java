package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleCode.IndexerServos;
import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;
import org.firstinspires.ftc.teamcode.TeleCode.LedSubSystem;
import org.firstinspires.ftc.teamcode.TeleCode.RobotDrive;


@TeleOp
public class FieldOrientatedOpMode extends OpMode {
    private IndexerServos flipper;
    private Intake intake;
    private Launcher launch;
    private RobotDrive drive;
    private LedSubSystem lights;
    double forward, strafe, rotate ;

    @Override
    public void init() {
        flipper = new IndexerServos(hardwareMap);
        intake = new Intake(hardwareMap);
        drive = new RobotDrive(hardwareMap);
        launch = new Launcher(hardwareMap);
    }

    @Override
    public void loop() {
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

            //Flipper 1 code
            if (gamepad2.dpad_up) {
                flipper.setFlipper_1(0.53);
            }
            else {
                flipper.setFlipper_1(0.6);
            }

            //Flipper 2 code
            if (gamepad2.dpad_left) {
                flipper.setFlipper_2(0.4);
            }
            else {
                flipper.setFlipper_2(0.08);
            }

            //Flipper 3 code
            if (gamepad2.dpad_right) {
                flipper.setFlipper_3(0.35);
            }
            else {
                flipper.setFlipper_3(0.0);
            }


        //Intake front intake
        if (gamepad2.a) {
            intake.intake(-1);
        }
        else {
            intake.intake(0.0);
        }

        //Lights of Led green is ready, red is not ready


//        //Shoot the launcher
//        if (gamepad2.x) {
//            lanch.shoot(1);
//        }
//        else {
//            lanch.shoot(0);
//        }
//
//        //Close Range
//        if (gamepad2.b) {
//            lanch.setHood(1);
//        }
//        else {
//            lanch.setHood(0);
//        }

        drive.driveFieldRelative(forward,strafe,rotate);
    }
}
