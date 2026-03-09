package org.firstinspires.ftc.teamcode.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

/**
 * Subsystems are a way to organize your code. Each subsystem will correspond to one mechanism, but each mechanism can have multiple motors/servos!
 */
public class sampleSubsystem implements Subsystem {

    /**
     * Here put your motors/servos/hardware! NextFTC's wrappers are MotorEx and ServoEx, CRServoEx, FeedbackServoEx, FeedbackCRServoEx
     * In the parentheses, put the hardware map name.
     */
    private final MotorEx motorExample  = new MotorEx("motorExample");
    private final ServoEx servoExample = new ServoEx("servoExample");

    /**
     * Commands are really cool! There's too much to put here, so read the docs if u need!
     */

    public Command test = instant(() -> {motorExample.setPower(0.1);} ) ; //THESE NEEDS TO BE TESTED
    public Command test2 = new InstantCommand(() -> {servoExample.setPosition(0.5);});

    @Override
    public void initialize() {
        // Put subsystem initalize logic here!
    }
    @Override
    public void periodic() {
        // Put loop subsystem logic here!
    }

}
