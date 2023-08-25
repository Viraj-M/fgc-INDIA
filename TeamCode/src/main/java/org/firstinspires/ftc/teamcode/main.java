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


@TeleOp(name = "main")
public class main extends OpMode {
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
        float rt = gamepad1.right_trigger;
        float lt = gamepad1.left_trigger;
        float x = gamepad1.left_stick_x;
        float y = rt - lt;
        int current_lm = (int) lm.getCurrent(CurrentUnit.MILLIAMPS);
        int current_rm = (int) rm.getCurrent(CurrentUnit.MILLIAMPS);
        long current_time = time.time(TimeUnit.SECONDS);

        if (y > 0) {
            lm.setPower(y + x);
            rm.setPower((y - x) * 0.95);
        } else if (y < 0) {
            lm.setPower(y - x);
            rm.setPower((y + x) * 0.95);
        } else if (y == 0) {
            lm.setPower(x);
            rm.setPower(-x);
        }
        telemetry.addData("rm_current:-", current_rm);
        telemetry.addData("lm_current:-", current_lm);

        if(lm.getPower() != 0 || rm.getPower() != 0){
            avg_current_rm = avg_current_rm + current_rm;
            avg_current_lm = avg_current_lm + current_lm;
            times_executed++;


            String logFilePath = String.format("%s/FIRST/data/right.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logFilePath, true), "utf-8"))) {
                writer.write(current_rm + "\n");
            } catch (IOException e) {
                telemetry.addData("Error:- ", e.getMessage());
                telemetry.update();
            }
            String logFilePath2 = String.format("%s/FIRST/data/left.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logFilePath2, true), "utf-8"))) {
                writer.write(current_lm + "\n");
            } catch (IOException e) {
                telemetry.addData("Error:- ", e.getMessage());
                telemetry.update();
            }
        } else if (lm.getPower() == 0 && rm.getPower() == 0) {
            String logFilePath = String.format("%s/FIRST/data/right.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logFilePath, true), "utf-8"))) {
                writer.write(Long.toString(current_time));
            } catch (IOException e) {
                telemetry.addData("Error:- ", e.getMessage());
                telemetry.update();
            }
            String logFilePath2 = String.format("%s/FIRST/data/left.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logFilePath2, true), "utf-8"))) {
                writer.write(Long.toString(current_time));
            } catch (IOException e) {
                telemetry.addData("Error:- ", e.getMessage());
                telemetry.update();
            }
        }
        if(gamepad1.cross){
            telemetry.addData("Avg-right-motor-current:- ", avg_current_rm/times_executed);
            telemetry.addData("Avg-left-motor-current:- ", avg_current_lm/times_executed);
            telemetry.update();
        }
    }
}