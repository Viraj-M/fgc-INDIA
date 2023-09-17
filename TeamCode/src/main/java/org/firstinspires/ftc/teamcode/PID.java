package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class PID extends OpMode {
    DcMotorEx lm;
    DcMotorEx rm;
    @Override
    public void init() {
        lm = hardwareMap.get(DcMotorEx.class, "lm");
    }

    @Override
    public void loop() {

    }
}
