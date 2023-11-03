package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.tuning.Lift;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

/**
 * This is sample code used to explain how to write an autonomous code
 *
 */

@Autonomous(name="Vision Sample", group="Pushbot")

public class autoVisionSample extends LinearOpMode {

    /* Declare OpMode members. */
    OpenCvCamera webcam;
    ObjectDetector OD = new ObjectDetector(telemetry);
    private ElapsedTime     runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        Pose2d startPose = new Pose2d(-65,36, Math.toRadians(90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        Lift lift = new Lift();

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
    } else if (OD.getIntLocation() == 1) {
        telemetry.addLine("CENTER");
        telemetry.update();
        sleep(30);

    } else {
        telemetry.addData("Location", OD.getIntLocation());
        telemetry.addLine("LEFT");
        telemetry.update();
        sleep(30);
    }
}
        waitForStart();
        telemetry.addLine("Ending vision detection...");
        telemetry.update();

        sleep(5000);


        telemetry.addLine("All done.  Press stop.");
        telemetry.update();

        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                    //    .
                        .build());


    }

}
