package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Paths.PathsRed;
import org.firstinspires.ftc.teamcode.TeleCode.Intake;
import org.firstinspires.ftc.teamcode.TeleCode.Launcher;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;

@Autonomous(name = "CommandsRed")
public class CommandsRed extends NextFTCOpMode {
    // Pretty self-explanatory, mess around with this values if the robot takes too much time shooting
    // Delay is always in seconds.
    private static final double TIME_TO_SHOOT_PRELOAD = 2.5;
    private static final double TIME_TO_SHOOT_PPG = 2.5;
    private static final double TIME_TO_SHOOT_PGP = 2.5;
    private static final double TIME_TO_SHOOT_GPP = 2.5;

    private PathsRed paths;

    private Launcher launcher;
    private Intake intake;
    private SequentialGroup autoCommands;

    // Let NextFTC know about Pedro
    public CommandsRed()
    {
        addComponents(
                new PedroComponent(Constants::createFollower)
        );
    }

    private SequentialGroup autonomousRoutine()
    {
        PedroComponent.follower().setStartingPose(PathsRed.startPose);

        paths = new PathsRed(PedroComponent.follower());

        launcher = new Launcher(hardwareMap);
        intake = new Intake(hardwareMap);

        InstantCommand startLauncher = new InstantCommand(() -> {

        });

        InstantCommand startIntake = new InstantCommand(() -> {

        });

        InstantCommand stopLauncher = new InstantCommand(() -> {

        });

        InstantCommand OpenGate = new InstantCommand(() -> {

        });

        InstantCommand CloseGate = new InstantCommand(() -> {

        });

        return new SequentialGroup(
                // Score preloads
                startLauncher,
                new FollowPath(paths.startToShoot).then(
                        // Delay to let shooters reach desired velocity
                        new Delay(0.5)
                ),
                new ParallelGroup(
                        startLauncher, OpenGate,
                        new Delay(TIME_TO_SHOOT_PRELOAD)
                ),
                stopLauncher,

                // Intake and score PPG
                new FollowPath(paths.moveToPPG).then(
                        startIntake
                ),
                new FollowPath(paths.moveToIntakePPG).then(
                        startLauncher
                ),

                new FollowPath((paths.shootPPG)).then(
                        new Delay(0.5)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_PPG)
                ),
                stopLauncher,

                // Intake and score PGP
                new FollowPath(paths.moveToPGP).then(
                        startIntake
                ),
                new FollowPath(paths.moveToIntakePGP).then(
                        startLauncher
                ),
                new FollowPath((paths.shootPGP)).then(
                        new Delay(0.5)
                ),
                new ParallelGroup(
                        startLauncher,
                        new Delay(TIME_TO_SHOOT_PGP)
                ),
                stopLauncher,

                // Intake and score GPP
                new FollowPath(paths.moveToGPP).then(
                        startIntake
                ),
                new FollowPath(paths.moveToIntakeGPP).then(
                        startLauncher
                ),
                new FollowPath((paths.shootGPP)).then(
                        new Delay(0.5)
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