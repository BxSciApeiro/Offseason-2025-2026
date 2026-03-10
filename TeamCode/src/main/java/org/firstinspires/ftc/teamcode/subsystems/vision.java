package org.firstinspires.ftc.teamcode.subsystems;

import com.bylazar.field.PanelsField;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.internal.hardware.android.GpioPin;
import org.firstinspires.ftc.teamcode.pedroPathing.Drawing;

import dev.nextftc.core.subsystems.Subsystem;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;
import dev.nextftc.ftc.ActiveOpMode;


public class vision implements Subsystem {

    public static final vision INSTANCE = new vision();

    private vision() {

    }

    // Test!

    private Limelight3A limelight;
    private LLResult result;
    private static double mountedHeight = 15;
    private static double targetHeight = 15;

    @Override
    public void initialize() {
        limelight = ActiveOpMode.hardwareMap().get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(4);
        limelight.setPollRateHz(250);
        limelight.start();
    }

    @Override
    public void periodic() {
        result = limelight.getLatestResult();

        double robotX = follower().getPose().getX();
        double robotY = follower().getPose().getY();

        if(hasValidResult()) {
            double tx = result.getTx();
            double ty = result.getTy();
            Pose targetPose = getTargetPose(robotX, robotY, tx, ty );
            ActiveOpMode.telemetry().addData("target pose", targetPose);
            Drawing.drawObject(targetPose);
            ActiveOpMode.telemetry().addLine("reading results!");
        } else {
            ActiveOpMode.telemetry().addLine("No results seen/Not valid!");
        }
        ActiveOpMode.telemetry().update();


    }

    public boolean hasValidResult() {
        return result != null && result.isValid();
    }
    public double yDistanceFromTarget(double ty) {
        double LLH = mountedHeight;
        double TH = targetHeight;
        return (LLH-TH)/(Math.tan(Math.toRadians(ty)));
    }

    public double xDistanceFromTarget(double ty, double tx) {
        return yDistanceFromTarget(ty)/(Math.tan(Math.toRadians(tx)));
    }

    public Pose getTargetPose(double robotX, double robotY, double tx, double ty) {
        return new Pose(robotX+xDistanceFromTarget(ty, tx), robotY+yDistanceFromTarget(ty));
    }
}
