package org.firstinspires.ftc.teamcode.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class claw implements Subsystem {
    public claw() {

    }
    public static claw INSTANCE = new claw();
    private double closePosition;
    private double openPosition;
    private ServoEx claw = new ServoEx("claw");

    public void close() {
        claw.setPosition(closePosition);
    }
    public void open() {
        claw.setPosition(openPosition);
    }




}
