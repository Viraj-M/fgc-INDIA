package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "full_robot_v1")
public class full_robot_v1 extends OpMode {

    DcMotorEx lm;
    DcMotorEx rm;
    DcMotorEx ls;
    DcMotorEx rs;
    DcMotorEx lh;
    DcMotorEx rh;
    DcMotorEx ching_chong;
    DcMotorEx ching_chong2;

    TouchSensor lsms;
    TouchSensor rsms;

    private Servo lf;
    private Servo rf;
    private Servo lsweep;
    private Servo rsweep;
    private Servo lhh;
    private Servo rhh;
    boolean lx = false;
    boolean rx = false;
    private double vel = 0;
    private int rfp;
    private int lfp;
    private double spr = 1;
    private double spl = 0;

    private double speed = 1;

    @Override
    public void init() {
        gamepad1.setLedColor(255, 128, 0, 10000);
        gamepad2.setLedColor(144, 0, 255, 10000);
        lm = hardwareMap.get(DcMotorEx.class, "lm");
        rm = hardwareMap.get(DcMotorEx.class, "rm");
        ls = hardwareMap.get(DcMotorEx.class, "ls");
        rs = hardwareMap.get(DcMotorEx.class, "rs");
        rh = hardwareMap.get(DcMotorEx.class, "rh");
        lh = hardwareMap.get(DcMotorEx.class, "lh");
        lsms = hardwareMap.get(TouchSensor.class, "ls_ms");
        rsms = hardwareMap.get(TouchSensor.class, "rs_ms");
        ching_chong = hardwareMap.get(DcMotorEx.class, "chingchong");
        ching_chong2 = hardwareMap.get(DcMotorEx.class, "chingchong2");
        rm.setDirection(DcMotorEx.Direction.REVERSE);
        ching_chong.setDirection(DcMotorEx.Direction.REVERSE);
        rm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rs.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        ls.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        ching_chong.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        ching_chong2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        ching_chong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ching_chong2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lf = hardwareMap.get(Servo.class, "lf");
        rf = hardwareMap.get(Servo.class, "rf");
        lsweep = hardwareMap.get(Servo.class, "lsweep");
        rsweep = hardwareMap.get(Servo.class, "rsweep");
        lhh = hardwareMap.get(Servo.class, "lhh");
        rhh = hardwareMap.get(Servo.class, "rhh");
        lf.scaleRange(0.4, 1);
        rf.scaleRange(0.2, 0.8);
        rsweep.scaleRange(0, 0.45);
        lsweep.scaleRange(0, 0.5);
    }

    @Override
    public void loop() {
        gamepad1.setLedColor(255, 128, 0, 1000);
        gamepad2.setLedColor(144, 0, 255, 1000);
        float g1rt = gamepad1.right_trigger;
        float g1lt = gamepad1.left_trigger;
        float g2rt = gamepad2.right_trigger;
        //double slider
        float ps = gamepad2.right_stick_y;
        //single left slider
        double psl = gamepad2.left_stick_y;
        //single right slider
        double psr = gamepad2.right_stick_y;
        //hanging control
        float ph = gamepad1.right_stick_y;
        //straight movement
        float y = g1rt - g1lt;
        boolean single_control = g2rt != 0;
        double ching_chong_velocity = gamepad2.left_stick_y * 1820;
        //acceleration factor
        double af = 1.02;
        //horixontal-axis
        double x;
        //Sharp turn fix
        if(y != 0){
            x = gamepad1.left_stick_x/1.3;
        } else {
            x = gamepad1.left_stick_x;
        }
        //Velocity
        vel = vel + ((y - vel) / af);
        //robot speed
        if(gamepad1.triangle){
            speed = 1;
        }
        if (gamepad1.dpad_up && speed < 1){
            speed = speed + 0.15;
        } else if (gamepad1.dpad_down && speed > 0.3){
            speed = speed - 0.15;
        }
        double lmp = ((vel + x) * speed) * 0.87;
        double rmp = (vel - x) * speed;

        //Robot control
        if (gamepad1.left_bumper){
            lm.setPower(0);
            rm.setPower(0);
        } else {
            lm.setPower(lmp);
            rm.setPower(rmp);
        }
        //Intake control
        if(!single_control) {
                ching_chong.setVelocity(ching_chong_velocity);
                ching_chong2.setVelocity(ching_chong_velocity);
        } else {
            if(gamepad2.cross){
                ching_chong.setPower(0.65);
                ching_chong2.setPower(0.65);
            } else {
                ching_chong.setPower(0);
                ching_chong2.setPower(0);
            }
        }



        //Flap control
        if (gamepad2.square){
            lf.setPosition(1);
            lfp = 0;
        }
        if (gamepad2.circle){
            rf.setPosition(0);
            rfp = 0;
        }
        if (gamepad2.dpad_left){
            lf.setPosition(0);
            lfp = 1;
        }
        if (gamepad2.dpad_right){
            rf.setPosition(1);
            rfp = 1;
        }

        //Sweep control
        if (gamepad1.square && spr < 1) {
            spr += 0.15;
        } else if (gamepad1.circle && spr > 0) {
            spr += -0.15;
        }
        if (gamepad1.dpad_right && spl > 0) {
            spl += -0.15;
        } else if (gamepad1.dpad_left && spl < 1) {
            spl += 0.15;
        }
        rsweep.setPosition(spr);
        lsweep.setPosition(spl);

        //Slider control

        if(gamepad2.right_bumper){
            single_control = !single_control;
        }

        if (single_control){
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
        } else {
            if (!lsms.isPressed()){
                ls.setPower(ps * 0.5);
            } else {
                if (ps > 0){
                    ls.setPower(ps * 0.5);
                } else {
                    ls.setPower(0);
                }
            }
            if (!rsms.isPressed()){
                rs.setPower(ps * 0.5);
            } else {
                if (ps > 0){
                    rs.setPower(ps * 0.5);
                } else {
                    rs.setPower(0);
                }
            }
        }

        //Hooking control
        lh.setPower(ph);
        rh.setPower(ph);

        telemetry.addData("Velocity: ", vel);
        telemetry.addData("lmp: ", lmp);
        telemetry.addData("rmp: ", rmp);
        telemetry.addData("af: ", af);
        telemetry.addData("lh: ", lh.getPower());
        telemetry.addData("rh: ", rh.getPower());
        telemetry.addData("lsms is connected: ", lsms.getConnectionInfo());
        telemetry.addData("rsms is connected: ", rsms.getConnectionInfo());
        telemetry.addData("lsms: ", lsms.isPressed());
        telemetry.addData("rsms: ", rsms.isPressed());
        telemetry.addData("rf: ", rfp);
        telemetry.addData("lf: ", lfp);
        telemetry.addData("spr: ", spr);
        telemetry.addData("spl: ", spl);
        telemetry.addData("speed factor: ", speed);
        telemetry.addData("single_control: ", single_control);
        telemetry.addData("ching_chong1 power: ", ching_chong.getPower());
        telemetry.addData("ching_chong2 power: ", ching_chong2.getPower());
        telemetry.update();


    }
}
