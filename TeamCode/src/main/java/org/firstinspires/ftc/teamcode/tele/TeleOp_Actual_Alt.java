package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual_altDrive", group="Pushbot")

public class TeleOp_Actual_Alt extends LinearOpMode{
    HardwareMap hwMap = null;

    public DcMotor r_motor;
    public DcMotor l_motor;
    public DcMotor lift;
    public DcMotor intake;


    public Servo l_dump;//Left Dumper Servo
    public Servo r_dump;//Right Dumper Servo
    public Servo lock;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        intake = hardwareMap.dcMotor.get("Intake Motor");
        lift = hardwareMap.dcMotor.get("Lifting Motor");
        l_dump = hardwareMap.servo.get("L_Dump");
        r_dump = hardwareMap.servo.get("R_Dump");
        lock = hardwareMap.servo.get("Lock");
        lock.setPosition(0.5);
        l_dump.setPosition(.25);
        r_dump.setPosition(.75);
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
            double left_speed = -lefty();
            l_motor.setPower(left_speed);
            telemetry.addData("Left Track", left_speed);
            //right motor
            double right_speed = righty();
            r_motor.setPower(right_speed);
            telemetry.addData("Right Track", right_speed);
            telemetry.update();
            //bar();
            dumper();
            intake();
            //servo();
            lift();
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

    public void servo(){
        if(gamepad1.x ){
            lock.setPosition(1);
        }else if(gamepad1.y){
            lock.setPosition(0);
        }
    }

    public void dumper(){
        if(gamepad2.dpad_up) {
            l_dump.setPosition(0);
            r_dump.setPosition(1);
        } else if(gamepad2.dpad_left || gamepad2.dpad_right) {
            l_dump.setPosition(.25);
            r_dump.setPosition(.75);
        } else if(gamepad2.dpad_down) {
            l_dump.setPosition(1);
            r_dump.setPosition(0);
        }
    }
    //    public void bar(){
//        if(gamepad2.right_bumper && !bar){
//            bar = true;
//            l_bar.setPosition(1);
//            r_bar.setPosition(1);
//        }else if(gamepad2.right_bumper && bar){
//            bar = false;
//            l_bar.setPosition(.5);
//            r_bar.setPosition(.5);
//        }
//    }
    public void lift() {
        if (gamepad2.right_trigger > 0.1) {
            lift.setPower(-gamepad2.right_trigger);
        }else if (gamepad2.left_trigger > 0.1){
            lift.setPower(gamepad2.left_trigger);
        }else{
            lift.setPower(0);
        }
    }

    public double lefty(){
        return -gamepad1.left_stick_y;
    }
    public double righty(){
        return -gamepad1.right_stick_y;
    }
}