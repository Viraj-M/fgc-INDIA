package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp(name = "Ching_Chong")
public class ching_chong extends OpMode {

    DcMotorEx motor;
    @Override
    public void init(){
        motor = hardwareMap.get(DcMotorEx.class, "ching_chong");
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void loop(){
        if (gamepad1.cross){
            motor.setPower(1);
        } else if (gamepad1.triangle) {
            motor.setPower(-1);
        } else {
            motor.setPower(0);
        }

    }

}
