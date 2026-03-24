package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.linkage;

import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Ticks Per Measurement Finder")
public class ticksPerMeasurement extends NextFTCOpMode {
    public ticksPerMeasurement() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                CommandManager.INSTANCE,
                new SubsystemComponent(linkage.INSTANCE)
        );
    }
    @Override
    public void onStartButtonPressed() {
        linkage.INSTANCE.overridePosition();
    }


    @Override
    public void onUpdate() {
        double position = linkage.INSTANCE.getPosition();
        telemetry.addData("Position", position);
        telemetry.addLine("Move the slides 1 inch");
        telemetry.addData("Distance traveled (IF 1:1 CONVERSION):", position);
        telemetry.addData("Multiplier: ", getMultiplier(1, position));
        telemetry.addData("This should show 1: ", getMultiplier(1, position)*position);
        telemetry.update();
    }

    public double getMultiplier(double movedDistance, double ticksDistance) {
        return movedDistance/ticksDistance;
    }
}
