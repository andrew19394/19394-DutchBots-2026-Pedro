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
        // intilzes the triggers and because they are floats
        double triggerValue = gamepad2.right_trigger;
        double triggerValue2 = gamepad2.left_trigger;

        // Drive code that uses the RobotDrive subclass
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.drive(forward,strafe,rotate);

        // Intake and feeder code
        // -1 for the fire servo is the blocker
        // 0.9 on the fire servo is all the way in
        if (gamepad2.left_bumper) {
            intake.intake(-1,-1);
        }
        else if (gamepad2.dpad_up) {
            intake.intake(-0.7, 0.9);
        }
        else if (gamepad2.left_trigger > 0.9) {
            intake.intake(0.5,0.9);
        }
        else {
            intake.intake(0, -0.8);
        }

        // Launcher speed code
        if (gamepad2.right_bumper) {
            launcher.shoot(1,-1);
        }
        else if (gamepad2.x) {
            launcher.shoot(0.65, -0.65);
        }
        else {
            launcher.shoot(0,0);
        }

        // Hood angle code
        if (gamepad2.a) {
            launcher.setHood(.55);
        }
        else {
            launcher.setHood(0.4);
        }

        // Auto-Aim Logic
        // Use Gamepad 2 "Right Trigger" to auto-aim
        if (gamepad2.right_trigger > 0.9 && limeLight.hasTarget()) {
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

        // Switches the pipline of the limelight so we can aim on both sides
        if (gamepad1.x) {
            limeLight.switchRed();
        }
        else if (gamepad1.circle) {
            limeLight.switchBlue();
        }

        // Update Telemetry
        limeLight.limetelemetry(telemetry);
        telemetry.update();

    }
}
