package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;
import org.firstinspires.ftc.teamcode.TeleCode.LimeLight;
import org.firstinspires.ftc.teamcode.TeleCode.RobotDrive;

@TeleOp
public class MainOpmode extends OpMode {
    private RobotDrive drive;
    private Intake intake;
    private Launcher launcher;
    private LimeLight limeLight;
    double forward, strafe, rotate ;

    @Override
    public void init() {
        drive = new RobotDrive(hardwareMap);
        intake = new Intake(hardwareMap);
        launcher = new Launcher(hardwareMap);
        limeLight = new LimeLight(hardwareMap);
    }



    @Override
    public void loop() {
        //Drive code that uses the RobotDrive subclass
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.drive(forward,strafe,rotate);

        //Intake and feeder code
        if (gamepad2.left_bumper) {
            intake.intake(1, -1,-1, 1);
        }
        else if (gamepad2.dpad_up) {
            intake.intake(1,-1,1, -1);
        }
        else {
            intake.intake(0, 0, 0, 0);
        }

        //Launcher Code
        if (gamepad2.right_bumper) {
            launcher.shoot(0.5,-0.5);
        }
        else {
            launcher.shoot(0,0);
        }

        //Hood code
        if (gamepad2.a) {
            launcher.setHood(.55);
        }
        else {
            launcher.setHood(0.4);
        }

        // Auto-Aim Logic
        // Use Gamepad 2 'Y' to auto-aim
        if (gamepad2.y && limeLight.hasTarget()) {
            launcher.autoTurret(limeLight.getTx());
        }
        // D-Pad Left/Right for manual override
        else if (gamepad2.dpad_left) {
            launcher.moveturret(0.3);
        }
        else if (gamepad2.dpad_right) {
            launcher.moveturret(-0.3);
        }
        else {
            launcher.moveturret(0);
        }

        // Update Telemetry
        limeLight.limetelemetry(telemetry);
        telemetry.update();

    }
}
