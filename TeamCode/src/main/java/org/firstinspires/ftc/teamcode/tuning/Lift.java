package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    final int ground = 200;
    final int low = 1230;
    final int med = 2100;
    final int high = 2950;
    final int initPos = 0;



    final int LIFTMAXIMUM = 3325;
    final int LIFTMINUMMUM = -100;

    private int lift_target_position = 0;





    DcMotor liftMotor;




    //public testLift(HardwareMap hwMap) {
    public void init(HardwareMap hwMap) {
        liftMotor = hwMap.get(DcMotor.class, "lift");
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(LIFTMINUMMUM);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(0);

    }






    public void controller() {
        if (lift_target_position < LIFTMINUMMUM)
            lift_target_position = LIFTMINUMMUM;

        if (lift_target_position > LIFTMAXIMUM)
            lift_target_position = LIFTMAXIMUM;

        liftMotor.setTargetPosition(lift_target_position);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(1);


    }


    public void raiseLiftManual(){
        lift_target_position = liftMotor.getCurrentPosition()+120;
    }
    public void lowerLiftManual(){
        lift_target_position=liftMotor.getCurrentPosition()-120;
    }

    public int getLiftTargetPosition(){
        return lift_target_position;
    }

    public void Lift_To_Position(int LiftPosition) {
        switch (LiftPosition) {
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
                break;
            case 4:
                lift_target_position = initPos;
                break;
            case 5:

        }
    }

    public double getLiftPosition(){ return liftMotor.getCurrentPosition(); }

}
