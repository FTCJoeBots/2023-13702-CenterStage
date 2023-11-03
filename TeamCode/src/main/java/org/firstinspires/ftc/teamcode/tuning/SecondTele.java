package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name="Second Tele", group="0")

public class SecondTele extends LinearOpMode {

    HardwareMap hwmap;
    static final double MAX_SPEED = 0.9;
    static final double TURTLE_SPEED = MAX_SPEED / 2;
    private double strafe = 0;
    private double rotate = 0;
    private double forward = 0;
    boolean prevstateRB;
    boolean currentstateRB;
    boolean currentStateB;
    boolean prevStateB;
    boolean currentstateDR;
    boolean prevstateDR;
    boolean  currStateGP2LeftBumper;
    boolean prevStateGP2LeftBumper;
    boolean currentstatedpadleft;
    boolean previousStatedpadleft;
    int stateIntake = 0;
    boolean preLift = false;
    boolean preIntake = false;
    boolean nowIntake;
    boolean previoiusPressedIntake = false;
    boolean aPrev = false;
    float RTPrev ;
    float LTPrev ;
    boolean curRB;
    boolean prevRB;
    boolean TurtleMode=false;
    boolean Previous1A=false;
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap , new Pose2d(0, 0, 0));
        waitForStart();


        while (opModeIsActive() && !isStopRequested()) {

            strafe = (gamepad1.left_trigger -gamepad1.right_trigger) * MAX_SPEED;
            rotate = gamepad1.right_stick_x;
            forward = -gamepad1.left_stick_y * MAX_SPEED;



            //telemetry.speak("NERDS!!!");

            telemetry.update();

        }
    }
}
