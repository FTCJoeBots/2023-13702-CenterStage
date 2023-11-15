package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.tuning.Intake;
import org.firstinspires.ftc.teamcode.tuning.Lift;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


/**
 * This is sample code used to explain how to write an autonomous code
 *
 */

@Autonomous(name="BlueLeftAutoTest", group="Pushbot")

public class BlueLeftAutoTest extends LinearOpMode {

    /* Declare OpMode members. */
    OpenCvCamera webcam;
    BlueLeftObjectDetector OD = new BlueLeftObjectDetector(telemetry);
    private ElapsedTime     runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        Pose2d startPose = new Pose2d(0,0, Math.toRadians(-90));
        Pose2d Pose1 = new Pose2d(-20, 32, Math.toRadians(-90));
        Pose2d Pose2 = new Pose2d(-17, 75, Math.toRadians(-90));
        Intake intake = new Intake();

        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        MecanumDrive drive1 = new MecanumDrive(hardwareMap, Pose1);
        MecanumDrive drive2 = new MecanumDrive(hardwareMap, Pose2);
        Lift lift = new Lift();
        intake.init(hardwareMap);
        lift.init(hardwareMap);
        telemetry.addLine("Press > to Start");
        telemetry.update();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.setPipeline(OD);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the camera to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Also, we specify the rotation that the camera is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        //telemetry.addData("location:",OD.getIntLocation());
        //Put this in a loop to run before we press start (?)
        //  Or do this right after we press start
        //
        while(!isStarted()) {
            if (OD.getIntLocation() == 2) {
                telemetry.addData("Location", OD.getIntLocation());
                telemetry.addLine("RIGHT");
                telemetry.update();
                sleep(30);
            }
            else if (OD.getIntLocation() == 1) {
                telemetry.addLine("CENTER");
                telemetry.update();
                sleep(30);
            }
            else {
                telemetry.addData("Location", OD.getIntLocation());
                telemetry.addLine("LEFT");
                telemetry.update();
                sleep(30);
            }
        }
        waitForStart();
        telemetry.addLine("Ending vision detection...");
        telemetry.update();

        sleep(500);
        telemetry.addLine("All done.  Press stop.");
        telemetry.update();
        if (OD.getIntLocation() == 2) { // Right
            Actions.runBlocking(new SequentialAction(
                    drive.actionBuilder(drive.pose)
                            .setTangent(0)
                            .strafeToLinearHeading(new Vector2d(29, 15), Math.toRadians(-65))
                            .strafeToLinearHeading(new Vector2d(35, 6), Math.toRadians(-65))
                            .build()

                    // intake.Intake_inverse(),

                   /* drive.actionBuilder(drive1.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-17.0, 75.0, Math.toRadians(-75)), 0)
                            .build(),


                    drive.actionBuilder(drive2.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(0.0, 0.0, Math.toRadians(-75)), 0)
                            .splineToLinearHeading(new Pose2d(0.0, -80.0, Math.toRadians(-75)), 0)
                            .build()*/
            ));
        }
            if (OD.getIntLocation() == 1) { // Center
                Actions.runBlocking(new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .setTangent(0)
                                .strafeToLinearHeading(new Vector2d(41, 43), Math.toRadians(-65))
                                //.strafeToLinearHeading(new Vector2d(41, 38), Math.toRadians(-65))
                                .build()

                        // intake.Intake_inverse(),

                   /* drive.actionBuilder(drive1.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-17.0, 75.0, Math.toRadians(-75)), 0)
                            .build(),


                    drive.actionBuilder(drive2.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(0.0, 0.0, Math.toRadians(-75)), 0)
                            .splineToLinearHeading(new Pose2d(0.0, -80.0, Math.toRadians(-75)), 0)
                            .build()*/
                ));
            }
                if (OD.getIntLocation() == 0) { // Left
                    Actions.runBlocking(new SequentialAction(
                            drive.actionBuilder(drive.pose)
                                    .setTangent(0)
                                    .strafeToLinearHeading(new Vector2d(29, 47), Math.toRadians(-65))
                                    .strafeToLinearHeading(new Vector2d(35, 42), Math.toRadians(-65))
                                    .build()

                            // intake.Intake_inverse(),

                   /* drive.actionBuilder(drive1.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-17.0, 75.0, Math.toRadians(-75)), 0)
                            .build(),


                    drive.actionBuilder(drive2.pose)
                            .waitSeconds(2)
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(0.0, 0.0, Math.toRadians(-75)), 0)
                            .splineToLinearHeading(new Pose2d(0.0, -80.0, Math.toRadians(-75)), 0)
                            .build()*/
                    ));
                }
        }
    }
//}