package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    DcMotor motor;
    public final double INTAKE_SPEED = .5;

    public void init(HardwareMap hwmap){

        motor = hwmap.get(DcMotor.class, "intakeM");
        motor.setPower(0);

    }
    public void Intake_stop(){ motor.setPower(0); }
    public void Intake_start(){ motor.setPower(-INTAKE_SPEED); }
    public void Intake_inverse(){ motor.setPower(INTAKE_SPEED); }
}
