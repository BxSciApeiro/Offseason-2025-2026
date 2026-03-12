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


@TeleOp(name = "vision testing TeleOp")
public class visionTestingTeleOp extends NextFTCOpMode {
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
        telemetry.addLine("Init was Pressed!");
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
