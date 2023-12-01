package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    final double Shooterready = 0;
    final double Shootertakeoff = 0.5;

    final double Downshooterarm = 0;
    final double UpShooterarm = 0.5;

    ///init to .4
    private static final double OnShooter = 0.1;
    private static final double OffShooter = 0;

    static boolean ShooteronB = false;


    private static final double ClosedShooterArm = 0;
    private static final double OpenShooterArm = 0.25;

    static boolean ShooterArmclosedB = false;


    static Servo Shooter;
    static Servo Shooterarm;

    public enum BucketStartPosition {
        //Bucket Points In (towards the intake)
        IN,
        //Bucket Points Out (towards the backboard)

        OUT
    }

    public enum BucketGateStartPosition {
        //Bucket Points In (towards the intake)
        CLOSE,
        //Bucket Points Out (towards the backboard)

        OPEN
    }

    public void init(HardwareMap hwMap, BucketStartPosition BSP, BucketGateStartPosition BGSP) {
        Shooter = hwMap.get(Servo.class, "Shooter");
        Shooterarm = hwMap.get(Servo.class, "ShooterArm");
        Shooter.setPosition(OnShooter);
        Shooterarm.setPosition(ClosedShooterArm);
        //     if (BSP == BucketStartPosition.IN) {
        //         Bucket.setPosition(IntakeSide);
        //     } else if (BSP == BucketStartPosition.OUT) {
        //         Bucket.setPosition(OutputSide);
        //     }

        //     if (BGSP == BucketGateStartPosition.CLOSE) {
        //         BucketGateIn();
        //     } else if (BGSP == BucketGateStartPosition.OPEN) {
        //         BucketGateOut();
        //     }

    }

    //Bucket

    public void Shooterset(int BucketPos) {

        switch (BucketPos) {
            case 2:
                Shooter.setPosition(Shooterready);
                break;
            case 1: // This is first
                Shooter.setPosition(Shootertakeoff);
                break;

        }
    }

    public static void Readyshooter() {
        Shooter.setPosition(OffShooter);
        ShooteronB = true;
    }

    public static void Takeoffshooter() {
        Shooter.setPosition(OnShooter);
        ShooteronB = false;
    }


    public static void ToggleBucket() {
        if (ShooteronB) {
            Readyshooter();
        } else {
            Takeoffshooter();
        }
    }

    public static double getBucketPosition() {
        return Shooter.getPosition();
    }


    //BucketGate

    public static void ShooterArmOut() {
        Shooterarm.setPosition(ClosedShooterArm);
        ShooterArmclosedB = true;
    }

    public static void ShooterArmIn() {
        Shooterarm.setPosition(OpenShooterArm);
        ShooterArmclosedB = false;
    }

    public static void ToggleBucketGate() {
        if (ShooterArmclosedB) {
            Readyshooter();
        } else {
            Takeoffshooter();
        }
    }


    public void BucketGate(int BucketGatePos) {

        switch (BucketGatePos) {
            case 1:
                Shooterarm.setPosition(OnShooter);
                break;
            case 2:
                Shooterarm.setPosition(OffShooter);
                break;

        }
    }

    public static double getGatePosition() {
        return Shooterarm.getPosition();
    }
}
