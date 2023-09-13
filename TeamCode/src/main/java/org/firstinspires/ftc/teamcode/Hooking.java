package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Hooking")
public class Hooking extends LinearOpMode {

    private DcMotor rh;
    private DcMotor lh;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        rh = hardwareMap.get(DcMotor.class, "rh");
        lh = hardwareMap.get(DcMotor.class, "lh");

        rh.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lh.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Put initialization blocks here.
        waitForStart();
        while (opModeIsActive()) {
            rh.setPower(gamepad2.right_stick_y);
            lh.setPower(gamepad2.left_stick_y);
            telemetry.addData("Rh_power", rh.getPower());
            telemetry.addData("Lh_power", lh.getPower());
            telemetry.update();
        }
    }
}