package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.constants.clawConstants.staticPosition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.claw;

import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Servo Angle Finding")
public class angleFindingTeleOP extends NextFTCOpMode {
    public angleFindingTeleOP() {
        addComponents(
                new SubsystemComponent(claw.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                CommandManager.INSTANCE
        );
    }

    @Override
    public void onUpdate() {
        claw.INSTANCE.setPosition(staticPosition);
        telemetry.addLine("Change the angle on Panels: staticPosition");
        telemetry.addData("angle", staticPosition);
        telemetry.update();
    }
}
