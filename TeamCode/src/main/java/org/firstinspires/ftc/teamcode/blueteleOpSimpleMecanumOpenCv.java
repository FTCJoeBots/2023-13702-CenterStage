package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;


//import org.firstinspires.ftc.robotcontroller.external.samples.RightsideObjectDetector;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

/**
 *import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 *import com.qualcomm.robotcore.hardware.DcMotor;
 *
 *
 */

/**
 *Notes For this TeleOp Code. This code is for Comp and all proggramers should review over this
 *code and understand this code for the possibility that a question may be asked related to TeleOp and
 *you should be able to explain in good detail everything in this code.
 *11/16/17-> Changed all gamepad's in code to correct gamepad (i.e some gamepad1's to gamepad2)
 ***11/18/17-> Competition Notes below
 *Notes-> Autonomous is incorrect, Not much was wrong from a software sandpoint but hardware issues were fixed
 *Autonomous issues included: Incorrect spinning causing us to move out of destination,
 *To much time on the down motion of the clamp and arm.
 *These issues are still not resolved
 * Recomendation for autonomous issues(Not Offical):Fine tune the timer on the clamp
 * Fine tune the movements and LOWER the TIME OF MOVEMENT in autonomous.
 * List of issues at Comp(1)-> https://docs.google.com/a/stjoebears.com/spreadsheets/d/1r_liipKBU7GHfONdxq9E6d4f7zikcCuXwDL2bsQfwm0/edit?usp=sharing
 *G-Sheet of time VS Heading for autonomous -> https://docs.google.com/a/stjoebears.com/spreadsheets/d/1pqv0iN94fFd5KvX1YIWP7z39HgpURXsscn0zPujs1q4/edit?usp=sharing
 */
@TeleOp(name="Simple Mecanum Drive OpenCv ", group="JoeBots")

public class blueteleOpSimpleMecanumOpenCv extends OpMode {
    OpenCvCamera webcam;

    //private MecanumDrive mecanumDrive = new MecanumDrive();
    ObjectDetector OD = new ObjectDetector(telemetry);


    protected int driveStyle = 1;
    private double[] distances;
    boolean currentStateA;

    // Set Drive Motor Limits for Driver Training
    static  double FORWARD_K = 1;
    static double RIGHT_K = 1;
    static double CLOCKWISE_K = 1;

    //HardwareChassisBot robot = new HardwareChassisBot();

    @Override
    // Code to run ONCE when the operators Initializes the robot
    public void init() {

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




        telemetry.speak("initializing Complete");
        telemetry.update();
    }

    // Code to run REPEATEDLY until the driver hits PLAY
    public void init_loop() {

        //This code should be executed in a loop while waiting for the start button to be pressed.
        //This is where we'll ask the driver what type of drive they would prefer

    }

    @Override
    public void loop() {

        currentStateA = gamepad1.a;
        if(currentStateA ==true){
            telemetry.addData("location:",OD.getLocation());

        }
        if(OD.getLocation() == ObjectDetector.Location.LEFT){
            telemetry.addLine("FOUND OBJECT");
            FORWARD_K = 0;
            RIGHT_K = 0;
            CLOCKWISE_K = 0;

        }
        if(gamepad1.b == true){
            FORWARD_K = 1;
            RIGHT_K = 1;
            CLOCKWISE_K = 1;
        }
        double forward;
        double strafe;
        double rotate;

        // Read the driveStyle variable and set the drive components appropriately.
        telemetry.addData("left stick", gamepad1.left_stick_y);
        telemetry.update();
        forward = gamepad1.left_stick_y * -1;
        strafe = gamepad1.right_trigger - gamepad1.left_trigger;
        rotate = gamepad1.right_stick_x;

        // Apply Driver Training limits
        forward = forward * FORWARD_K;
        strafe = strafe * RIGHT_K;
        rotate = rotate * CLOCKWISE_K;





    }


}

