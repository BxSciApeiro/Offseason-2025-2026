package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Drawing;
import org.firstinspires.ftc.teamcode.subsystems.vision;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;


/**
 * Here is a sample teleOp, following the NextFTC format.
 */
@TeleOp(name = "vision testing TeleOp")
public class visionTestingTeleOp extends NextFTCOpMode {
    /**
     * Here is where you would add components to the op mode. I've added the
     * pedro,
     * bindings (necessary for using NextBindings),
     * bulk read (decreases loop times), and
     * command manager (which stores and organizes all the commands scheduled)
     * In your real opModes, you must include ALL the subsystems you create.
     */
    public visionTestingTeleOp() {
        addComponents(
                new PedroComponent(Constants::createFollower),
                new SubsystemComponent(vision.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                CommandManager.INSTANCE
        );
    }
    @Override
    public void onInit() {
        // Runs ONCE when INIT is pressed
        telemetry.addLine("Init was Pressed!");
        telemetry.update();
    }
    @Override
    public void onWaitForStart() {
        // Loops until start is pressed, after init is pressed
        telemetry.addLine("Init was Pressed!, but start wasnt!");
        telemetry.addData("This outputs the gamepad value of the left stick x, constantly updating, ", gamepad1.left_stick_x);
        telemetry.update();
    }
    @Override
    public void onStartButtonPressed() {
        // Runs ONCE when START is pressed
        // Put your bindings here!
        telemetry.addLine("Start was pressed!, This will most likely disappear when the loop telemetry update removes it!");
        telemetry.update();
    }
    @Override
    public void onUpdate() {
        // Loops after start until stop

        // Here are three important things to put in your update:
        // follower().update(), which requires the import static dev.nextftc.extensions.pedro.PedroComponent.follower; and updates your localization constantly!
        // Drawing.drawDebug(follower()), which draws the robot and paths to Panels.
        // BindingManager.update(), which constantly checks for bindings and allows your previously made binds to work!

        follower().update();
        Drawing.drawDebug(follower());
        BindingManager.update();
    }
    @Override
    public void onStop() {
        // Runs ONCE when stop is pressed
        telemetry.addLine("Stopping!");
        telemetry.update();
    }
}
