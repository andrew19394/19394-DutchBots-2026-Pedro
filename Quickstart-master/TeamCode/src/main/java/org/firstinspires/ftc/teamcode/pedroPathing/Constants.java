package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(14.4)
            .forwardZeroPowerAcceleration(-40.29580761517009)
            .lateralZeroPowerAcceleration(-66.92253413170417)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.09, 0.00007, 0.009, 0.01))
            .headingPIDFCoefficients(new PIDFCoefficients(0.49, 0, 0.0, 0.032))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.03, 0, 0.001, 0.6, 0.001))
            .centripetalScaling(0.00006)
            ;
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(.67)
            .xVelocity(51.16777939683809)
            .yVelocity(35.769556721364424)
            .rightFrontMotorName("front_right_motor")
            .rightRearMotorName("back_right_motor")
            .leftRearMotorName("back_left_motor")
            .leftFrontMotorName("front_left_motor")
            .leftFrontMotorDirection(DcMotorEx.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorEx.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorEx.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorEx.Direction.FORWARD);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(1)
            .strafePodX(-5.5)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);
    public static PathConstraints pathConstraints = new PathConstraints(0.99,
            100,
            1,
            1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .pinpointLocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
