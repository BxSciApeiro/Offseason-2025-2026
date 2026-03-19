package org.firstinspires.ftc.teamcode.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.SubsystemGroup;
import dev.nextftc.ftc.Gamepads;

public class clawLinkageGroup extends SubsystemGroup {
    public static clawLinkageGroup INSTANCE = new clawLinkageGroup();
    public clawLinkageGroup() {
        super(
                claw.INSTANCE,
                linkage.INSTANCE
        );
    }

    public Command extendAndClose(double inches) {
        return new SequentialGroup(
                new InstantCommand(() -> linkage.INSTANCE.setTarget(inches)),
                new WaitUntil(() -> linkage.INSTANCE.reachedPosition()),
                new InstantCommand(() -> claw.INSTANCE.close())
        );
    }
    public Command resetToDefault() {
        return new SequentialGroup(
                new InstantCommand(() -> linkage.INSTANCE.setTarget(0)),
                new WaitUntil(() -> linkage.INSTANCE.reachedPosition()),
                new InstantCommand(() -> claw.INSTANCE.open())
        );
    }

    public void buttonMap() {
        Gamepads.gamepad1().triangle()
                .whenTrue(() -> extendAndClose(-Gamepads.gamepad1().leftStickY().get()).schedule())
                .whenFalse(resetToDefault());
    }
}
