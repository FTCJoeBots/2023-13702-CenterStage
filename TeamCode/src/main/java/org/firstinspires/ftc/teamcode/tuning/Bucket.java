package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bucket {
    final double IntakeSide = 0;
    final double OutputSide = 0.5;

    final double ClosedBucket = 0;
    final double OpenBucket = 0.5;

    ///init to .4
    private static final double InBucket = 0.497;
    private static final double OutBucket = 0.1;

    static boolean BucketInB = false;


    private static final double ClosedBucketGate = 0.8;
    private static final double OpenBucketGate = 0.58;

    static boolean BucketGateClosedB = false;


    static Servo Bucket;
    static Servo BucketGate;

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
        Bucket = hwMap.get(Servo.class, "Bucket");
        BucketGate = hwMap.get(Servo.class, "BucketGate");
        Bucket.setPosition(InBucket);
        BucketGate.setPosition(ClosedBucketGate);
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

    public void BucketSet(int BucketPos) {

        switch (BucketPos) {
            case 2:
                Bucket.setPosition(IntakeSide);
                break;
            case 1: // This is first
                Bucket.setPosition(OutputSide);
                break;

        }
    }

    public static void BucketOut() {
        Bucket.setPosition(OutBucket);
        BucketInB = true;
    }

    public static void BucketIn() {
        Bucket.setPosition(InBucket);
        BucketInB = false;
    }


    public static void ToggleBucket() {
        if (BucketInB) {
            BucketIn();
        } else {
            BucketOut();
        }
    }

    public static double getBucketPosition() {
        return Bucket.getPosition();
    }


    //BucketGate

    public static void BucketGateOut() {
        BucketGate.setPosition(OpenBucketGate);
        BucketGateClosedB = true;
    }

    public static void BucketGateIn() {
        BucketGate.setPosition(ClosedBucketGate);
        BucketGateClosedB = false;
    }

    public static void ToggleBucketGate() {
        if (BucketGateClosedB) {
            BucketGateIn();
        } else {
            BucketGateOut();
        }
    }


    public void BucketGate(int BucketGatePos) {

        switch (BucketGatePos) {
            case 1:
                BucketGate.setPosition(OpenBucket);
                break;
            case 2:
                BucketGate.setPosition(ClosedBucket);
                break;

        }
    }

    public static double getGatePosition() {
        return BucketGate.getPosition();
    }
//bucket actions
    public class b0 implements Action {
        public void init() {
            BucketOut();
        }
        public boolean loop(){return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            BucketOut();
            return false;
        }
    }
    public Action bPos0() {
        return new Bucket.b0();
    }
    public class b1 implements Action {
        public void init() {
            BucketIn();
        }
        public boolean loop(){return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            BucketIn();
            return false;
        }
    }
    public Action bPos1() {
        return new Bucket.b1();
    }






//bucket gate actions
    public class bgate implements Action {
        public void init() {
            BucketGateOut();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            BucketGateOut();
            return false;
        }
    }


    public Action bgate() {
        return new Bucket.bgate();
    }
    public class bgatein implements Action {
        public void init() {
            BucketGateIn();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            BucketGateIn();
            return false;
        }
    }
    public Action bgatein() {
        return new Bucket.bgatein();
    }



}