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
import dev.nextftc.ftc.Gamepads;


public class vision implements Subsystem {

    public static final vision INSTANCE = new vision();

    private vision() {

    }

    // Test!

    private Limelight3A limelight;
    private static double mountedHeight = 7.5;
    private static double targetHeight = 0.8;

    @Override
    public void initialize() {
        limelight = ActiveOpMode.hardwareMap().get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(250);
        limelight.start();

        Gamepads.gamepad1().a().toggleOnBecomesTrue()
                .whenBecomesTrue(() -> limelight.pipelineSwitch(1))
                .whenBecomesFalse(() -> limelight.pipelineSwitch(0));
    }

    @Override
    public void periodic() {
        LLResult result = limelight.getLatestResult();

        double robotX = follower().getPose().getX();
        double robotY = follower().getPose().getY();

        if(result != null) {
            double[] pythonOutputs = result.getPythonOutput();
            if (pythonOutputs != null && pythonOutputs.length > 0) {
                double area = pythonOutputs[5];
                ActiveOpMode.telemetry().addData("area", area);
            }
//            double tx = result.getTx();
//            double ty = result.getTy();
//            Pose targetPose = getTargetPose(robotX, robotY, tx, ty );
//            ActiveOpMode.telemetry().addData("target pose", targetPose);
//            Drawing.drawObject(targetPose);
//            ActiveOpMode.telemetry().addLine("reading results!");
//        } else {
//            ActiveOpMode.telemetry().addLine("No results seen/Not valid!");
//        }
        }
        ActiveOpMode.telemetry().addData("has valid results",result != null && result.isValid() );
        ActiveOpMode.telemetry().addData("pipleine", limelight.getStatus().getPipelineIndex());
        ActiveOpMode.telemetry().update();


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
