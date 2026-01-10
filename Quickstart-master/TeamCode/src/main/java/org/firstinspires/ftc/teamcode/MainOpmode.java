package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;
import org.firstinspires.ftc.teamcode.TeleCode.RobotDrive;

@TeleOp
public class MainOpmode extends OpMode {
    private RobotDrive drive;
    private Intake intake;
    private Launcher launcher;
    double forward, strafe, rotate ;

    @Override
    public void init() {
        drive = new RobotDrive(hardwareMap);
        intake = new Intake(hardwareMap);
    }



    @Override
    public void loop() {
        //Drive code that uses the RobotDrive subclass
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.drive(forward,strafe,rotate);

        //Intake and feeder code
        if (gamepad2.a) {
            intake.intake(1, 1,-1);
        }
        else if (gamepad2.b) {
            intake.intake(1,1,1);
        }
        else {
            intake.intake(0, 0, 0);
        }

        //Launcher Code
        if (gamepad2.x) {
            launcher.shoot(1,-1);
        }
        else {
            launcher.shoot(0,0);
        }

        //Hood code
        if (gamepad1.dpad_up) {
            launcher.setHood(.5);
        }
        else {
            launcher.setHood(0);
        }






    }
}
