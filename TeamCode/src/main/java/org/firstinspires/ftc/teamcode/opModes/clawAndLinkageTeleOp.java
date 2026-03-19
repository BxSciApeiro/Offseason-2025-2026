package org.firstinspires.ftc.teamcode.opModes;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Drawing;
import org.firstinspires.ftc.teamcode.subsystems.clawLinkageGroup;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "claw and linkage test!")
public class clawAndLinkageTeleOp extends NextFTCOpMode {
    public clawAndLinkageTeleOp() {
        addComponents(
                new PedroComponent(Constants::createFollower),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                CommandManager.INSTANCE,
                new SubsystemComponent(clawLinkageGroup.INSTANCE)
        );
    }
@Override
public void onStartButtonPressed() {
        clawLinkageGroup.INSTANCE.buttonMap();
}
@Override
public void onUpdate() {
    follower().update();
    Drawing.drawDebug(follower());
    BindingManager.update();
    telemetry.addLine("please work?");
    telemetry.update();
}

}
