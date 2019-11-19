package org.firstinspires.ftc.teamcode.tele;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual", group="Pushbot")
@Disabled
public class TeleOp_Actual extends LinearOpMode{
    HardwareMap hwMap = null;

    private static boolean overrideDrive = false;
    private static boolean overrideLift = true;

    public DcMotor r_motor;
    public DcMotor l_motor;
    public DcMotor lift;
    public DcMotor intake;


    public Servo l_dump;//Left Dumper Servo
    public Servo r_dump;//Right Dumper Servo


    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        intake = hardwareMap.dcMotor.get("Intake Motor");
        lift = hardwareMap.dcMotor.get("Lifting Motor");
        l_dump = hardwareMap.servo.get("L_Dump");
        r_dump = hardwareMap.servo.get("R_Dump");
        l_dump.setPosition(.45);
        r_dump.setPosition(.55);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void runOpMode() throws InterruptedException{
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            //left motor
            float left_speed = -lefty();
            if(!opModeIsActive()) return;
            telemetry.addData("Left Track", left_speed);
            //right motor
            float right_speed = righty();
            if(!opModeIsActive()) return;
            telemetry.addData("Right Track", right_speed);

            l_motor.setPower(left_speed);
            r_motor.setPower(right_speed);
            telemetry.update();
        //bar();
            if(!opModeIsActive()) return;
            dumper();
            if(!opModeIsActive()) return;
            intake();
            if(!opModeIsActive()) return;
            //servo();
            if(!opModeIsActive()) return;
            lift();
            if(!opModeIsActive()) return;
            override();
        }
    }



    public void intake(){
        if(gamepad2.b) {
            intake.setPower(-1);
        }else if(gamepad2.a){
            intake.setPower(1);
        }else{
            intake.setPower(0);
        }
    }

    private boolean flag1 = true;
    private boolean flag2 = true;
    public void override(){
        if(gamepad2.x && flag2){
            overrideDrive = !overrideDrive;
            flag2 = false;
        }else{
            flag2 = true;
        }
        if(gamepad1.x && flag1){
            overrideLift = !overrideLift;
            flag1 = false;
        }else{
            flag1 = true;
        }
    }


    public void dumper(){
        if(gamepad2.dpad_up) {
            l_dump.setPosition(0);
            r_dump.setPosition(1);
        } else if(gamepad2.dpad_left || gamepad2.dpad_right) {
            l_dump.setPosition(.45);
            r_dump.setPosition(.55);
        } else if(gamepad2.dpad_down) {
            l_dump.setPosition(.9);
            r_dump.setPosition(.1);
        }
    }
    public void lift() {
        double trigR = 0;
        double trigL = 0;
        if(overrideLift){
            trigR = gamepad1.right_trigger;
            trigL = gamepad1.left_trigger;
        }else{
            trigR = gamepad2.right_trigger;
            trigL = gamepad2.left_trigger;
        }
        if (trigR > 0.1) {
            lift.setPower(trigR);
        }else if (trigL > 0.1){
            lift.setPower(-trigL);
        }else{
            lift.setPower(0);
        }
    }

    public float cap(float f){
        if(f >= 1)
            return 1;
        else if(f <= -1)
            return -1;
        else
            return f;
    }

    public float lefty(){
        Pair<Float,Float> pair = generateDrivers();
        return cap(-pair.second + pair.first);
    }

    private Pair<Float,Float> generateDrivers(){
        float driveX;
        float driveY;
        if(overrideDrive){
            driveX = gamepad2.left_stick_x;
            driveY = gamepad2.left_stick_y;
        }else{
            driveX = gamepad1.left_stick_x;
            driveY = gamepad1.left_stick_y;
        }
        return new Pair<>(driveX,driveY);
    }
    public float righty(){
        Pair<Float,Float> pair = generateDrivers();
        return cap(-pair.second - pair.first);
    }
}