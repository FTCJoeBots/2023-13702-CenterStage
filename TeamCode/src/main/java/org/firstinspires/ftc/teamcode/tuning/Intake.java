package org.firstinspires.ftc.teamcode.tuning;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.acmerobotics.roadrunner.*;
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
    public void inverse(){ motor.setPower(INTAKE_SPEED); }

    public class InverseIntake implements Action {
        public void init(){
            inverse();
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            inverse();
            return false;
        }
    }
    public Action Intake_inverse() {
        return new Intake.InverseIntake();
    }
}
