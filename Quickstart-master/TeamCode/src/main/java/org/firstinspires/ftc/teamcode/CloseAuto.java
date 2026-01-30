package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;


@Autonomous
public class CloseAuto extends OpMode {

    private Follower follower;
    private Launcher launcher;
    private Intake intake;

    private ElapsedTime pathTimer = new ElapsedTime();
    private ElapsedTime opModeTimer = new ElapsedTime();

    private enum PathState {
        //Start position to the end position
        Drive_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD,

        DRIVE_SHOOTPOS_INTAKE1POS
    }

    PathState pathState;

    private final Pose startPose = new Pose(21.511111111111113, 121.42222222222222, Math.toRadians(138));
    private final Pose shootPose = new Pose(51.37777777777778, 91.91111111111111, Math.toRadians(138));
    private final Pose intakeLine1Pose = new Pose(40.888888888888886, 83.73333333333333, Math.toRadians(180));

    private PathChain driveStartPosShootPos, driveShootToIntake1Pos;

    public void buildPaths() {
        //put in coordinates for starting pose the ending pose
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootToIntake1Pos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, intakeLine1Pose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), intakeLine1Pose.getHeading())
                .build();
    }


    public void statePathUpdate() {
        switch (pathState) {
            case Drive_STARTPOS_SHOOT_POS:
                follower.followPath(driveStartPosShootPos, true);
                launcher.setHood(0.4);
                launcher.shoot(0.65, -0.65);
                intake.shoot(0.9);
                setPathState(PathState.SHOOT_PRELOAD);
                break;
            case SHOOT_PRELOAD:
                // Check is follower done it's path?
                // 5 seconds have elapsed

                pathTimer.reset();

                if (!follower.isBusy()) {
                intake.feed(-0.63);
                }

                if(pathTimer.seconds() >= 0.01)
                {
                    setPathState(PathState.DRIVE_SHOOTPOS_INTAKE1POS);
                }

                break;
            case DRIVE_SHOOTPOS_INTAKE1POS:
                if (!follower.isBusy()) {
                    follower.followPath(driveShootToIntake1Pos, true);
                    telemetry.addLine("Done all Paths");
                }
            default:
                telemetry.addLine("No State Commanded");
        }
    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.reset();
    }


    @Override
    public void init() {
        pathState = PathState.Drive_STARTPOS_SHOOT_POS;
        pathTimer = new ElapsedTime();
        opModeTimer = new ElapsedTime();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);

        launcher = new Launcher(hardwareMap);
        intake = new Intake(hardwareMap);
        // todo add in any other init mechanisms below example flywheel, limelight

        buildPaths();
    }

    @Override
    public void start() {
        opModeTimer.reset();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();

        telemetry.addData("Path State: ", pathState.toString());
        telemetry.addData("x ", follower.getPose().getX());
        telemetry.addData("y ", follower.getPose().getY());
        telemetry.addData("Heading ", follower.getPose().getHeading());
        telemetry.addData("Path Time ", pathTimer.seconds());
        telemetry.addData("pathTimer", pathTimer.seconds());
    }
}
