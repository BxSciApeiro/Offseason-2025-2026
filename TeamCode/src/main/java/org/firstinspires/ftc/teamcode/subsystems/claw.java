package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.constants.clawConstants.closePosition;
import static org.firstinspires.ftc.teamcode.subsystems.constants.clawConstants.openPosition;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class claw implements Subsystem {
    public claw() {

    }
    public static claw INSTANCE = new claw();
    private final ServoEx claw = new ServoEx("claw");

    public void close() {
        claw.setPosition(closePosition);
    }
    public void open() {
        claw.setPosition(openPosition);
    }




}
