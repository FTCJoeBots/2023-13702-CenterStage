package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name="First Tele", group="0")

public class FirstTele extends LinearOpMode {

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

        Intake intake = new Intake();
        Lift lift = new Lift();
        Shooter shooter = new Shooter();
        Bucket bucketFunctions = new Bucket();

        intake.init(hardwareMap);
        lift.init(hardwareMap);
        //shooter.init(hardwareMap);
        bucketFunctions.init(hardwareMap, Bucket.BucketStartPosition.OUT, Bucket.BucketGateStartPosition.CLOSE);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            strafe = (gamepad1.left_trigger -gamepad1.right_trigger) * MAX_SPEED;
            rotate = gamepad1.right_stick_x;
            forward = -gamepad1.left_stick_y * MAX_SPEED;



            if(gamepad1.a&&!Previous1A) {
                TurtleMode=!TurtleMode;
                Previous1A=true;
            }
            if(!gamepad1.a) {
                Previous1A=false;
            }
            if(TurtleMode) {
                strafe*=0.3;
                forward*=0.3;
                rotate*=0.3;
            }

            drive.setDrivePowers(
                    new PoseVelocity2d(new Vector2d(
                            forward,
                            strafe
                    ), -rotate
                    ));

            // OPERATOR CODE

            //Intake: Y
            //Manual Lift: Right Bumper (up), Left Bumper (down)
            //Lift Positions: dpad down (ground), dpad left (1), dpad right (2), dpad up (3)
            //Bucket: Right Trigger
            //BucketGate: Left Trigger
            //Shooter: ??
            //hanger
            if (gamepad1.b){
                lift.resetHanger();
                lift.Hcheck();
            }
            if (gamepad1.y){
                lift.upHanger();
                lift.Hcheck();

            }
            if (gamepad1.x){
                lift.zeroHanger();
                lift.Hcheck();

            }
            //intake
            if (gamepad2.x){
                intake.Intake_stop();
                intake.Intake_start();
            }
            if (gamepad2.a){

                intake.Intake_stop();
            }
            if (gamepad2.b){
                intake.Intake_stop();
                intake.inverse();
            }

            /*nowIntake = gamepad2.y;
            if (nowIntake && nowIntake != preIntake) {
                // Normal suction
                if (stateIntake == 1) {
                    intake.Intake_start();

                }
                // Rejection
                else if (stateIntake == 2) {
                    intake.Intake_stop();
                    intake.Intake_inverse();
                }
                // Stop
                else if (stateIntake == 3) {
                    intake.Intake_stop();
                }
                stateIntake += 1;
                if (stateIntake > 3) {
                    stateIntake = 1;
                }
                previoiusPressedIntake = true;
            }
            preIntake = nowIntake;
*/
            //Lift


            //Manual LIFT (we don't accually need this)
            if(gamepad2.right_bumper){
                lift.raiseLiftManual();
                lift.contorller();
            }
            if(gamepad2.left_bumper){
                lift.lowerLiftManual();
                lift.contorller();
            }


            if(gamepad2.dpad_up){
                lift.Lift_to_Pos(3);
                lift.contorller();
            }
            if(gamepad2.dpad_right){
                lift.Lift_to_Pos(2);
                lift.contorller();
            }
            if(gamepad2.dpad_left){
                lift.Lift_to_Pos(1);
                lift.contorller();
            }

            if(gamepad2.dpad_down){
                lift.Lift_to_Pos(0);
                lift.contorller();
            }


            // States LIFT
          /*  nowLift = gamepad2.x;
            //preLift = false;
            if (nowLift && (nowLift != preLift)) {

                // ground
                if (stateLift == 1) {
                    lift.Lift_To_Position(0);
                    lift.check();
                }
                // low
                else if (stateLift == 2) {
                    lift.Lift_To_Position(1);
                    lift.check();
                }
                // med
                else if (stateLift == 3) {
                    lift.Lift_To_Position(2);
                    lift.check();
                }
                // high
                else if (stateLift == 4) {
                    lift.Lift_To_Position(3);
                    lift.check();
                }
                stateLift += 1;
                if (stateLift > 4) {
                    stateLift = 1;
                }
                previoiusPressedLift = true;
            }
            preLift = nowLift;
*/





            //Bucket
            //telemetry.addData("BucketEncoders",Bucket.getBucketPosition());


            if(gamepad2.right_trigger >= .7){
                if(lift.LeftLift.getCurrentPosition()>190) {
                    bucketFunctions.BucketOut();
                }

            } else {
                bucketFunctions.BucketIn();
            }

            //Cur = gamepad1.j;
            //if(Cur != Prev){ Motor.Toggle }
            // Prev = Cur


            //BucketGate
            // telemetry.addData("BucketGateEncoders",Bucket.getGatePosition());

            if (gamepad2.left_trigger >= .7) {
                bucketFunctions.BucketGateOut();
            } else {
                bucketFunctions.BucketGateIn();
            }


            //Shooter

           /* if (gamepad2.a && !aPrev) {
                shooter.Toggle();
             }
            aPrev = gamepad2.a;*/



            /*strafe = gamepad1.left_trigger -gamepad1.right_trigger;

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * MAX_SPEED,
                            strafe*MAX_SPEED,
                            -gamepad1.right_stick_x*MAX_SPEED
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();


*/





            //telemetry.addData("LiftEncoders",lift.joseph_target_position);

            //telemetry.speak("NERDS!!!");
            telemetry.addData("Left Lift:  ",lift.LeftLift.getCurrentPosition());
            telemetry.addData("Right Lift:  ",lift.RightLift.getCurrentPosition());

            telemetry.update();

        }
    }
}
