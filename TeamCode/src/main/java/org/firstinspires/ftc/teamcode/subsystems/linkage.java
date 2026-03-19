package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.constants.linkageConstants.inchesPerTick;
import static org.firstinspires.ftc.teamcode.subsystems.constants.linkageConstants.pidCof;
import static org.firstinspires.ftc.teamcode.subsystems.constants.linkageConstants.target;
import static org.firstinspires.ftc.teamcode.subsystems.constants.linkageConstants.tolerance;

import dev.nextftc.control2.feedback.PIDController;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.impl.MotorEx;


public class linkage implements Subsystem {
    public static linkage INSTANCE = new linkage();
    public linkage() {

    }

    private MotorEx linkage1 = new MotorEx("linkage1");
    private MotorEx linkage2 = new MotorEx("linkage2");
    private PIDController controller2;


    @Override
    public void initialize() {
        controller2 = new PIDController(pidCof);
    }

    @Override
    public void periodic() {
        double currentPosition = linkage1.getCurrentPosition();
        double powerNeeded = controller2.calculate(target-currentPosition);
        linkage1.setPower(powerNeeded);
        linkage2.setPower(-powerNeeded);

        ActiveOpMode.telemetry().addData("position", currentPosition);
        ActiveOpMode.telemetry().addData("power", powerNeeded);
    }

    public void setTarget(double inches) {
        double ticksPosition = inches*inchesPerTick;
        target = ticksPosition;
    }
    public boolean reachedPosition() {
        return Math.abs(linkage1.getCurrentPosition() - target) <= tolerance;
    }

    public double getPosition() {
        return linkage1.getCurrentPosition();
    }


}
