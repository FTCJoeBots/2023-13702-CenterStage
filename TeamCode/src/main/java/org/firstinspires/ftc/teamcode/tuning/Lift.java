package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
public class Lift {

    public static final double LIFT_SPEED=.25;
    public static final int LIFT_MANUAL_INCREMENT=30;
    DcMotor LeftLift;
    DcMotor RightLift;

    int ground = 0;
    int low = 1000;
    int med = 2000;
    int high = 3000;

    int LIFTMAXIMUM = 3000;
    int LIFTMINIMUM = 0;
    int lift_target_position=0;

    public void init(HardwareMap hwMap){
        LeftLift = hwMap.get(DcMotor.class,"LeftLift");
        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLift.setTargetPosition(LIFTMINIMUM);
        LeftLift.setDirection(DcMotor.Direction.FORWARD);
        LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftLift.setPower(0);
        RightLift = hwMap.get(DcMotor.class,"RightLift");
        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightLift.setDirection(DcMotor.Direction.REVERSE);
        RightLift.setTargetPosition(LIFTMINIMUM);
        RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLift.setPower(0);
    }

    public void Lift_to_Pos(int liftPositions){
        switch (liftPositions){
            case 0:
                lift_target_position = ground;
                break;
            case 1:
                lift_target_position = low;
                break;
            case 2:
                lift_target_position = med;
                break;
            case 3:
                lift_target_position = high;


        }

    }

    public void contorller(){
        LeftLift.setTargetPosition(lift_target_position);
        LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftLift.setPower(LIFT_SPEED);
        RightLift.setTargetPosition(lift_target_position);
        RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLift.setPower(LIFT_SPEED);
    }

    public void raiseLiftManual(){
        lift_target_position = LeftLift.getCurrentPosition()+LIFT_MANUAL_INCREMENT;
        lift_target_position = RightLift.getCurrentPosition()+LIFT_MANUAL_INCREMENT;
    }

    public void lowerLiftManual(){

        int newTargetLeftLiftPosition = LeftLift.getCurrentPosition()-LIFT_MANUAL_INCREMENT;
        int newTargetRightLiftPosition = RightLift.getCurrentPosition()-LIFT_MANUAL_INCREMENT;

        if(newTargetLeftLiftPosition <= 0 || newTargetRightLiftPosition <= 0){
            lift_target_position = LIFTMINIMUM;
        }
        else{
            lift_target_position = newTargetLeftLiftPosition;
            lift_target_position = newTargetRightLiftPosition;
        }
    }


}

