package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "velocity")
public class Velocity extends OpMode {

    DcMotorEx lm;
    DcMotorEx rm;
//    private double vel = 0;

    @Override
    public void init() {
        lm = hardwareMap.get(DcMotorEx.class, "lm");
        rm = hardwareMap.get(DcMotorEx.class, "rm");
        rm.setDirection(DcMotorEx.Direction.REVERSE);
    }

    @Override
    public void loop() {
        lm.setVelocity(1400);
        rm.setVelocity(1400);

    }
}
