package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual", group="Pushbot")

public class TeleOp_Actual extends LinearOpMode{
    HardwareMap hwMap = null;

    public DcMotor r_motor;
    public DcMotor l_motor;
    public boolean bar = false;
    public boolean dumper = false;
    public DcMotor lift;
    public DcMotor climb;
    public DcMotor l_intake;
    public DcMotor r_intake;

    public Servo l_bar;// Left Bar Servo
    public Servo r_bar;// Right Bar Servo

    public Servo l_dump;//Left Dumper Servo
    public Servo r_dump;//Right Dumper Servo

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        r_motor = hardwareMap.dcMotor.get("R_Drive");
        l_motor = hardwareMap.dcMotor.get("L_Drive");
        lift = hardwareMap.dcMotor.get("Climbing Motor");
        l_bar = hardwareMap.servo.get("L_Bar");
        r_bar = hardwareMap.servo.get("R_Bar");
    }
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            //left motor
            float left_speed = lefty();
            l_motor.setPower(left_speed);
            telemetry.addData("Left Track", left_speed);
            telemetry.update();
            //right motor
            float right_speed =  righty();
            r_motor.setPower(right_speed);
            telemetry.addData("Right Track", right_speed);
            telemetry.update();
            //Lift Motor
            elevator();
            bar();
            dumper();
            intake();
 
        }
    }
    public void climb(){
        if(gamepad2.y){
            climb.setPower(1);
        }
    }
    public void intake(){
        if(gamepad2.x){
            l_intake.setPower(1);
            r_intake.setPower(1);
        }
    }
    public void dumper(){
        if(gamepad2.left_bumper && !dumper){
            dumper = true;
            l_dump.setPosition(1);
            r_dump.setPosition(1);
        }else if(gamepad2.left_bumper && dumper){
            dumper = false;
            l_dump.setPosition(0);
            r_dump.setPosition(0);
        }
    }
    public void bar(){
        if(gamepad2.right_bumper && !bar){
            bar = true;
            l_bar.setPosition(1);
            r_bar.setPosition(1);
        }else if(gamepad2.right_bumper && bar){
            bar = false;
            l_bar.setPosition(.5);
            r_bar.setPosition(.5);
        }
    }
    public void elevator() {
        if (gamepad2.left_trigger > 0) {
            lift.setPower(-gamepad2.left_trigger);
        }else{
            lift.setPower(gamepad2.right_trigger);
        }
    }
    public float lefty(){
        if ((-gamepad1.left_stick_y + gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y + gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y + gamepad1.left_stick_x);
        }
    }
    public float righty(){
        if ((-gamepad1.left_stick_y - gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y - gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y - gamepad1.left_stick_x);
        }
    }
}






