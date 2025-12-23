package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;
import org.firstinspires.ftc.teamcode.TeleCode.LedSubSystem;
import org.firstinspires.ftc.teamcode.TeleCode.RobotDrive;

@TeleOp
public class MainOpmode extends OpMode {
    private Intake intake;
    private Launcher launch;
    private RobotDrive drive;
    private LedSubSystem lights;
    double forward, strafe, rotate ;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);
        drive = new RobotDrive(hardwareMap);
        launch = new Launcher(hardwareMap);
    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        //Intake code
        if (gamepad2.left_bumper) {
            intake.intake(-1, 1);
        } else if (gamepad2.square) {
            intake.intake(1, -1);
        }
        else {
            intake.intake(0.0, 0.0);
        }

        //Shoot the launcher
        if (gamepad2.right_bumper) {
            launch.shoot(-1);
        }
        else {
            launch.shoot(0);
        }


        //Close Range
        if (gamepad2.b) {
            launch.setHood(0.5);
        }
        else {
            launch.setHood(0.2);
        }


        drive.drive(forward,strafe,rotate);
    }
}
