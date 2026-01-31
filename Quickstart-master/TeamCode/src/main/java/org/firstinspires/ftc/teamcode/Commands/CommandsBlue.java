package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Paths.PathsBlue;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;

import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;

@Autonomous(name = "CommandsBlue")
public class CommandsBlue extends NextFTCOpMode {
    // Pretty self-explanatory, mess around with this values if the robot takes too much time shooting
    // Delay is always in seconds.
    private static final double TIME_TO_SHOOT_PRELOAD = 2;
    private static final double TIME_TO_SHOOT_PPG = 2;
    private static final double TIME_TO_SHOOT_PGP = 2;
    private static final double TIME_TO_SHOOT_GPP = 2;

    private PathsBlue paths;

    private Launcher launcher;
    private Intake intake;
    private SequentialGroup autoCommands;

    // Let NextFTC know about Pedro
    public CommandsBlue()
    {
        addComponents(
                new PedroComponent(Constants::createFollower)
        );
    }

    private SequentialGroup autonomousRoutine()
    {
        PedroComponent.follower().setStartingPose(PathsBlue.startPose);

        paths = new PathsBlue(PedroComponent.follower());

        launcher = new Launcher(hardwareMap);
        intake = new Intake(hardwareMap);

        InstantCommand startLauncher = new InstantCommand(() -> {
            launcher.shoot(0.53, -0.53);
        });

        InstantCommand startIntake = new InstantCommand(() -> {
            intake.feed(-1);
        });

        InstantCommand feedToLaunch = new InstantCommand(() -> {
            intake.feed(-0.54);
        });

        InstantCommand stopLauncher = new InstantCommand(() -> {
            launcher.shoot(0, 0);
        });

        InstantCommand OpenGate = new InstantCommand(() -> {
            intake.shoot(0.9);
        });

        InstantCommand CloseGate = new InstantCommand(() -> {
            intake.shoot(-1);
        });

        InstantCommand hood = new InstantCommand(() -> {
            launcher.setHood(0.4);
        });

        return new SequentialGroup(
                // Score preloads
                startLauncher, OpenGate, hood,
                new FollowPath(paths.startToShoot, true, 1.0).then(
                        feedToLaunch,
                        new Delay(0.05)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_PRELOAD)
                ),
                stopLauncher, CloseGate,

                // Intake and score PPG
                startIntake,
                new FollowPath(paths.moveToPPG, true, 1.0).then(
                        CloseGate
                ),
                new FollowPath(paths.moveToIntakePPG, true, 0.45).then(
                        startLauncher
                ),

                new FollowPath((paths.shootPPG)).then(
                        OpenGate, feedToLaunch,
                        new Delay(0.05)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_PPG)
                ),
                stopLauncher, CloseGate,

                // Intake and score PGP
                startIntake,
                new FollowPath(paths.moveToPGP, true, 1.0).then(
                        CloseGate
                ),
                new FollowPath(paths.moveToIntakePGP, true, 0.45).then(
                        startLauncher
                ),
                new FollowPath((paths.shootPGP)).then(
                        OpenGate, feedToLaunch,
                        new Delay(0.05)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_PGP)
                ),
                stopLauncher, CloseGate,

                // Intake and score GPP
                startIntake,
                new FollowPath(paths.moveToGPP, true, 1.0).then(
                        CloseGate
                ),
                new FollowPath(paths.moveToIntakeGPP, true, 0.45).then(
                        startLauncher
                ),
                new FollowPath((paths.shootGPP)).then(
                        OpenGate, feedToLaunch,
                        new Delay(0.05)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_GPP)
                ),
                stopLauncher,

                // Park
                new FollowPath(paths.moveToPGP)
        );
    }

    @Override
    public void onStartButtonPressed()
    {
        autoCommands = autonomousRoutine();
        autoCommands.schedule();
    }

    @Override
    public void onUpdate()
    {
        CommandManager.INSTANCE.run();
    }
}