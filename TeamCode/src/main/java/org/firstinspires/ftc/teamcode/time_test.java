package org.firstinspires.ftc.teamcode;



import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;


@TeleOp(name = "time_elapsed_test")
public class time_test extends OpMode {
    DcMotorEx lm;
    DcMotorEx rm;
    int avg_current_rm = 0;
    int avg_current_lm = 0;
    int times_executed = 0;
    ElapsedTime time = new ElapsedTime();
    @Override
    public void init() {
        lm = hardwareMap.get(DcMotorEx.class, "lm");
        rm = hardwareMap.get(DcMotorEx.class, "rm");
        lm.setDirection(DcMotorSimple.Direction.REVERSE);
        rm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {
        long current_time = time.time(TimeUnit.SECONDS);

    }
}