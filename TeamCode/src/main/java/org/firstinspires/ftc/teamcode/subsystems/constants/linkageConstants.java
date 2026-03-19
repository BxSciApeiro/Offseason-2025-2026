package org.firstinspires.ftc.teamcode.subsystems.constants;

import com.bylazar.configurables.annotations.Configurable;

import dev.nextftc.control2.feedback.PIDCoefficients;


@Configurable
public class linkageConstants {
    public static double p;
    public static double i;
    public static double d;
    public static PIDCoefficients pidCof = new PIDCoefficients(p, i, d);
    public static double target;
    public static double inchesPerTick = 0.001;
    public static double tolerance = 10;

}
