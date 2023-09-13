package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "slider_set")
public class slider_set extends OpMode {

    DcMotorEx ls;
    DcMotorEx rs;
    TouchSensor lsms;
    TouchSensor rsms;
    @Override
    public void init() {
        ls = hardwareMap.get(DcMotorEx.class, "ls");
        rs = hardwareMap.get(DcMotorEx.class, "rs");
        lsms = hardwareMap.get(TouchSensor.class, "ls_ms");
        rsms = hardwareMap.get(TouchSensor.class, "rs_ms");
        ls.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rs.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        double psr = gamepad2.right_stick_y;
        double psl = gamepad2.left_stick_y;


        telemetry.addData("lsp:- ", ls.getCurrentPosition());
        telemetry.addData("rsp:- ", rs.getCurrentPosition());
        telemetry.addData("power_left:- ", ls.getPower());
        telemetry.addData("power_right:- ", rs.getPower());
        telemetry.update();

        if (!lsms.isPressed()){
            ls.setPower(psl * 0.5);
        } else {
            if (psl > 0){
                ls.setPower(psl * 0.5);
            } else {
                ls.setPower(0);
            }
        }
        if (!rsms.isPressed()){
            rs.setPower(psr * 0.5);
        } else {
            if (psr > 0){
                rs.setPower(psr * 0.5);
            } else {
                rs.setPower(0);
            }
        }
    }
}
